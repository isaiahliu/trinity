package org.trinity.yqyl.common.message.dto.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class ServiceOrderDto extends AbstractBusinessDto {
    private Double price;
    private LookupDto status;
    private List<ServiceSubOrderDto> serviceSubOrders;
    private Date serviceTime;
    private Integer score;
    private String appraise;
    private String username;

    public String getAppraise() {
        return appraise;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getScore() {
        return score;
    }

    public List<ServiceSubOrderDto> getServiceSubOrders() {
        return serviceSubOrders;
    }

    public Date getServiceTime() {
        return serviceTime;
    }

    public LookupDto getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    public void setAppraise(final String appraise) {
        this.appraise = appraise;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public void setScore(final Integer score) {
        this.score = score;
    }

    public void setServiceSubOrders(List<ServiceSubOrderDto> serviceSubOrders) {
        if (serviceSubOrders == null) {
            serviceSubOrders = new ArrayList<>();
        }
        this.serviceSubOrders = serviceSubOrders;
    }

    public void setServiceTime(final Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }

    public void setUsername(final String username) {
        this.username = username;
    }
}
