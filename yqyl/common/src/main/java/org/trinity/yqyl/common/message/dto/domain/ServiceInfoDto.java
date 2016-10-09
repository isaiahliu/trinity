package org.trinity.yqyl.common.message.dto.domain;

import java.util.ArrayList;
import java.util.List;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class ServiceInfoDto extends AbstractBusinessDto {
    private String name;
    private String description;
    private Double price;
    private LookupDto status;
    private String image;
    private List<ServiceCategoryDto> serviceCategories;
    private ServiceSupplierClientDto serviceSupplierClient;

    public List<ServiceCategoryDto> getServiceCategories() {
        if (serviceCategories == null) {
            serviceCategories = new ArrayList<>();
        }
        return serviceCategories;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public ServiceSupplierClientDto getServiceSupplierClient() {
        return serviceSupplierClient;
    }

    public LookupDto getStatus() {
        return status;
    }

    public void setServiceCategories(final List<ServiceCategoryDto> serviceCategories) {
        this.serviceCategories = serviceCategories;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public void setServiceSupplierClient(final ServiceSupplierClientDto serviceSupplierClient) {
        this.serviceSupplierClient = serviceSupplierClient;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }
}
