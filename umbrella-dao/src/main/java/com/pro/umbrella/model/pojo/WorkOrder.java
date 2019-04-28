package com.pro.umbrella.model.pojo;

import java.util.Date;

public class WorkOrder {
    private Integer id;

    private Long workId;

    private String umbrellaCabinetNumber;

    private Long leaseNumber;

    private Integer priority;

    private String feedbackUseValue;

    private String createUser;

    private String executeUser;

    private String feedbackDesc;

    private String pic;

    private String locationName;

    private String locationPosition;

    private String city;

    private Byte state;

    private Byte detailState;

    private Date reviewTime;

    private Byte validState;

    private Byte syncState;

    private String commentMessage;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public String getUmbrellaCabinetNumber() {
        return umbrellaCabinetNumber;
    }

    public void setUmbrellaCabinetNumber(String umbrellaCabinetNumber) {
        this.umbrellaCabinetNumber = umbrellaCabinetNumber == null ? null : umbrellaCabinetNumber.trim();
    }

    public Long getLeaseNumber() {
        return leaseNumber;
    }

    public void setLeaseNumber(Long leaseNumber) {
        this.leaseNumber = leaseNumber;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getFeedbackUseValue() {
        return feedbackUseValue;
    }

    public void setFeedbackUseValue(String feedbackUseValue) {
        this.feedbackUseValue = feedbackUseValue == null ? null : feedbackUseValue.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getExecuteUser() {
        return executeUser;
    }

    public void setExecuteUser(String executeUser) {
        this.executeUser = executeUser == null ? null : executeUser.trim();
    }

    public String getFeedbackDesc() {
        return feedbackDesc;
    }

    public void setFeedbackDesc(String feedbackDesc) {
        this.feedbackDesc = feedbackDesc == null ? null : feedbackDesc.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName == null ? null : locationName.trim();
    }

    public String getLocationPosition() {
        return locationPosition;
    }

    public void setLocationPosition(String locationPosition) {
        this.locationPosition = locationPosition == null ? null : locationPosition.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getDetailState() {
        return detailState;
    }

    public void setDetailState(Byte detailState) {
        this.detailState = detailState;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Byte getValidState() {
        return validState;
    }

    public void setValidState(Byte validState) {
        this.validState = validState;
    }

    public Byte getSyncState() {
        return syncState;
    }

    public void setSyncState(Byte syncState) {
        this.syncState = syncState;
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