package com.huashitu.domain;

import javax.persistence.*;
import java.util.Date;

public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "front_type")
    private String frontType;

    @Column(name = "device_token")
    private String deviceToken;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "delete_flag")
    private Integer deleteFlag;

    @Column(name = "is_receive")
    private Integer isReceive;

    @Column(name = "create_time")
    private Date createTime;

    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Device(Long id, Long userId, String frontType, String deviceToken, Date updateTime, Integer deleteFlag, Integer isReceive, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.frontType = frontType;
        this.deviceToken = deviceToken;
        this.updateTime = updateTime;
        this.deleteFlag = deleteFlag;
        this.isReceive = isReceive;
        this.createTime = createTime;
    }

    public Device() {
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
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return front_type
     */
    public String getFrontType() {
        return frontType;
    }

    /**
     * @param frontType
     */
    public void setFrontType(String frontType) {
        this.frontType = frontType;
    }

    /**
     * @return device_token
     */
    public String getDeviceToken() {
        return deviceToken;
    }

    /**
     * @param deviceToken
     */
    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
     * @return is_receive
     */
    public Integer getIsReceive() {
        return isReceive;
    }

    /**
     * @param isReceive
     */
    public void setIsReceive(Integer isReceive) {
        this.isReceive = isReceive;
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
}