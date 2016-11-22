package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.object.AbstractSearchingDto;

public class ServiceInfoSearchingDto extends AbstractSearchingDto {
    private Long serviceSupplierClientId;
    private String name;
    private Long categoryId;
    private Long parentCategoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public Long getServiceSupplierClientId() {
        return serviceSupplierClientId;
    }

    public void setCategoryId(final Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setParentCategoryId(final Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public void setServiceSupplierClientId(final Long serviceSupplierClientId) {
        this.serviceSupplierClientId = serviceSupplierClientId;
    }
}
