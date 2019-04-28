package com.pro.umbrella.model.pojo;

public class OrderSnapshotWithBLOBs extends OrderSnapshot {
    private String lendContent;

    private String returnContent;

    public String getLendContent() {
        return lendContent;
    }

    public void setLendContent(String lendContent) {
        this.lendContent = lendContent == null ? null : lendContent.trim();
    }

    public String getReturnContent() {
        return returnContent;
    }

    public void setReturnContent(String returnContent) {
        this.returnContent = returnContent == null ? null : returnContent.trim();
    }
}