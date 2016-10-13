package org.trinity.yqyl.common.message.dto.domain;

import java.util.ArrayList;
import java.util.List;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class ServiceCategoryDto extends AbstractBusinessDto {
    private String name;
    private String description;
    private LookupDto status;
    private List<ServiceCategoryDto> serviceSubCategories;

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public LookupDto getStatus() {
        return status;
    }

    public List<ServiceCategoryDto> getServiceSubCategories() {
        if (serviceSubCategories == null) {
            serviceSubCategories = new ArrayList<>();
        }
        return serviceSubCategories;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }

    public void setServiceSubCategories(final List<ServiceCategoryDto> subServiceCategoryDto) {
        this.serviceSubCategories = subServiceCategoryDto;
    }

}
