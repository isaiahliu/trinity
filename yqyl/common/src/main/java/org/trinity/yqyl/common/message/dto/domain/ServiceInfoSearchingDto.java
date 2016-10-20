package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.object.PagingDto;

public class ServiceInfoSearchingDto extends PagingDto {
    private Long serviceSupplierClientId;
    private String name;
    private Long id;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getServiceSupplierClientId() {
        return serviceSupplierClientId;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setServiceSupplierClientId(final Long serviceSupplierClientId) {
        this.serviceSupplierClientId = serviceSupplierClientId;
    }
}
