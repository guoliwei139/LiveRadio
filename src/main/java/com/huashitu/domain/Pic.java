package com.huashitu.domain;

import javax.persistence.*;
import java.util.Date;

public class Pic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;

    @Column(name = "pic_name")
    private String picName;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "delete_flag")
    private Integer deleteFlag;

    public Pic(Long id, String path, String picName, Date createTime, Integer deleteFlag) {
        this.id = id;
        this.path = path;
        this.picName = picName;
        this.createTime = createTime;
        this.deleteFlag = deleteFlag;
    }

    public Pic() {
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
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return pic_name
     */
    public String getPicName() {
        return picName;
    }

    /**
     * @param picName
     */
    public void setPicName(String picName) {
        this.picName = picName;
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