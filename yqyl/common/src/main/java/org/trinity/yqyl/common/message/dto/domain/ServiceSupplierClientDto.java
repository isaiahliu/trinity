package org.trinity.yqyl.common.message.dto.domain;

import java.util.ArrayList;
import java.util.List;

import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.LookupDto;

public class ServiceSupplierClientDto extends AbstractBusinessDto {
    private LookupDto type;
    private LookupDto status;
    private String email;
    private String name;
    private String address;
    private String description;
    private List<ServiceCategoryDto> serviceCategories;
    private Double expectedPrice;
    private String logo;
    private String contact;
    private String contactPhone;
    private String region;
    private String categories;
    private String servicePhone;

    private ServiceSupplierClientAccountDto account;
    private ServiceSupplierClientMaterialDto material;

    public ServiceSupplierClientAccountDto getAccount() {
        return account;
    }

    public String getAddress() {
        return address;
    }

    public String getCategories() {
        return categories;
    }

    public String getContact() {
        return contact;
    }

    public String getContactPhone() {
        return contactPhone;
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

    public String getLogo() {
        return logo;
    }

    public ServiceSupplierClientMaterialDto getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public List<ServiceCategoryDto> getServiceCategories() {
        if (serviceCategories == null) {
            serviceCategories = new ArrayList<>();
        }
        return serviceCategories;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public LookupDto getStatus() {
        return status;
    }

    public LookupDto getType() {
        return type;
    }

    public void setAccount(final ServiceSupplierClientAccountDto account) {
        this.account = account;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setCategories(final String categories) {
        this.categories = categories;
    }

    public void setContact(final String contact) {
        this.contact = contact;
    }

    public void setContactPhone(final String contactPhone) {
        this.contactPhone = contactPhone;
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

    public void setLogo(final String logo) {
        this.logo = logo;
    }

    public void setMaterial(final ServiceSupplierClientMaterialDto material) {
        this.material = material;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setRegion(final String region) {
        this.region = region;
    }

    public void setServiceCategories(final List<ServiceCategoryDto> serviceCategories) {
        this.serviceCategories = serviceCategories;
    }

    public void setServicePhone(final String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }

    public void setType(final LookupDto type) {
        this.type = type;
    }
}
