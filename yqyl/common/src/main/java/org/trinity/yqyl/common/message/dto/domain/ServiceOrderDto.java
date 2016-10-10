package org.trinity.yqyl.common.message.dto.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class ServiceOrderDto extends AbstractBusinessDto {
    private Double price;
    private LookupDto status;
    private List<ServiceSubOrderDto> serviceSubOrders;
    private Integer score;
    private String appraise;
    private String username;
    private Date proposalTime;
    private Date approvalTime;
    private Date settledTime;

    public String getAppraise() {
        return appraise;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public Double getPrice() {
        return price;
    }

    public Date getProposalTime() {
        return proposalTime;
    }

    public Integer getScore() {
        return score;
    }

    public List<ServiceSubOrderDto> getServiceSubOrders() {
        return serviceSubOrders;
    }

    public Date getSettledTime() {
        return settledTime;
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

    public void setApprovalTime(final Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public void setProposalTime(final Date proposalTime) {
        this.proposalTime = proposalTime;
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

    public void setSettledTime(final Date settledTime) {
        this.settledTime = settledTime;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }

    public void setUsername(final String username) {
        this.username = username;
    }
}
