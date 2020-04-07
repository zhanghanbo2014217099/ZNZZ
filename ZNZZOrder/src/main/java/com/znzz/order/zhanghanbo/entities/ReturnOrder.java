package com.znzz.order.zhanghanbo.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class ReturnOrder implements Serializable {
    /**
     * 退货单编号
     */
    private String rorderId;

    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 产品编号
     */
    private String productId;

    /**
     * 申请退货数量
     */
    private Float rorderNum;

    /**
     * 退货原因
     */
    private String rorderReason;

    /**
     * 退货发起时间
     */
    private Date startDate;

    /**
     * 退货审核人
     */
    private String employeeId;

    /**
     * 备注
     */
    private String note;

    private Date endDate;

    private static final long serialVersionUID = 1L;

    public String getRorderId() {
        return rorderId;
    }

    public void setRorderId(String rorderId) {
        this.rorderId = rorderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Float getRorderNum() {
        return rorderNum;
    }

    public void setRorderNum(Float rorderNum) {
        this.rorderNum = rorderNum;
    }

    public String getRorderReason() {
        return rorderReason;
    }

    public void setRorderReason(String rorderReason) {
        this.rorderReason = rorderReason;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ReturnOrder other = (ReturnOrder) that;
        return (this.getRorderId() == null ? other.getRorderId() == null : this.getRorderId().equals(other.getRorderId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getRorderNum() == null ? other.getRorderNum() == null : this.getRorderNum().equals(other.getRorderNum()))
            && (this.getRorderReason() == null ? other.getRorderReason() == null : this.getRorderReason().equals(other.getRorderReason()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEmployeeId() == null ? other.getEmployeeId() == null : this.getEmployeeId().equals(other.getEmployeeId()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRorderId() == null) ? 0 : getRorderId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getRorderNum() == null) ? 0 : getRorderNum().hashCode());
        result = prime * result + ((getRorderReason() == null) ? 0 : getRorderReason().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEmployeeId() == null) ? 0 : getEmployeeId().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rorderId=").append(rorderId);
        sb.append(", orderId=").append(orderId);
        sb.append(", productId=").append(productId);
        sb.append(", rorderNum=").append(rorderNum);
        sb.append(", rorderReason=").append(rorderReason);
        sb.append(", startDate=").append(startDate);
        sb.append(", employeeId=").append(employeeId);
        sb.append(", note=").append(note);
        sb.append(", endDate=").append(endDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}