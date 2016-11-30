package com.huashitu.service.validateCode;

import com.huashitu.config.BaseService;
import com.huashitu.domain.ValidateCode;

/**
 * Created by levy on 2016/11/10.
 */
public interface IValidateCodeService extends BaseService<ValidateCode> {

    /**发送验证码，1:注册*/
    public static final String CODE_REGIST = "1";
    /**发送验证码，2:忘记密码*/
    public static final String CODE_FORGETPW = "2";
    /**发送验证码，3:修改手机号*/
    public static final String UPDATE_PHONE = "3";
    /**发送验证码，4:修改密码*/
    public static final String UPDATE_PWD = "4";

}
