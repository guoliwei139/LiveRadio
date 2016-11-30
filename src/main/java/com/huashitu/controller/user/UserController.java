package com.huashitu.controller.user;

import com.huashitu.common.system.ResCodeManager;
import com.huashitu.common.system.SysStatic;
import com.huashitu.config.BaseCRUDController;
import com.huashitu.domain.Device;
import com.huashitu.domain.Pic;
import com.huashitu.domain.User;
import com.huashitu.domain.ValidateCode;
import com.huashitu.service.device.IDeviceService;
import com.huashitu.service.pic.IPicService;
import com.huashitu.service.user.IUserService;
import com.huashitu.service.validateCode.IValidateCodeService;
import com.huashitu.util.*;
import com.huashitu.vo.AjaxResult;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by levy on 2016/11/8.
 */
@Api(description = "用户相关的CURD操作")
@Controller
@RequestMapping("/user")

public class UserController extends BaseCRUDController<User, Long> {
    private static Logger LOG = Logger.getLogger(User.class);
    @Autowired
    private IUserService userService;
    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private IPicService picService;
    @Autowired
    private IValidateCodeService validateCodeService;

    @ApiOperation(value = "普通用户注册接口", notes = "用户在请求validate/send_code发送手机验证码之后的验证过程已经包含在此接口中，不用另外调取验证验证码接口",response = User.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientKey", value = "clientKey", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public
    @ResponseBody
    Object register(
            String clientKey,
            String phone,
            String code,
            String password
    ) {
        AjaxResult ajaxResult;
        Map hashMap;
        //判断参数非空
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(code) || StringUtils.isBlank(password)) {
            ajaxResult = new AjaxResult();
            ResCodeManager.getInstance().setResError(ajaxResult, ResCodeManager.PARAMETER_ERROR);
            return mapper.toJson(ajaxResult);
        }
        try {
            //验证手机号是否已经注册
            User userTemp = new User();
            userTemp.setPhone(phone);
            if (userService.select(userTemp).size() != 0) {
                ajaxResult = new AjaxResult();
                ResCodeManager.getInstance().setResError(ajaxResult, ResCodeManager.PHONE_IS_EXSIST_ERROR);
                return mapper.toJson(ajaxResult);
            }
            ValidateCode validateCodeTemp = new ValidateCode();
            validateCodeTemp.setPhone(phone);
            validateCodeTemp.setCode(code);
            if (validateCodeService.selectOne(validateCodeTemp) == null) {
                ajaxResult = new AjaxResult();
                ResCodeManager.getInstance().setResError(ajaxResult, ResCodeManager.VALIDATECODE_ERROR);
            } else {
                validateCodeService.delete(validateCodeTemp);
                User user = new User();
                user.setPhone(phone);
                user.setPassword(Encrypt.md5AndSha(password));
                //以下为默认值
                String tempRandom = StringUtil.getNumByCurrTime(9);
                //手机号注册默认昵称为p+账号
                user.setName("p" + tempRandom);
                user.setAccount(tempRandom);
                user.setIdentity(IUserService.IDENTITY_COMPANY);
                user.setIsAdmin(IUserService.IS_NOT_ADMIN);
                user.setDeleteFlag(SysStatic.NORMALDELFLAG);
                user.setBalance(0);
                user.setCertification(2);
                user.setIncome(0);
                user.setUserFrom(IUserService.USER_FROM_NORMAL);
                user.setLevel(1);
                user.setCreateTime(new Date());
                user.setLiveCount(0);
                user.setAttentionCount(0);
                user.setBeAttentionedCount(0);
                user.setContribution(0);
                user.setSex(0);
                user.setEmotionStatus(0);
                String token = StringUtil.generateToken();
                user.setToken(token);
                user.setTokenTime(new Date());

                userService.insertUser(user);
                ajaxResult = new AjaxResult();
                hashMap = new HashMap<String, Object>();

                putUserToMap(user, hashMap);
                hashMap.put("phone", user.getPhone());
//                ObjectToMap.getinstance().objectToHashmap(user, hashMap);
                ResCodeManager.getInstance().setResSuccess(ajaxResult, hashMap);
            }
            return mapper.toJson(ajaxResult);
        } catch (Exception e) {
            LOG.error("===【UserContronller】register异常===" + e.getMessage());
        }
        return null;
    }


