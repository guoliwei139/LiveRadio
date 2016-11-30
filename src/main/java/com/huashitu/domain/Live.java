package com.huashitu.domain;

import java.util.Date;
import javax.persistence.*;

public class Live {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 主播id
     */
    @Column(name = "anchor_id")
    private Long anchorId;

    /**
     * 直播标题
     */
    private String title;

    /**
     * 开始时间
     */
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 观众数量
     */
    @Column(name = "audience_conut")
    private Integer audienceConut;

    /**
     * 直播封面图片id
     */
    @Column(name = "cover_pic_id")
    private Long coverPicId;

    /**
     * 是否在线，1在线，0不在线
     */
    @Column(name = "online_status")
    private Integer onlineStatus;

    /**
     * geohash算法产生的位置字符串
     */
    @Column(name = "geo_location")
    private String geoLocation;

    /**
     * 经度
     */
    private double lon;

    /**
     * 纬度
     */
    private double lat;

    public Live(Long id, Long anchorId, String title, Date beginTime, Date endTime, Integer audienceConut, Long coverPicId, Integer onlineStatus, String geoLocation, double lon, double lat) {
        this.id = id;
        this.anchorId = anchorId;
        this.title = title;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.audienceConut = audienceConut;
        this.coverPicId = coverPicId;
        this.onlineStatus = onlineStatus;
        this.geoLocation = geoLocation;
        this.lon = lon;
        this.lat = lat;
    }

    public Live() {
        super();
    }

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取主播id
     *
     * @return anchor_id - 主播id
     */
    public Long getAnchorId() {
        return anchorId;
    }

    /**
     * 设置主播id
     *
     * @param anchorId 主播id
     */
    public void setAnchorId(Long anchorId) {
        this.anchorId = anchorId;
    }

    /**
     * 获取直播标题
     *
     * @return title - 直播标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置直播标题
     *
     * @param title 直播标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取开始时间
     *
     * @return begin_time - 开始时间
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 设置开始时间
     *
     * @param beginTime 开始时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取观众数量
     *
     * @return audience_conut - 观众数量
     */
    public Integer getAudienceConut() {
        return audienceConut;
    }

    /**
     * 设置观众数量
     *
     * @param audienceConut 观众数量
     */
    public void setAudienceConut(Integer audienceConut) {
        this.audienceConut = audienceConut;
    }

    /**
     * 获取直播封面图片id
     *
     * @return cover_pic_id - 直播封面图片id
     */
    public Long getCoverPicId() {
        return coverPicId;
    }

    /**
     * 设置直播封面图片id
     *
     * @param coverPicId 直播封面图片id
     */
    public void setCoverPicId(Long coverPicId) {
        this.coverPicId = coverPicId;
    }

    /**
     * 获取是否在线，1在线，0不在线
     *
     * @return online_status - 是否在线，1在线，0不在线
     */
    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    /**
     * 设置是否在线，1在线，0不在线
     *
     * @param onlineStatus 是否在线，1在线，0不在线
     */
    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    /**
     * 获取geohash算法产生的位置字符串
     *
     * @return geo_location - geohash算法产生的位置字符串
     */
    public String getGeoLocation() {
        return geoLocation;
    }

    /**
     * 设置geohash算法产生的位置字符串
     *
     * @param geoLocation geohash算法产生的位置字符串
     */
    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    /**
     * 获取经度
     *
     * @return lon - 经度
     */
    public double getLon() {
        return lon;
    }

    /**
     * 设置经度
     *
     * @param lon 经度
     */
    public void setLon(double lon) {
        this.lon = lon;
    }

    /**
     * 获取纬度
     *
     * @return lat - 纬度
     */
    public double getLat() {
        return lat;
    }

    /**
     * 设置纬度
     *
     * @param lat 纬度
     */
    public void setLat(double lat) {
        this.lat = lat;
    }
}