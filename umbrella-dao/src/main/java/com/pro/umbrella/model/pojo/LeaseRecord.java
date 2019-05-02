package com.pro.umbrella.model.pojo;

import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

public class LeaseRecord {
    private Integer id;

    private Long leaseNumber;

    private BigDecimal amount;

    private BigDecimal totalAmount;

    private BigDecimal couponAmount;

    private BigDecimal reduceAmount;

    private Long uid;

    private String umbrellaNumber;

    private String cabinetLendNumber;

    private String cabinetBackNumber;

    private Date startTime;

    private Date endTime;

    private String startShopCode;

    private String startShopName;

    private String endShopCode;

    private String endShopName;

    private Byte leaseState;

    private Byte tradeState;

    private Byte freeDeposit;

    private String leaseNode;

    private Byte transState;

    private String leaseResource;

    private String entrance;

    private Byte userResource;

    private String startCity;

    private String endCity;

    private String startScene;

    private BigDecimal archivedAmount;

    private Date createTime;

    private Date updateTime;

    private String payMethod;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getReduceAmount() {
        return reduceAmount;
    }

    public void setReduceAmount(BigDecimal reduceAmount) {
        this.reduceAmount = reduceAmount;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUmbrellaNumber() {
        return umbrellaNumber;
    }

    public void setUmbrellaNumber(String umbrellaNumber) {
        this.umbrellaNumber = umbrellaNumber == null ? null : umbrellaNumber.trim();
    }

    public String getCabinetLendNumber() {
        return cabinetLendNumber;
    }

    public void setCabinetLendNumber(String cabinetLendNumber) {
        this.cabinetLendNumber = cabinetLendNumber == null ? null : cabinetLendNumber.trim();
    }

    public String getCabinetBackNumber() {
        return cabinetBackNumber;
    }

    public void setCabinetBackNumber(String cabinetBackNumber) {
        this.cabinetBackNumber = cabinetBackNumber == null ? null : cabinetBackNumber.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStartShopCode() {
        return startShopCode;
    }

    public void setStartShopCode(String startShopCode) {
        this.startShopCode = startShopCode == null ? null : startShopCode.trim();
    }

    public String getStartShopName() {
        return startShopName;
    }

    public void setStartShopName(String startShopName) {
        this.startShopName = startShopName == null ? null : startShopName.trim();
    }

    public String getEndShopCode() {
        return endShopCode;
    }

    public void setEndShopCode(String endShopCode) {
        this.endShopCode = endShopCode == null ? null : endShopCode.trim();
    }

    public String getEndShopName() {
        return endShopName;
    }

    public void setEndShopName(String endShopName) {
        this.endShopName = endShopName == null ? null : endShopName.trim();
    }

    public Byte getLeaseState() {
        return leaseState;
    }

    public void setLeaseState(Byte leaseState) {
        this.leaseState = leaseState;
    }

    public Byte getTradeState() {
        return tradeState;
    }

    public void setTradeState(Byte tradeState) {
        this.tradeState = tradeState;
    }

    public Byte getFreeDeposit() {
        return freeDeposit;
    }

    public void setFreeDeposit(Byte freeDeposit) {
        this.freeDeposit = freeDeposit;
    }

    public String getLeaseNode() {
        return leaseNode;
    }

    public void setLeaseNode(String leaseNode) {
        this.leaseNode = leaseNode == null ? null : leaseNode.trim();
    }

    public Byte getTransState() {
        return transState;
    }

    public void setTransState(Byte transState) {
        this.transState = transState;
    }

    public String getLeaseResource() {
        return leaseResource;
    }

    public void setLeaseResource(String leaseResource) {
        this.leaseResource = leaseResource == null ? null : leaseResource.trim();
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance == null ? null : entrance.trim();
    }

    public Byte getUserResource() {
        return userResource;
    }

    public void setUserResource(Byte userResource) {
        this.userResource = userResource;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity == null ? null : startCity.trim();
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity == null ? null : endCity.trim();
    }

    public String getStartScene() {
        return startScene;
    }

    public void setStartScene(String startScene) {
        this.startScene = startScene == null ? null : startScene.trim();
    }

    public BigDecimal getArchivedAmount() {
        return archivedAmount;
    }

    public void setArchivedAmount(BigDecimal archivedAmount) {
        this.archivedAmount = archivedAmount;
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

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod == null ? null : payMethod.trim();
    }
    public LeaseRecord cloneOne() {
        LeaseRecord leaseRecord = new LeaseRecord();
        BeanUtils.copyProperties(this, leaseRecord);
        return leaseRecord;
    }
}