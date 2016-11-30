package com.huashitu.service.user;

import com.huashitu.config.BaseService;
import com.huashitu.domain.User;

/**
 * Created by levy on 2016/11/7.
 */
public interface IUserService  extends BaseService<User>{
    /**用户来源，手机号注册：1*/
    public static final Integer USER_FROM_NORMAL = 1;
    /**用户来源，微信：2*/
    public static final Integer USER_FROM_WX = 2;
    /**用户来源，QQ：3*/
    public static final Integer USER_FROM_QQ = 3;
    /**身份，企业：1*/
    public static final Integer IDENTITY_COMPANY = 1;
    /**身份，会员用户：2*/
    public static final Integer IDENTITY_MEMBER = 2;
    /**管理员：1*/
    public static final Integer IS_ADMIN = 1;
    /**会员：2*/
    public static final Integer IS_NOT_ADMIN = 2;


    /**性别，1：男*/
    public static final Integer SEX_MALE = 1;
    /**性别，2：女*/
    public static final Integer SEX_FEMALE = 2;

    /**
     * 插入用户会把主键id值注入到对象
     * @param user
     * @return
     */
    public Long insertUser(User user);
}


