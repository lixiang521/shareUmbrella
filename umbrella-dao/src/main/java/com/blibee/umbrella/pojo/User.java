package com.blibee.umbrella.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private Long uid;

    private Integer totalCount;

    private Integer totalTime;

    private Date firstFinishTime;

    private Date lastFinishTime;

    private Byte foregiftState;

    private Byte state;

    private String telNumber;

    private Date createTime;

    private Date updateTime;

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

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Date getFirstFinishTime() {
        return firstFinishTime;
    }

    public void setFirstFinishTime(Date firstFinishTime) {
        this.firstFinishTime = firstFinishTime;
    }

    public Date getLastFinishTime() {
        return lastFinishTime;
    }

    public void setLastFinishTime(Date lastFinishTime) {
        this.lastFinishTime = lastFinishTime;
    }

    public Byte getForegiftState() {
        return foregiftState;
    }

    public void setForegiftState(Byte foregiftState) {
        this.foregiftState = foregiftState;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber == null ? null : telNumber.trim();
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