    /**
     * 用户第三方登录
     *
     * @param thirdId
     * @param userFrom    用户来源 1:手机号注册  2.微信  3.QQ
     * @param name
     * @param sex
     * @param clientKey
     * @param headUrl     第三方登录获取到的头像地址
     * @param deviceToken
     * @param session
     * @param request
     * @return
     */
    @ApiOperation(value = "第三方用户登陆接口", notes = "注意：此接口的用户head为从第三方那里获取到的头像url地址，deviceToken为可选参数，在处理推送数据时候需要传到后台",response = User.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientKey", value = "clientKey", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "thirdId", value = "第三方用户id", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "userFrom", value = "用户来源:1手机号，2微信，3QQ", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "name", value = "第三方用户昵称", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "sex", value = "性别：0保密，1男，2女", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "deviceToken", value = "设备号", required = false, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "headUrl", value = "头像地址", required = false, dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/third_user_login", method = RequestMethod.POST)
    public
    @ResponseBody
    Object thirdUserLogin(
            String thirdId,
            String userFrom,
            String name,
            String sex,
            HttpSession session,
            String clientKey,
            @RequestParam(required = false) String deviceToken,
            @RequestParam(required = false) String headUrl,
            HttpServletRequest request
    ) {
        AjaxResult ajaxResult;
        Map hashMap;
        if (StringUtils.isBlank(thirdId) || StringUtils.isBlank(userFrom)
                || StringUtils.isBlank(name) || StringUtils.isBlank(sex)) {
            ajaxResult = new AjaxResult();
            ResCodeManager.getInstance().setResError(ajaxResult, ResCodeManager.PARAMETER_ERROR);
            return mapper.toJson(ajaxResult);
        }
        try {
            User user = new User();
            user.setThirdId(thirdId);
            user.setUserFrom(Integer.parseInt(userFrom));
            //判断第三方登录用户已经存在
            User userOld = userService.selectOne(user);
            if (userOld != null) {
                session.setAttribute(SESSION_USER, userOld);
                // 更新用户的deviceToken
                if (StringUtils.isNotBlank(deviceToken)) {
                    changeDeviceToken(userOld, deviceToken, clientKey);
                }
                //返回用户登陆成功
                ajaxResult = new AjaxResult();
                hashMap = new HashMap<String, Object>();
                putUserToMap(userOld, hashMap);
                hashMap.put("sex", userOld.getSex());
//                ObjectToMap.getinstance().objectToHashmap(userOld, hashMap);
                ResCodeManager.getInstance().setResSuccess(ajaxResult, hashMap);
            } else {
                // 如果第三方登录是新用户，进行保存
                user.setIdentity(IUserService.IDENTITY_MEMBER);
                user.setIsAdmin(IUserService.IS_NOT_ADMIN);
                user.setTokenTime(new Date());
                user.setToken(StringUtil.generateToken());
                user.setSex(Integer.parseInt(sex));
                user.setName(name);
                user.setAccount(StringUtil.getNumByCurrTime(9));
                user.setCreateTime(new Date());
                user.setDeleteFlag(SysStatic.NORMALDELFLAG);
                user.setLevel(1);
                user.setEmotionStatus(0);
                user.setBalance(0);
                user.setIncome(0);
                user.setAttentionCount(0);
                user.setBeAttentionedCount(0);
                user.setContribution(0);
                user.setLiveCount(0);

                if (StringUtils.isNoneBlank(headUrl)) {
                    Long picId = picService.saveUrlImg(headUrl, request);
                    user.setHeadPicId(picId);
                }
                //此方法会注入插入user的id值
                userService.insertUser(user);
                //更新deviceToken
                if (StringUtils.isNotBlank(deviceToken)) {
                    changeDeviceToken(user, deviceToken, clientKey);
                }
                session.setAttribute(SESSION_USER, user);
                ajaxResult = new AjaxResult();
                hashMap = new HashMap<String, Object>();
                //用户信息放入hashmap
                putUserToMap(user, hashMap);
                hashMap.put("sex", user.getSex());
                hashMap.put("thirId", user.getThirdId());
//                ObjectToMap.getinstance().objectToHashmap(user, hashMap);
                ResCodeManager.getInstance().setResSuccess(ajaxResult, hashMap);
            }
            return mapper.toJson(ajaxResult);
        } catch (Exception e) {
            LOG.error("===【UserContronller】third_user_login异常===" + e.getMessage());
        }
        return null;
    }


