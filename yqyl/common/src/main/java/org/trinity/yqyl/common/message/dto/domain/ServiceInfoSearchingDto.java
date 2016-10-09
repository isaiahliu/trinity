package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.object.PagingDto;

public class ServiceInfoSearchingDto extends PagingDto {
    private Long serviceSupplierClientId;

    public Long getServiceSupplierClientId() {
        return serviceSupplierClientId;
    }

    public void setServiceSupplierClientId(final Long serviceSupplierClientId) {
        this.serviceSupplierClientId = serviceSupplierClientId;
    }
}
