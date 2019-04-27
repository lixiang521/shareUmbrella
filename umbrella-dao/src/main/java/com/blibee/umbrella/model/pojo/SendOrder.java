package com.blibee.umbrella.model.pojo;

import java.util.Date;

public class SendOrder {
    private Integer id;

    private String actionId;

    private String mainDeviceId;

    private String subDeviceId;

    private String deviceType;

    private String operation;

    private String parameter;

    private Byte sendType;

    private Byte sendTimes;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId == null ? null : actionId.trim();
    }

    public String getMainDeviceId() {
        return mainDeviceId;
    }

    public void setMainDeviceId(String mainDeviceId) {
        this.mainDeviceId = mainDeviceId == null ? null : mainDeviceId.trim();
    }

    public String getSubDeviceId() {
        return subDeviceId;
    }

    public void setSubDeviceId(String subDeviceId) {
        this.subDeviceId = subDeviceId == null ? null : subDeviceId.trim();
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
    }

    public Byte getSendType() {
        return sendType;
    }

    public void setSendType(Byte sendType) {
        this.sendType = sendType;
    }

    public Byte getSendTimes() {
        return sendTimes;
    }

    public void setSendTimes(Byte sendTimes) {
        this.sendTimes = sendTimes;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}