    /**
     * 用户手机号登陆
     *
     * @param clientKey
     * @param phone
     * @param deviceToken
     * @param session
     * @return
     */
    @ApiOperation(value = "普通用户登陆接口", notes = "根据phone和password参数来判断数据库中是否存在此用户",response = User.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientKey", value = "clientKey", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "deviceToken", value = "设备号", required = false, dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public
    @ResponseBody
    Object login(
            String clientKey,
            String phone,
            String password,
            @RequestParam(required = false) String deviceToken,
            HttpSession session
    ) {
        AjaxResult ajaxResult;
        Map hashMap;
        //判断参数非空
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(password)) {
            ajaxResult = new AjaxResult();
            ResCodeManager.getInstance().setResError(ajaxResult, ResCodeManager.PARAMETER_ERROR);
            return mapper.toJson(ajaxResult);
        }
        try {
            User userTemp = new User();
            userTemp.setPhone(phone);
            userTemp.setPassword(Encrypt.md5AndSha(password));
            User user = userService.selectOne(userTemp);
            if (user != null) {
                //登陆成功
                ajaxResult = new AjaxResult();
                hashMap = new HashMap<String, Object>();
                //设置老用户到session
                session.setAttribute(SESSION_USER, user);
                //更新deviceToken
                if (StringUtils.isNotBlank(deviceToken)) {
                    changeDeviceToken(user, deviceToken, clientKey);
                }
                //将需要返回的user信息加入hashmap
                putUserToMap(user, hashMap);
                hashMap.put("sex", user.getSex());
                hashMap.put("phone", user.getPhone());
//                ObjectToMap.getinstance().objectToHashmap(user, hashMap);
                ResCodeManager.getInstance().setResSuccess(ajaxResult, hashMap);
            } else {
                ajaxResult = new AjaxResult();
                hashMap = new HashMap<String, Object>();
                ResCodeManager.getInstance().setResError(ajaxResult, ResCodeManager.PHONE_OR_PASSWORD_ERROR);
            }
            return mapper.toJson(ajaxResult);
        } catch (Exception e) {
            LOG.error("===【UserContronller】login异常===" + e.getMessage());
        }
        return null;
    }


