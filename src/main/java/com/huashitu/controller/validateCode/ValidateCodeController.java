package com.huashitu.controller.validateCode;

import com.huashitu.common.system.ResCodeManager;
import com.huashitu.common.system.SysStatic;
import com.huashitu.config.BaseCRUDController;
import com.huashitu.domain.ValidateCode;
import com.huashitu.service.validateCode.IValidateCodeService;
import com.huashitu.util.JuHeSendSMSUtils;
import com.huashitu.util.StringUtil;
import com.huashitu.vo.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by levy on 2016/11/10.
 */
@Api( description = "验证码的发送和验证接口")
@Controller
@RequestMapping("/validateCode")
public class ValidateCodeController extends BaseCRUDController<ValidateCode, Long> {
    private static final Logger LOG = Logger.getLogger(ValidateCodeController.class);
    @Autowired
    private IValidateCodeService validateCodeService;

    /**
     * 用户发送验证码
     * @param clientKey
     * @param phone 手机号
     * @param type  短信模板： 1 ：注册 2：忘记密码  3：修改手机号
     * @return
     */
    @ApiOperation(value = "发送验证码接口", notes = "存取用户phone信息和code信息到数据库中，验证时候使用",response = ValidateCode.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientKey", value = "clientKey", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "type", value = "短信模板类型(1注册，2忘记密码，3修改手机号)", required = true, dataType = "String",paramType = "query"),
    })
    @RequestMapping(value = "/send_code", method = RequestMethod.POST)
    public
    @ResponseBody
    Object sendCode(
            String clientKey,
            String phone,
            String type) {
        AjaxResult ajaxResult ;
        Map hashMap;
        if (phone == null || type == null) {
            ResCodeManager.getInstance().setResError(ajaxlist, ResCodeManager.PARAMETER_ERROR);
            return mapper.toJson(ajaxlist);
        }
        try {
            ValidateCode validateCode = new ValidateCode();
            // 随机数
            String code = StringUtil.getRandomNumString(4);
            String tplId = ""; // 短信模板id
            if ("1".equals(type)) {
                tplId = JuHeSendSMSUtils.TPL_ID_REGISTER; // 注册
            } else if ("2".equals(type)) {
                tplId = JuHeSendSMSUtils.TPL_ID_FORGET_PWD; // 忘记密码
            } else {
                tplId = JuHeSendSMSUtils.TPL_ID_GET_CODE; // 修改手机号
            }
            //判断验证码是否发送成功
            if (!JuHeSendSMSUtils.sendSMS(phone, code, tplId)) {
                ajaxResult = new AjaxResult();
                ResCodeManager.getInstance().setResError(ajaxResult, ResCodeManager.VALIDATECODE_SEND_ERROR);
            } else {
                validateCode.setPhone(phone); // 电话
                if (validateCodeService.select(validateCode)!= null) {
                    //删除数据库中该用户的旧验证码
                    this.validateCodeService.delete(validateCode);
                }
                validateCode.setCode(code); // 验证码
                validateCode.setCreateTime(new Date());
                validateCode.setDeleteFlag(SysStatic.NORMALDELFLAG);
                //数据库中保存新验证码
                validateCodeService.insert(validateCode);
                ajaxResult = new AjaxResult();
                hashMap = new HashMap<String ,Object>();
                hashMap.put("code",validateCode.getCode());
                hashMap.put("phone",validateCode.getPhone());

                ResCodeManager.getInstance().setResSuccess(ajaxResult, hashMap);
            }
            return mapper.toJson(ajaxResult);
        } catch (Exception e) {
            LOG.error("===【ValidateCodeController】sendCode验证码发送异常===" + e.getMessage());
        }
        return null;
    }

    /**
     * 用户验证验证码是否正确
     * @param clientKey
     * @param phone 手机号
     * @param code  验证码
     * @return
     */
    @ApiOperation(value = "验证码验证接口", notes = "注意：此接口在验证成功之后，会从数据库中删掉此条验证码记录，在某些接口中已经包含了此过程，不需要另外调用",response = ValidateCode.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientKey", value = "clientKey", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, dataType = "String",paramType = "query"),
    })
    @RequestMapping(value = "/validate_code", method = RequestMethod.POST)
    public
    @ResponseBody
    Object validateCode(
            String clientKey,
            String phone,
            String code) {
        AjaxResult ajaxResult ;
        Map hashMap ;
        try {
            ValidateCode validateCode = new ValidateCode();
            validateCode.setCode(code);
            validateCode.setPhone(phone);
            //查找用户验证码是否存在数据库
            if (this.validateCodeService.selectOne(validateCode) == null) {
                ajaxResult = new AjaxResult();
                ResCodeManager.getInstance().setResError(ajaxResult, ResCodeManager.VALIDATECODE_ERROR);
            } else {
                //验证成功,清空此验证码
                this.validateCodeService.delete(validateCode);
                ajaxResult = new AjaxResult();
                hashMap = new HashMap<String,Object>();
                hashMap.put("code",validateCode.getCode());
                hashMap.put("phone",validateCode.getPhone());

                ResCodeManager.getInstance().setResSuccess(ajaxResult, hashMap);
            }
            return mapper.toJson(ajaxResult);
        } catch (Exception e) {
            LOG.error("===【ValidateCodeController】validateCode验证码验证异常===" + e.getMessage());
        }
        return null;
    }


}
