package com.huashitu.common.system;

import com.huashitu.vo.AjaxResult;

import java.util.HashMap;

/**
 * Created by levy on 2016/11/11.
 */
public class ResCodeManager {
    //失败消息提示
    public static final String PARAMETER_ERROR = "parameter_error";
    public static final String VALIDATECODE_ERROR = "validatecode_error";
    public static final String VALIDATECODE_SEND_ERROR = "validatecode_send_error";
    public static final String CLIENT_KEY_ERROR = "client_key_error";
    public static final String CLIENT_KEY_NULL_ERROR = "client_key_null_error";
    public static final String CLIENT_KEY_EXPIRE_ERROR = "client_key_expire_error";
    public static final String SUMBIT_INFO_ERROR = "sumbit_info_error";
    public static final String USERID_OR_TOKEN_ERROR = "userid_or_token_error";
    public static final String PHONE_OR_PASSWORD_ERROR = "phone_or_password_error";
    public static final String PHONE_IS_EXSIST_ERROR = "phone_is_exsist_error";
    public static final String PHONE_IS_NO_EXSIST_ERROR = "phone_is_no_exsist_error";
    public static final String USER_IS_NO_EXSIST_ERROR = "user_is_no_exsist_error";



    public static final String NO_TIP = "no_tip";

    private static HashMap<String, String> map;

    private ResCodeManager(){
        map = new HashMap<>();
        addResCode(map);
    }

    public String getResInfo(String resCode) {
        if (!map.containsKey(resCode)) {
            return "此结果码未定义";
        }
        return map.get(resCode);
    }

    private void addResCode(HashMap<String, String> map) {
        map.put(PARAMETER_ERROR,"参数不全，请检查");
        map.put(VALIDATECODE_ERROR,"手机验证码验证失败，请检查");
        map.put(VALIDATECODE_SEND_ERROR,"手机验证码发送失败");
        map.put(CLIENT_KEY_ERROR,"clientKey验证失败");
        map.put(CLIENT_KEY_NULL_ERROR,"clientKey为null,请在请求参数中加上clientKey");
        map.put(CLIENT_KEY_EXPIRE_ERROR,"clientKey过期");
        map.put(SUMBIT_INFO_ERROR,"新用户需要先请求user/login接口,再请求此接口");
        map.put(USERID_OR_TOKEN_ERROR,"用户id或token错误");
        map.put(PHONE_OR_PASSWORD_ERROR,"手机号或者密码错误");
        map.put(PHONE_IS_EXSIST_ERROR,"手机号已注册");
        map.put(PHONE_IS_NO_EXSIST_ERROR,"手机号码不存在于数据库");
        map.put(USER_IS_NO_EXSIST_ERROR,"用户不存在");

        map.put(NO_TIP,"该结果码表示不需要Toast提示，App端需作屏蔽处理");

    }

    private static ResCodeManager instance;

    public static ResCodeManager getInstance() {
        if (instance == null){
            instance = new ResCodeManager();
        }
        return instance;
    }

    public void setResSuccess(AjaxResult ajaxResult, Object object) {
       ajaxResult.setRet("success");
        ajaxResult.setRet_code(NO_TIP);
        ajaxResult.setRet_info(getResInfo(NO_TIP));
        ajaxResult.setContent(object);
    }


    public void setResError(AjaxResult ajaxResult, String resCode) {
        ajaxResult.setRet("error");
        ajaxResult.setRet_code(resCode);
        ajaxResult.setRet_info(getResInfo(resCode));

    }

}
