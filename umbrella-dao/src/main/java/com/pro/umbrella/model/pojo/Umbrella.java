package com.pro.umbrella.model.pojo;

import java.util.Date;

public class Umbrella {
    private Integer id;

    private String umbrellaNumber;

    private String umbrellaCabinetNumber;

    private Byte transState;

    private Byte repairState;

    private Date createTime;

    private Date updateTime;

    private String hardVer;

    private String softVer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Byte getTransState() {
        return transState;
    }

    public void setTransState(Byte transState) {
        this.transState = transState;
    }

    public Byte getRepairState() {
        return repairState;
    }

    public void setRepairState(Byte repairState) {
        this.repairState = repairState;
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
}