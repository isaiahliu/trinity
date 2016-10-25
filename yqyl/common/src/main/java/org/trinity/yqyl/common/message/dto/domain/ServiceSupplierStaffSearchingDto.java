package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.object.AbstractSearchingDto;

public class ServiceSupplierStaffSearchingDto extends AbstractSearchingDto {
    private String name;
    private String status;

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}
