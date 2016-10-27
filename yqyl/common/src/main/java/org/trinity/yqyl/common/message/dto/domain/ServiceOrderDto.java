package org.trinity.yqyl.common.message.dto.domain;

import java.util.Date;

import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.LookupDto;

public class ServiceOrderDto extends AbstractBusinessDto {
    private Double price;
    private LookupDto status;
    private String username;
    private Date proposalTime;
    private Date approvalTime;
    private Date settledTime;
    private Date serviceDate;
    private Integer serviceHour;
    private String phone;
    private String address;
    private String receipt;
    private ServiceInfoDto serviceInfo;
    private ServiceOrderAppraiseDto appraise;
    private ServiceSupplierStaffDto staff;
    private byte[] receiptContent;

    public String getAddress() {
        return address;
    }

    public ServiceOrderAppraiseDto getAppraise() {
        return appraise;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public String getPhone() {
        return phone;
    }

    public Double getPrice() {
        return price;
    }

    public Date getProposalTime() {
        return proposalTime;
    }

    public String getReceipt() {
        return receipt;
    }

    public byte[] getReceiptContent() {
        if (receiptContent == null) {
            receiptContent = new byte[0];
        }
        return receiptContent;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public Integer getServiceHour() {
        return serviceHour;
    }

    public ServiceInfoDto getServiceInfo() {
        return serviceInfo;
    }

    public Date getSettledTime() {
        return settledTime;
    }

    public ServiceSupplierStaffDto getStaff() {
        return staff;
    }

    public LookupDto getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setAppraise(final ServiceOrderAppraiseDto appraise) {
        this.appraise = appraise;
    }

    public void setApprovalTime(final Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public void setProposalTime(final Date proposalTime) {
        this.proposalTime = proposalTime;
    }

    public void setReceipt(final String receipt) {
        this.receipt = receipt;
    }

    public void setReceiptContent(final byte[] receiptContent) {
        this.receiptContent = receiptContent;
    }

    public void setServiceDate(final Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public void setServiceHour(final Integer serviceHour) {
        this.serviceHour = serviceHour;
    }

    public void setServiceInfo(final ServiceInfoDto serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public void setSettledTime(final Date settledTime) {
        this.settledTime = settledTime;
    }

    public void setStaff(final ServiceSupplierStaffDto staff) {
        this.staff = staff;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }

    public void setUsername(final String username) {
        this.username = username;
    }
}
