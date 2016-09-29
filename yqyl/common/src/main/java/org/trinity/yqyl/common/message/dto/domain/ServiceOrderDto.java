package org.trinity.yqyl.common.message.dto.domain;

import java.util.Date;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class ServiceOrderDto extends AbstractBusinessDto {
    private Double price;
    private LookupDto status;
    private ServiceInfoDto service;
    private Date serviceTime;

    public Double getPrice() {
        return price;
    }

    public ServiceInfoDto getService() {
        return service;
    }

    public Date getServiceTime() {
        return serviceTime;
    }

    public LookupDto getStatus() {
        return status;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public void setService(final ServiceInfoDto service) {
        this.service = service;
    }

    public void setServiceTime(final Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }
}
