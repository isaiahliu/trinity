package org.trinity.yqyl.common.message.dto.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class ServiceOrderRequirementDto extends AbstractBusinessDto {
    private String address;

    private LookupDto announceStatus;

    private LookupDto status;

    private String comment;

    private String phone;

    private Double price;

    private Date serviceDate;

    private Integer serviceHour;

    private List<ServiceOrderDto> serviceOrders;

    private UserDto user;

    public String getAddress() {
        return address;
    }

    public LookupDto getAnnounceStatus() {
        return announceStatus;
    }

    public String getComment() {
        return comment;
    }

    public String getPhone() {
        return phone;
    }

    public Double getPrice() {
        return price;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public Integer getServiceHour() {
        return serviceHour;
    }

    public List<ServiceOrderDto> getServiceOrders() {
        if (serviceOrders == null) {
            serviceOrders = new ArrayList<>();
        }
        return serviceOrders;
    }

    public LookupDto getStatus() {
        return status;
    }

    public UserDto getUser() {
        return user;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setAnnounceStatus(final LookupDto announceStatus) {
        this.announceStatus = announceStatus;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public void setServiceDate(final Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public void setServiceHour(final Integer serviceHour) {
        this.serviceHour = serviceHour;
    }

    public void setServiceOrders(final List<ServiceOrderDto> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }

    public void setUser(final UserDto user) {
        this.user = user;
    }
}
