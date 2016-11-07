package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.object.AbstractSearchingDto;

public class ServiceOrderSearchingDto extends AbstractSearchingDto {
    private String receiverUserName;
    private Long serviceSupplierClientId;
    private String serviceDate;
    private String settledDate;
    private String supplierUserName;
    private String category;
    private String paymentMethod;

    public String getCategory() {
        return category;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getReceiverUserName() {
        return receiverUserName;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public Long getServiceSupplierClientId() {
        return serviceSupplierClientId;
    }

    public String getSettledDate() {
        return settledDate;
    }

    public String getSupplierUserName() {
        return supplierUserName;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public void setPaymentMethod(final String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setReceiverUserName(final String receiverUserName) {
        this.receiverUserName = receiverUserName;
    }

    public void setServiceDate(final String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public void setServiceSupplierClientId(final Long serviceSupplierClientId) {
        this.serviceSupplierClientId = serviceSupplierClientId;
    }

    public void setSettledDate(final String settledDate) {
        this.settledDate = settledDate;
    }

    public void setSupplierUserName(final String supplierUserName) {
        this.supplierUserName = supplierUserName;
    }
}
