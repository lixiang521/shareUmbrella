package com.blibee.umbrella.pojo;
import java.util.Date;

public class Feedback {
    private Integer id;

    private Long uid;

    private String content;

    private String umbrellaNumber;

    private String umbrellaCabinetNumber;

    private Long leaseId;

    private Byte period;

    private Byte state;

    private String leaseType;

    private String faultType;

    private Byte solveType;

    private Date createTime;

    private Date updateTime;

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUmbrellaNumber() {
        return umbrellaNumber;
    }

    public void setUmbrellaNumber(String umbrellaNumber) {
        this.umbrellaNumber = umbrellaNumber == null ? null : umbrellaNumber.trim();
    }

    public String getUmbrellaCabinetNumber() {
        return umbrellaCabinetNumber;
    }

    public void setUmbrellaCabinetNumber(String umbrellaCabinetNumber) {
        this.umbrellaCabinetNumber = umbrellaCabinetNumber == null ? null : umbrellaCabinetNumber.trim();
    }

    public Long getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(Long leaseId) {
        this.leaseId = leaseId;
    }

    public Byte getPeriod() {
        return period;
    }

    public void setPeriod(Byte period) {
        this.period = period;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(String leaseType) {
        this.leaseType = leaseType == null ? null : leaseType.trim();
    }

    public String getFaultType() {
        return faultType;
    }

    public void setFaultType(String faultType) {
        this.faultType = faultType == null ? null : faultType.trim();
    }

    public Byte getSolveType() {
        return solveType;
    }

    public void setSolveType(Byte solveType) {
        this.solveType = solveType;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}