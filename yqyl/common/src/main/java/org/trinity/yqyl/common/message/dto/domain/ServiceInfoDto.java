package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class ServiceInfoDto extends AbstractBusinessDto {
    private String name;
    private String description;
    private Double price;
    private LookupDto status;
    private String image;
    private LookupDto paymentMethod;
    private LookupDto paymentType;
    private int monthlyProposalOrderCount;
    private double monthlyRate;

    private ServiceSupplierClientDto serviceSupplierClient;

    private ServiceCategoryDto serviceCategory;

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public int getMonthlyProposalOrderCount() {
        return monthlyProposalOrderCount;
    }

    public double getMonthlyRate() {
        return monthlyRate;
    }

    public String getName() {
        return name;
    }

    public LookupDto getPaymentMethod() {
        return paymentMethod;
    }

    public LookupDto getPaymentType() {
        return paymentType;
    }

    public Double getPrice() {
        return price;
    }

    public ServiceCategoryDto getServiceCategory() {
        return serviceCategory;
    }

    public ServiceSupplierClientDto getServiceSupplierClient() {
        return serviceSupplierClient;
    }

    public LookupDto getStatus() {
        return status;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public void setMonthlyProposalOrderCount(final int monthlyProposalOrderCount) {
        this.monthlyProposalOrderCount = monthlyProposalOrderCount;
    }

    public void setMonthlyRate(final double monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPaymentMethod(final LookupDto paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentType(final LookupDto paymentType) {
        this.paymentType = paymentType;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public void setServiceCategory(final ServiceCategoryDto serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public void setServiceSupplierClient(final ServiceSupplierClientDto serviceSupplierClient) {
        this.serviceSupplierClient = serviceSupplierClient;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }
}
