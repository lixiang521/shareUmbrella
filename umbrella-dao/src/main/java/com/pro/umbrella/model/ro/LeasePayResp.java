package com.pro.umbrella.model.ro;

import com.pro.umbrella.api.pojo.BaseBean;

import java.util.Set;

public class LeasePayResp extends BaseBean {

    private long uid;
    private String payAmount;
    private long payNo;
    private String leaseNumber;
    private AccountInfo accountInfo;
    private Set<String> payType;
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getLeaseNumber() {
        return leaseNumber;
    }

    public void setLeaseNumber(String leaseNumber) {
        this.leaseNumber = leaseNumber;
    }

    public LeasePayResp.AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(LeasePayResp.AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public static class AccountInfo extends BaseBean {

        private static final long serialVersionUID = 237998426045334230L;
        private boolean balanceOpened;
        private boolean enough;
        private boolean passwordFree;
        private Balance balance;

        public boolean isBalanceOpened() {
            return balanceOpened;
        }

        public void setBalanceOpened(boolean balanceOpened) {
            this.balanceOpened = balanceOpened;
        }

        public LeasePayResp.AccountInfo.Balance getBalance() {
            return balance;
        }

        public void setBalance(LeasePayResp.AccountInfo.Balance balance) {
            this.balance = balance;
        }

        public boolean isEnough() {
            return enough;
        }

        public void setEnough(boolean enough) {
            this.enough = enough;
        }

        public boolean isPasswordFree() {
            return passwordFree;
        }

        public void setPasswordFree(boolean passwordFree) {
            this.passwordFree = passwordFree;
        }

        public static class Balance extends BaseBean {

            private static final long serialVersionUID = -3939083918104490823L;
            private Double amount;
            private String currency;

            public Double getAmount() {
                return amount;
            }

            public void setAmount(Double amount) {
                this.amount = amount;
            }

            public String getCurrency() {
                return currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }
        }

    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public long getPayNo() {
        return payNo;
    }

    public void setPayNo(long payNo) {
        this.payNo = payNo;
    }

    public Set<String> getPayType() {
        return payType;
    }

    public void setPayType(Set<String> payType) {
        this.payType = payType;
    }
}
