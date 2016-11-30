package com.huashitu.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "fans_attention")
public class FansAttention {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 关注者
     */
    @Column(name = "from_user")
    private Long fromUser;

    /**
     * 被关注者
     */
    @Column(name = "to_user")
    private Long toUser;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否互相关注，0否，1是
     */
    private Integer relation;

    public FansAttention(Integer id, Long fromUser, Long toUser, Date createTime, Integer relation) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.createTime = createTime;
        this.relation = relation;
    }

    public FansAttention() {
        super();
    }

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取关注者
     *
     * @return from_user - 关注者
     */
    public Long getFromUser() {
        return fromUser;
    }

    /**
     * 设置关注者
     *
     * @param fromUser 关注者
     */
    public void setFromUser(Long fromUser) {
        this.fromUser = fromUser;
    }

    /**
     * 获取被关注者
     *
     * @return to_user - 被关注者
     */
    public Long getToUser() {
        return toUser;
    }

    /**
     * 设置被关注者
     *
     * @param toUser 被关注者
     */
    public void setToUser(Long toUser) {
        this.toUser = toUser;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取是否互相关注，0否，1是
     *
     * @return relation - 是否互相关注，0否，1是
     */
    public Integer getRelation() {
        return relation;
    }

    /**
     * 设置是否互相关注，0否，1是
     *
     * @param relation 是否互相关注，0否，1是
     */
    public void setRelation(Integer relation) {
        this.relation = relation;
    }
}