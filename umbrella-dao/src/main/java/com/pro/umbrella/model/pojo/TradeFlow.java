package com.pro.umbrella.model.pojo;

import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

public class TradeFlow {
    private Integer id;

    private Long tradeNumber;

    private BigDecimal amount;

    private BigDecimal refundPrepayAmount;

    private BigDecimal refundPartAmount;

    private Long refundPartTradeNumber;

    private Date refundPartTime;

    private Long refundPrepayTradeNumber;

    private Date refundPrepayTime;

    private Date refundTime;

    private Date tradeTime;

    private Byte state;

    private Long leaseNumber;

    private Long uid;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTradeNumber() {
        return tradeNumber;
    }

    public void setTradeNumber(Long tradeNumber) {
        this.tradeNumber = tradeNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRefundPrepayAmount() {
        return refundPrepayAmount;
    }

    public void setRefundPrepayAmount(BigDecimal refundPrepayAmount) {
        this.refundPrepayAmount = refundPrepayAmount;
    }

    public BigDecimal getRefundPartAmount() {
        return refundPartAmount;
    }

    public void setRefundPartAmount(BigDecimal refundPartAmount) {
        this.refundPartAmount = refundPartAmount;
    }

    public Long getRefundPartTradeNumber() {
        return refundPartTradeNumber;
    }

    public void setRefundPartTradeNumber(Long refundPartTradeNumber) {
        this.refundPartTradeNumber = refundPartTradeNumber;
    }

    public Date getRefundPartTime() {
        return refundPartTime;
    }

    public void setRefundPartTime(Date refundPartTime) {
        this.refundPartTime = refundPartTime;
    }

    public Long getRefundPrepayTradeNumber() {
        return refundPrepayTradeNumber;
    }

    public void setRefundPrepayTradeNumber(Long refundPrepayTradeNumber) {
        this.refundPrepayTradeNumber = refundPrepayTradeNumber;
    }

    public Date getRefundPrepayTime() {
        return refundPrepayTime;
    }

    public void setRefundPrepayTime(Date refundPrepayTime) {
        this.refundPrepayTime = refundPrepayTime;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Long getLeaseNumber() {
        return leaseNumber;
    }

    public void setLeaseNumber(Long leaseNumber) {
        this.leaseNumber = leaseNumber;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
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


    public TradeFlow cloneOne() {
        TradeFlow tradeFlow = new TradeFlow();
        BeanUtils.copyProperties(this, tradeFlow);
        return tradeFlow;
    }
}