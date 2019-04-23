package com.blibee.umbrella.pojo;

import java.util.Date;

public class OrderSnapshot {
    private Integer id;

    private Long leaseNumber;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getLeaseNumber() {
        return leaseNumber;
    }

    public void setLeaseNumber(Long leaseNumber) {
        this.leaseNumber = leaseNumber;
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