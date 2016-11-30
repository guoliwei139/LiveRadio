package com.huashitu.controller.indexPage;

import com.github.pagehelper.PageHelper;
import com.huashitu.common.system.ResCodeManager;
import com.huashitu.config.BaseCRUDController;
import com.huashitu.domain.FansAttention;
import com.huashitu.domain.User;
import com.huashitu.service.indexPage.IFansAttentionService;
import com.huashitu.vo.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by levy on 2016/11/21.
 */
@Api(description = "首页“关注”栏目显示列表")
@Controller
@RequestMapping("/attentions")
public class FansAttentionController extends BaseCRUDController {

    @Autowired
    private IFansAttentionService fansAttentionService;


    @ApiOperation(value = "获取关注用户接口", notes = "根据用户id找出此用户关注的偶像用户",response = User.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientKey", value = "clientKey", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "fromUser", value = "用户id", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "显示第几页", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的数量", required = true, dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/getIdols", method = RequestMethod.POST)
    public
    @ResponseBody
    Object getIdols(
            String clientKey,
            String fromUser,
            String pageNum,
            String pageSize) {
        //根据传进来的userid获取此用户关注的人
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<User> users = this.fansAttentionService.selectIdolByUserId(Long.parseLong(fromUser));
        AjaxResult ajaxResult = new AjaxResult();
        ResCodeManager.getInstance().setResSuccess(ajaxResult, users);
        return mapper.toJson(ajaxResult);
    }








}
