package com.blibee.umbrella.pojo;
import java.util.Date;

public class OpenRecord {
    private Integer id;

    private Long uid;

    private String cabinetLendNumber;

    private Byte result;

    private String msg;

    private Date createTime;

    private String entrance;

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

    public String getCabinetLendNumber() {
        return cabinetLendNumber;
    }

    public void setCabinetLendNumber(String cabinetLendNumber) {
        this.cabinetLendNumber = cabinetLendNumber == null ? null : cabinetLendNumber.trim();
    }

    public Byte getResult() {
        return result;
    }

    public void setResult(Byte result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance == null ? null : entrance.trim();
    }
}