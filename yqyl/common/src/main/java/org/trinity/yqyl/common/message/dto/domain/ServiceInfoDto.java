package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class ServiceInfoDto extends AbstractBusinessDto {
    private String name;
    private String description;
    private Double price;
    private LookupDto status;
    private ServiceCategoryDto category;

    public ServiceCategoryDto getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public LookupDto getStatus() {
        return status;
    }

    public void setCategory(final ServiceCategoryDto category) {
        this.category = category;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }
}
