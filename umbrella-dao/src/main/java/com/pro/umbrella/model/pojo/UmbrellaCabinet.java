package com.pro.umbrella.model.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class UmbrellaCabinet {
    private Integer id;

    private String umbrellaCabinetNumber;

    private String deviceId;

    private Integer pointId;

    private String msisdn;

    private String vbat;

    private String csq;

    private String shopCode;

    private String shopName;

    private String location;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private BigDecimal upLng;

    private BigDecimal upLat;

    private Byte onlineState;

    private Byte maxCap;

    private Byte propertyCap;

    private Byte transState;

    private Byte occupyState;

    private String merchantCode;

    private String hardVer;

    private String softVer;

    private String city;

    private Date uploadTime;

    private Date locateTime;

    private Integer lostRecord;

    private Integer obtainRecord;

    private String scene;

    private Date putDate;

    private String putPic;

    private Date migrateDate;

    private Byte positionAccordance;

    private String commentMessage;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUmbrellaCabinetNumber() {
        return umbrellaCabinetNumber;
    }

    public void setUmbrellaCabinetNumber(String umbrellaCabinetNumber) {
        this.umbrellaCabinetNumber = umbrellaCabinetNumber == null ? null : umbrellaCabinetNumber.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn == null ? null : msisdn.trim();
    }

    public String getVbat() {
        return vbat;
    }

    public void setVbat(String vbat) {
        this.vbat = vbat == null ? null : vbat.trim();
    }

    public String getCsq() {
        return csq;
    }

    public void setCsq(String csq) {
        this.csq = csq == null ? null : csq.trim();
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode == null ? null : shopCode.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getUpLng() {
        return upLng;
    }

    public void setUpLng(BigDecimal upLng) {
        this.upLng = upLng;
    }

    public BigDecimal getUpLat() {
        return upLat;
    }

    public void setUpLat(BigDecimal upLat) {
        this.upLat = upLat;
    }

    public Byte getOnlineState() {
        return onlineState;
    }

    public void setOnlineState(Byte onlineState) {
        this.onlineState = onlineState;
    }

    public Byte getMaxCap() {
        return maxCap;
    }

    public void setMaxCap(Byte maxCap) {
        this.maxCap = maxCap;
    }

    public Byte getPropertyCap() {
        return propertyCap;
    }

    public void setPropertyCap(Byte propertyCap) {
        this.propertyCap = propertyCap;
    }

    public Byte getTransState() {
        return transState;
    }

    public void setTransState(Byte transState) {
        this.transState = transState;
    }

    public Byte getOccupyState() {
        return occupyState;
    }

    public void setOccupyState(Byte occupyState) {
        this.occupyState = occupyState;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode == null ? null : merchantCode.trim();
    }

    public String getHardVer() {
        return hardVer;
    }

    public void setHardVer(String hardVer) {
        this.hardVer = hardVer == null ? null : hardVer.trim();
    }

    public String getSoftVer() {
        return softVer;
    }

    public void setSoftVer(String softVer) {
        this.softVer = softVer == null ? null : softVer.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getLocateTime() {
        return locateTime;
    }

    public void setLocateTime(Date locateTime) {
        this.locateTime = locateTime;
    }

    public Integer getLostRecord() {
        return lostRecord;
    }

    public void setLostRecord(Integer lostRecord) {
        this.lostRecord = lostRecord;
    }

    public Integer getObtainRecord() {
        return obtainRecord;
    }

    public void setObtainRecord(Integer obtainRecord) {
        this.obtainRecord = obtainRecord;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene == null ? null : scene.trim();
    }

    public Date getPutDate() {
        return putDate;
    }

    public void setPutDate(Date putDate) {
        this.putDate = putDate;
    }

    public String getPutPic() {
        return putPic;
    }

    public void setPutPic(String putPic) {
        this.putPic = putPic == null ? null : putPic.trim();
    }

    public Date getMigrateDate() {
        return migrateDate;
    }

    public void setMigrateDate(Date migrateDate) {
        this.migrateDate = migrateDate;
    }

    public Byte getPositionAccordance() {
        return positionAccordance;
    }

    public void setPositionAccordance(Byte positionAccordance) {
        this.positionAccordance = positionAccordance;
    }

    public String getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage == null ? null : commentMessage.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}