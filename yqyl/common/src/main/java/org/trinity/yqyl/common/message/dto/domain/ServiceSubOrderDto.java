package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class ServiceSubOrderDto extends AbstractBusinessDto {
    private ServiceInfoDto service;

    public ServiceInfoDto getService() {
        return service;
    }

    public void setService(final ServiceInfoDto service) {
        this.service = service;
    }
}
