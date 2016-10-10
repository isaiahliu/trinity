package org.trinity.yqyl.common.message.dto.domain;

import java.util.Date;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class ServiceSubOrderDto extends AbstractBusinessDto {
    private ServiceInfoDto service;
    private Date serviceDate;
    private Integer serviceHour;
    private Double price;
    private String phone;
    private String address;
    private LookupDto status;

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Double getPrice() {
        return price;
    }

    public ServiceInfoDto getService() {
        return service;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public Integer getServiceHour() {
        return serviceHour;
    }

    public LookupDto getStatus() {
        return status;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public void setService(final ServiceInfoDto service) {
        this.service = service;
    }

    public void setServiceDate(final Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public void setServiceHour(final Integer serviceHour) {
        this.serviceHour = serviceHour;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }
}
