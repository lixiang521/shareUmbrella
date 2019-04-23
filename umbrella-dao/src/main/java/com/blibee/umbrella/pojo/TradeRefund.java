package com.blibee.umbrella.pojo;
import java.math.BigDecimal;
import java.util.Date;

public class TradeRefund {
    private Integer id;

    private Long leaseNumber;

    private Long tradeNumber;

    private Long refundTradeNumber;

    private BigDecimal refundAmount;

    private Date refundTime;

    private Byte state;

    private String operator;

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

    public Long getTradeNumber() {
        return tradeNumber;
    }

    public void setTradeNumber(Long tradeNumber) {
        this.tradeNumber = tradeNumber;
    }

    public Long getRefundTradeNumber() {
        return refundTradeNumber;
    }

    public void setRefundTradeNumber(Long refundTradeNumber) {
        this.refundTradeNumber = refundTradeNumber;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
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