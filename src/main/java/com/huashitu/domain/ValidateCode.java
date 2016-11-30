package com.huashitu.domain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "validate_code")
public class ValidateCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String phone;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "delete_flag")
    private Integer deleteFlag;

    public ValidateCode(Long id, String code, String phone, Date createTime, Integer deleteFlag) {
        this.id = id;
        this.code = code;
        this.phone = phone;
        this.createTime = createTime;
        this.deleteFlag = deleteFlag;
    }

    public ValidateCode() {
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
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
}