package com.blibee.umbrella.model.pojo;

import java.util.Date;

public class HandleResult {
    private Integer id;

    private Long workId;

    private String umbrellaCabinetNumber;

    private Byte umbrellaCabinetCoordinate;

    private String umbrellaTotalNum;

    private String umbrellaDamagedNum;

    private Byte codeState;

    private Byte borrowState;

    private Byte returnState;

    private Byte voiceState;

    private Byte positionState;

    private Byte hardwareState;

    private Byte handleState;

    private String feedbackHardwareValue;

    private String feedbackDesc;

    private String pic;

    private String video;

    private String createUser;

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

    public Byte getUmbrellaCabinetCoordinate() {
        return umbrellaCabinetCoordinate;
    }

    public void setUmbrellaCabinetCoordinate(Byte umbrellaCabinetCoordinate) {
        this.umbrellaCabinetCoordinate = umbrellaCabinetCoordinate;
    }

    public String getUmbrellaTotalNum() {
        return umbrellaTotalNum;
    }

    public void setUmbrellaTotalNum(String umbrellaTotalNum) {
        this.umbrellaTotalNum = umbrellaTotalNum == null ? null : umbrellaTotalNum.trim();
    }

    public String getUmbrellaDamagedNum() {
        return umbrellaDamagedNum;
    }

    public void setUmbrellaDamagedNum(String umbrellaDamagedNum) {
        this.umbrellaDamagedNum = umbrellaDamagedNum == null ? null : umbrellaDamagedNum.trim();
    }

    public Byte getCodeState() {
        return codeState;
    }

    public void setCodeState(Byte codeState) {
        this.codeState = codeState;
    }

    public Byte getBorrowState() {
        return borrowState;
    }

    public void setBorrowState(Byte borrowState) {
        this.borrowState = borrowState;
    }

    public Byte getReturnState() {
        return returnState;
    }

    public void setReturnState(Byte returnState) {
        this.returnState = returnState;
    }

    public Byte getVoiceState() {
        return voiceState;
    }

    public void setVoiceState(Byte voiceState) {
        this.voiceState = voiceState;
    }

    public Byte getPositionState() {
        return positionState;
    }

    public void setPositionState(Byte positionState) {
        this.positionState = positionState;
    }

    public Byte getHardwareState() {
        return hardwareState;
    }

    public void setHardwareState(Byte hardwareState) {
        this.hardwareState = hardwareState;
    }

    public Byte getHandleState() {
        return handleState;
    }

    public void setHandleState(Byte handleState) {
        this.handleState = handleState;
    }

    public String getFeedbackHardwareValue() {
        return feedbackHardwareValue;
    }

    public void setFeedbackHardwareValue(String feedbackHardwareValue) {
        this.feedbackHardwareValue = feedbackHardwareValue == null ? null : feedbackHardwareValue.trim();
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video == null ? null : video.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
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