    /**
     * 获取用户信息
     *
     * @param clientKey
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取用户信息接口", notes = "根据用户id来返回数据库中的用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientKey", value = "clientKey", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String",paramType = "query"),
    })
    @RequestMapping(value = "/user_detail", method = RequestMethod.POST)
    public
    @ResponseBody
    Object userDetail(
            String clientKey,
            String userId
    ) {
        AjaxResult ajaxResult;
        Map hashMap;
        if (StringUtils.isBlank(userId)) {
            ajaxResult = new AjaxResult();
            ResCodeManager.getInstance().setResError(ajaxResult, ResCodeManager.PARAMETER_ERROR);
            return mapper.toJson(ajaxResult);
        }
        try {
            User userTemp = new User();
            userTemp.setId(Long.parseLong(userId));
            User user = userService.selectOne(userTemp);
            if (user == null) {
                ajaxResult = new AjaxResult();
                ResCodeManager.getInstance().setResError(ajaxResult, ResCodeManager.USERID_OR_TOKEN_ERROR);
            } else {
                ajaxResult = new AjaxResult();
                hashMap = new HashMap();
                //用户信息放入hashmap
                putUserToMap(user, hashMap);
                hashMap.put("sex", user.getSex());
                hashMap.put("phone", user.getPhone());
//                ObjectToMap.getinstance().objectToHashmap(user, hashMap);
                ResCodeManager.getInstance().setResSuccess(ajaxResult, hashMap);
            }
            return mapper.toJson(ajaxResult);
        } catch (Exception e) {
            LOG.error("===【UserContronller】userDetail异常===" + e.getMessage());
        }
        return null;
    }

    /**
     * 编辑用户资料
     *
     * @param clientKey
     * @param userId
     * @param name
     * @param sex
     * @param signature
     * @param emotionStatus
     * @param home
     * @param profession
     * @return
     */
    @ApiOperation(value = "更新用户资料接口", notes = "注意：返回的json数据中仅包含修改过的信息，未修改的不做返回",response = User.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientKey", value = "clientKey", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "name", value = "昵称", required = false, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "sex", value = "性别：0保密，1男，2女", required = false, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "signature", value = "签名（限制32个汉字）", required = false, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "emotionStatus", value = "感情状态:0保密，1单身，2热恋，3已婚，4同性", required = false, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "home", value = "家乡", required = false, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "profession", value = "职业(限制16个汉字)", required = false, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "head", value = "头像", required = false, dataType = "file",paramType = "query"),
    })
    @RequestMapping(value = "/update_user", method = RequestMethod.POST)
    public
    @ResponseBody
    Object updateUser(
            String clientKey,
            String userId,
            HttpServletRequest request,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String sex,
            @RequestParam(required = false) String signature,
            @RequestParam(required = false) String emotionStatus,
            @RequestParam(required = false) String home,
            @RequestParam(required = false) String profession


    ) {
        AjaxResult ajaxResult;
        Map hashMap = new HashMap<String, Object>();
        if (StringUtils.isBlank(userId)) {
            ajaxResult = new AjaxResult();
            ResCodeManager.getInstance().setResError(ajaxResult, ResCodeManager.PARAMETER_ERROR);
            return mapper.toJson(ajaxResult);
        }
        try {
            User userTemp = new User();
            userTemp.setId(Long.parseLong(userId));
            User user = userService.selectOne(userTemp);
            if (user == null) {
                ajaxResult = new AjaxResult();
                ResCodeManager.getInstance().setResError(ajaxResult, ResCodeManager.USER_IS_NO_EXSIST_ERROR);
            } else {
                //转换请求为multipart请求，否则后面上传时候报错
                if (isMutipartRequest(request)) {
                    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                    MultipartFile head = multipartRequest.getFile("head");
                    //从request中取head头像信息
                    if (head != null) {
                        if (user.getHeadPicId() == null) {
                            Long picId = picService.saveImg(head, request);
                            user.setHeadPicId(picId);
                            Pic picTemp = new Pic();
                            picTemp.setId(picId);
                            Pic pic = picService.selectOne(picTemp);
                            hashMap.put("headPic", pic.getPath() + "/" + pic.getPicName());
                        } else {
                            //先删除再保存
                            Pic p = new Pic();
                            p.setId(user.getHeadPicId());
                            picService.delete(p);
                            Long picId = picService.saveImg(head, request);
                            user.setHeadPicId(picId);
                            Pic picTemp = new Pic();
                            picTemp.setId(picId);
                            Pic pic = picService.selectOne(picTemp);
                            hashMap.put("headPic", pic.getPath() + "/" + pic.getPicName());
                        }

                    }
                }
                if (StringUtils.isNoneBlank(name)) {
                    user.setName(name);
                    hashMap.put("name", name);
                }
                if (StringUtils.isNoneBlank(sex)) {
                    user.setSex(Integer.parseInt(sex));
                    hashMap.put("sex", sex);
                }

                if (StringUtils.isNoneBlank(signature)) {
                    user.setSignature(signature);
                    hashMap.put("signature", signature);
                }
                if (StringUtils.isNoneBlank(emotionStatus)) {
                    user.setEmotionStatus(Integer.parseInt(emotionStatus));
                    hashMap.put("emotionStatus", emotionStatus);
                }
                if (StringUtils.isNotBlank(home)) {
                    user.setHome(home);
                    hashMap.put("home", home);
                }
                if (StringUtils.isNoneBlank(profession)) {
                    user.setProfession(profession);
                    hashMap.put("profession", profession);
                }
                hashMap.put("id", user.getId());
                userService.updateByPrimaryKey(user);
                ajaxResult = new AjaxResult();
                //用户消息放入hashmap
//                putUserToMap(user,hashMap);
//                hashMap.put("sex",user.getSex());
//                hashMap.put("phone",user.getPhone());
//                ObjectToMap.getinstance().objectToHashmap(user, hashMap);
                ResCodeManager.getInstance().setResSuccess(ajaxResult, hashMap);
            }
            return mapper.toJson(ajaxResult);
        } catch (Exception e) {
            LOG.error("===【UserContronller】updateUser异常===" + e.getMessage());
        }
        return null;
    }

    @ApiOperation(value = "找回密码接口", notes = "此接口已经包含了验证验证码的过程，不需要另调验证验证码接口",response = User.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientKey", value = "clientKey", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/find_password", method = RequestMethod.POST)
    public
    @ResponseBody
    Object findPassword(
            String clientKey,
            String phone,
            String password,
            String code
    ) {
        AjaxResult ajaxResult;
        Map hashMap;
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(password) || StringUtils.isBlank(code)) {
            ajaxResult = new AjaxResult();
            ResCodeManager.getInstance().setResError(ajaxResult, ResCodeManager.PARAMETER_ERROR);
            return mapper.toJson(ajaxResult);
        }
        try {
            User userTemp = new User();
            userTemp.setPhone(phone);
            User user = userService.selectOne(userTemp);
            //验证手机号是否存在于数据库
            if (user == null) {
                ajaxResult = new AjaxResult();
                ResCodeManager.getInstance().setResError(ajaxResult, ResCodeManager.PHONE_IS_NO_EXSIST_ERROR);
                return mapper.toJson(ajaxResult);
            }
            //验证验证码是否正确，正确后删除数据库验证码
            ValidateCode validateCodeTemp = new ValidateCode();
            validateCodeTemp.setPhone(phone);
            validateCodeTemp.setCode(code);
            if (validateCodeService.selectOne(validateCodeTemp) == null) {
                ajaxResult = new AjaxResult();
                ResCodeManager.getInstance().setResError(ajaxResult, ResCodeManager.VALIDATECODE_ERROR);
            } else {
                validateCodeService.delete(validateCodeTemp);
                user.setPassword(Encrypt.md5AndSha(password));
                userService.updateByPrimaryKey(user);
                ajaxResult = new AjaxResult();
                hashMap = new HashMap<String, Object>();
                hashMap.put("id", user.getId());
                hashMap.put("phone", user.getPhone());

//                ObjectToMap.getinstance().objectToHashmap(user, hashMap);
                ResCodeManager.getInstance().setResSuccess(ajaxResult, hashMap);
            }
            return mapper.toJson(ajaxResult);
        } catch (Exception e) {
            LOG.error("===【UserContronller】findPassword异常===" + e.getMessage());
        }
        return null;
    }


    /**
     * 当余户和devicetoken都不为空的时候 更新devietoken
     *
     * @param targetUser
     * @param deviceToken
     * @param clientKey
     */
    private void changeDeviceToken(User targetUser, String deviceToken, String clientKey) {
        if (targetUser != null) {
            try {
                Device device = new Device();
                device.setDeviceToken(deviceToken);
                device.setDeleteFlag(SysStatic.NORMALDELFLAG);
                device.setCreateTime(new Date());
                device.setUpdateTime(new Date());
                device.setUserId(targetUser.getId());
                device.setIsReceive(IDeviceService.IS_RECEIVE);
                device.setFrontType(CheckUtils.getClientKey(clientKey));
                deviceService.saveOrUpdateDevice(device);
            } catch (Exception e) {
                LOG.error("====【UserContoller】chagedevicetoken出错====");
            }
        }

    }


    private void updateUserToken(User user, String token) {
        try {
            if (StringUtils.isBlank(token)) {
                // 生成新的token
                String tokenNew = StringUtil.generateToken();
                user.setToken(tokenNew);
                user.setTokenTime(new Date());
                // 更新用户的Token信息
                this.userService.updateByPrimaryKey(user);
            } else {
                user.setToken(token);
                user.setTokenTime(new Date());
                this.userService.updateByPrimaryKey(user);
            }
        } catch (Exception e) {
            LOG.error("===【UserContronller】updateUserToken更新异常===" + e.getMessage());
        }
    }

    /**
     * user用户信息放入map，不包含许多信息
     *
     * @param user
     * @param map
     */
    private void putUserToMap(User user, Map map) {
        map.put("id", user.getId());
        map.put("account", user.getAccount());
        map.put("token", user.getToken());
        map.put("userFrom", user.getUserFrom());
        map.put("income", user.getIncome());
        map.put("level", user.getLevel());
        map.put("beAttentionedCount", user.getBeAttentionedCount());
        map.put("attentionCount", user.getAttentionCount());
        map.put("contribution", user.getContribution());
        map.put("banlance", user.getBalance());
        map.put("liveCount", user.getLiveCount());
        map.put("name", user.getName());
        if (user.getHeadPicId() != null) {
            Pic picTemp = new Pic();
            picTemp.setId(user.getHeadPicId());
            Pic pic = picService.selectOne(picTemp);
            map.put("headPic", pic.getPath() + "/" + pic.getPicName());
        }
    }

    /**
     * 判断是否为mutipart请求
     * @param request
     * @return
     */
    private boolean isMutipartRequest(HttpServletRequest request) {
        String contentType = request.getContentType();  //获取Content-Type
        if ((contentType != null) && (contentType.toLowerCase().startsWith("multipart/"))) {
            return true;
        } else {
            return false;
        }
    }


}



