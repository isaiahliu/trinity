package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.object.AbstractSearchingDto;

public class ServiceInfoSearchingDto extends AbstractSearchingDto {
    private Long serviceSupplierClientId;
    private String name;
    private Long id;
    private String status;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getServiceSupplierClientId() {
        return serviceSupplierClientId;
    }

    public String getStatus() {
        return status;
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

    public void setStatus(final String status) {
        this.status = status;
    }
}
