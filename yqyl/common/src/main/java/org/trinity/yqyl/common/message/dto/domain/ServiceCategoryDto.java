package org.trinity.yqyl.common.message.dto.domain;

import java.util.ArrayList;
import java.util.List;

import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.LookupDto;

public class ServiceCategoryDto extends AbstractBusinessDto {
    private String name;
    private String description;
    private LookupDto status;
    private List<ServiceCategoryDto> serviceSubCategories;
    private ServiceCategoryDto parent;

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public ServiceCategoryDto getParent() {
        return parent;
    }

    public List<ServiceCategoryDto> getServiceSubCategories() {
        if (serviceSubCategories == null) {
            serviceSubCategories = new ArrayList<>();
        }
        return serviceSubCategories;
    }

    public LookupDto getStatus() {
        return status;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setParent(final ServiceCategoryDto parent) {
        this.parent = parent;
    }

    public void setServiceSubCategories(final List<ServiceCategoryDto> subServiceCategoryDto) {
        this.serviceSubCategories = subServiceCategoryDto;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }

}
