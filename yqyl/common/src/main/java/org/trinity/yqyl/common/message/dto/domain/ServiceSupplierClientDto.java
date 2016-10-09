package org.trinity.yqyl.common.message.dto.domain;

import java.util.ArrayList;
import java.util.List;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class ServiceSupplierClientDto extends AbstractBusinessDto {
    private LookupDto type;
    private LookupDto status;
    private String email;
    private String identity;
    private String name;
    private String licenseCopy;
    private String identityCopy;
    private String address;
    private String description;
    private List<ServiceCategoryDto> serviceCategories;
    private Double expectedPrice;
    private String logo;

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public Double getExpectedPrice() {
        return expectedPrice;
    }

    public String getIdentity() {
        return identity;
    }

    public String getIdentityCopy() {
        return identityCopy;
    }

    public String getLicenseCopy() {
        return licenseCopy;
    }

    public String getLogo() {
        return logo;
    }

    public String getName() {
        return name;
    }

    public List<ServiceCategoryDto> getServiceCategories() {
        if (serviceCategories == null) {
            serviceCategories = new ArrayList<>();
        }
        return serviceCategories;
    }

    public LookupDto getStatus() {
        return status;
    }

    public LookupDto getType() {
        return type;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setExpectedPrice(final Double expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    public void setIdentity(final String identity) {
        this.identity = identity;
    }

    public void setIdentityCopy(final String identityCopy) {
        this.identityCopy = identityCopy;
    }

    public void setLicenseCopy(final String licenseCopy) {
        this.licenseCopy = licenseCopy;
    }

    public void setLogo(final String logo) {
        this.logo = logo;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setServiceCategories(final List<ServiceCategoryDto> serviceCategories) {
        this.serviceCategories = serviceCategories;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }

    public void setType(final LookupDto type) {
        this.type = type;
    }
}
