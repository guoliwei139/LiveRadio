package com.huashitu.domain;

import java.util.Date;
import javax.persistence.*;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 第三方登陆id
     */
    @Column(name = "third_id")
    private String thirdId;

    /**
     * 昵称
     */
    private String name;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 手机
     */
    private String phone;

    /**
     * 头像id
     */
    @Column(name = "head_pic_id")
    private Long headPicId;

    /**
     * 用户来源 1手机号 2微信授权 3QQ授权
     */
    @Column(name = "user_from")
    private Integer userFrom;

    /**
     * 1男 2女
     */
    private Integer sex;

    /**
     * access_token
     */
    private String token;

    @Column(name = "token_time")
    private Date tokenTime;

    /**
     * 1管理员  2非管理员
     */
    @Column(name = "is_admin")
    private Integer isAdmin;

    /**
     * 用户身份 1操作员  2会员
     */
    private Integer identity;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "delete_flag")
    private Integer deleteFlag;

    /**
     * 个人签名
     */
    private String signature;

    /**
     * 情感状态 0保密 1单身 2热恋 3已婚 4同性
     */
    @Column(name = "emotion_status")
    private Integer emotionStatus;

    /**
     * 家乡省份城市
     */
    private String home;

    /**
     * 职业
     */
    private String profession;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 1已实名认证  2未实名认证
     */
    private Integer certification;

    /**
     * 账户余额
     */
    private Integer balance;

    /**
     * 收益
     */
    private Integer income;

    /**
     * 关注数量
     */
    @Column(name = "attention_count")
    private Integer attentionCount;

    /**
     * 粉丝数量，被关注数量
     */
    @Column(name = "be_attentioned_count")
    private Integer beAttentionedCount;

    /**
     * 直播数
     */
    private Integer liveCount;

    /**
     * 贡献榜
     */
    private Integer contribution;

    public User(Long id, String thirdId, String name, String account, String password, Date birthday, String phone, Long headPicId, Integer userFrom, Integer sex, String token, Date tokenTime, Integer isAdmin, Integer identity, Date createTime, Integer deleteFlag, String signature, Integer emotionStatus, String home, String profession, Integer level, Integer certification, Integer balance, Integer income, Integer attentionCount, Integer beAttentionedCount, Integer liveCount, Integer contribution) {
        this.id = id;
        this.thirdId = thirdId;
        this.name = name;
        this.account = account;
        this.password = password;
        this.birthday = birthday;
        this.phone = phone;
        this.headPicId = headPicId;
        this.userFrom = userFrom;
        this.sex = sex;
        this.token = token;
        this.tokenTime = tokenTime;
        this.isAdmin = isAdmin;
        this.identity = identity;
        this.createTime = createTime;
        this.deleteFlag = deleteFlag;
        this.signature = signature;
        this.emotionStatus = emotionStatus;
        this.home = home;
        this.profession = profession;
        this.level = level;
        this.certification = certification;
        this.balance = balance;
        this.income = income;
        this.attentionCount = attentionCount;
        this.beAttentionedCount = beAttentionedCount;
        this.liveCount = liveCount;
        this.contribution = contribution;
    }

    public User() {
        super();
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取第三方登陆id
     *
     * @return third_id - 第三方登陆id
     */
    public String getThirdId() {
        return thirdId;
    }

    /**
     * 设置第三方登陆id
     *
     * @param thirdId 第三方登陆id
     */
    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    /**
     * 获取昵称
     *
     * @return name - 昵称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置昵称
     *
     * @param name 昵称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取账号
     *
     * @return account - 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账号
     *
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取生日
     *
     * @return birthday - 生日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取手机
     *
     * @return phone - 手机
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机
     *
     * @param phone 手机
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取头像id
     *
     * @return head_pic_id - 头像id
     */
    public Long getHeadPicId() {
        return headPicId;
    }

    /**
     * 设置头像id
     *
     * @param headPicId 头像id
     */
    public void setHeadPicId(Long headPicId) {
        this.headPicId = headPicId;
    }

    /**
     * 获取用户来源 1手机号 2微信授权 3QQ授权
     *
     * @return user_from - 用户来源 1手机号 2微信授权 3QQ授权
     */
    public Integer getUserFrom() {
        return userFrom;
    }

    /**
     * 设置用户来源 1手机号 2微信授权 3QQ授权
     *
     * @param userFrom 用户来源 1手机号 2微信授权 3QQ授权
     */
    public void setUserFrom(Integer userFrom) {
        this.userFrom = userFrom;
    }

    /**
     * 获取1男 2女
     *
     * @return sex - 1男 2女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置1男 2女
     *
     * @param sex 1男 2女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取access_token
     *
     * @return token - access_token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置access_token
     *
     * @param token access_token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return token_time
     */
    public Date getTokenTime() {
        return tokenTime;
    }

    /**
     * @param tokenTime
     */
    public void setTokenTime(Date tokenTime) {
        this.tokenTime = tokenTime;
    }

    /**
     * 获取1管理员  2非管理员
     *
     * @return is_admin - 1管理员  2非管理员
     */
    public Integer getIsAdmin() {
        return isAdmin;
    }

    /**
     * 设置1管理员  2非管理员
     *
     * @param isAdmin 1管理员  2非管理员
     */
    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * 获取用户身份 1操作员  2会员
     *
     * @return identity - 用户身份 1操作员  2会员
     */
    public Integer getIdentity() {
        return identity;
    }

    /**
     * 设置用户身份 1操作员  2会员
     *
     * @param identity 用户身份 1操作员  2会员
     */
    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return delete_flag
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * @param deleteFlag
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * 获取个人签名
     *
     * @return signature - 个人签名
     */
    public String getSignature() {
        return signature;
    }

    /**
     * 设置个人签名
     *
     * @param signature 个人签名
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * 获取情感状态 0保密 1单身 2热恋 3已婚 4同性
     *
     * @return emotion_status - 情感状态 0保密 1单身 2热恋 3已婚 4同性
     */
    public Integer getEmotionStatus() {
        return emotionStatus;
    }

    /**
     * 设置情感状态 0保密 1单身 2热恋 3已婚 4同性
     *
     * @param emotionStatus 情感状态 0保密 1单身 2热恋 3已婚 4同性
     */
    public void setEmotionStatus(Integer emotionStatus) {
        this.emotionStatus = emotionStatus;
    }

    /**
     * 获取家乡省份城市
     *
     * @return home - 家乡省份城市
     */
    public String getHome() {
        return home;
    }

    /**
     * 设置家乡省份城市
     *
     * @param home 家乡省份城市
     */
    public void setHome(String home) {
        this.home = home;
    }

    /**
     * 获取职业
     *
     * @return profession - 职业
     */
    public String getProfession() {
        return profession;
    }

    /**
     * 设置职业
     *
     * @param profession 职业
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * 获取等级
     *
     * @return level - 等级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置等级
     *
     * @param level 等级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取1已实名认证  2未实名认证
     *
     * @return certification - 1已实名认证  2未实名认证
     */
    public Integer getCertification() {
        return certification;
    }

    /**
     * 设置1已实名认证  2未实名认证
     *
     * @param certification 1已实名认证  2未实名认证
     */
    public void setCertification(Integer certification) {
        this.certification = certification;
    }

    /**
     * 获取账户余额
     *
     * @return balance - 账户余额
     */
    public Integer getBalance() {
        return balance;
    }

    /**
     * 设置账户余额
     *
     * @param balance 账户余额
     */
    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    /**
     * 获取收益
     *
     * @return income - 收益
     */
    public Integer getIncome() {
        return income;
    }

    /**
     * 设置收益
     *
     * @param income 收益
     */
    public void setIncome(Integer income) {
        this.income = income;
    }

    /**
     * 获取关注数量
     *
     * @return attention_count - 关注数量
     */
    public Integer getAttentionCount() {
        return attentionCount;
    }

    /**
     * 设置关注数量
     *
     * @param attentionCount 关注数量
     */
    public void setAttentionCount(Integer attentionCount) {
        this.attentionCount = attentionCount;
    }

    /**
     * 获取粉丝数量，被关注数量
     *
     * @return be_attentioned_count - 粉丝数量，被关注数量
     */
    public Integer getBeAttentionedCount() {
        return beAttentionedCount;
    }

    /**
     * 设置粉丝数量，被关注数量
     *
     * @param beAttentionedCount 粉丝数量，被关注数量
     */
    public void setBeAttentionedCount(Integer beAttentionedCount) {
        this.beAttentionedCount = beAttentionedCount;
    }

    /**
     * 获取直播
     *
     * @return liveCount - 直播
     */
    public Integer getLiveCount() {
        return liveCount;
    }

    /**
     * 设置直播
     *
     * @param liveCount 直播
     */
    public void setLiveCount(Integer liveCount) {
        this.liveCount = liveCount;
    }

    /**
     * 获取贡献榜
     *
     * @return contribution - 贡献榜
     */
    public Integer getContribution() {
        return contribution;
    }

    /**
     * 设置贡献榜
     *
     * @param contribution 贡献榜
     */
    public void setContribution(Integer contribution) {
        this.contribution = contribution;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", thirdId='" + thirdId + '\'' +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", headPicId=" + headPicId +
                ", userFrom=" + userFrom +
                ", sex=" + sex +
                ", token='" + token + '\'' +
                ", tokenTime=" + tokenTime +
                ", isAdmin=" + isAdmin +
                ", identity=" + identity +
                ", createTime=" + createTime +
                ", deleteFlag=" + deleteFlag +
                ", signature='" + signature + '\'' +
                ", emotionStatus=" + emotionStatus +
                ", home='" + home + '\'' +
                ", profession='" + profession + '\'' +
                ", level=" + level +
                ", certification=" + certification +
                ", balance=" + balance +
                ", income=" + income +
                ", attentionCount=" + attentionCount +
                ", beAttentionedCount=" + beAttentionedCount +
                ", liveCount=" + liveCount +
                ", contribution=" + contribution +
                '}';
    }
}