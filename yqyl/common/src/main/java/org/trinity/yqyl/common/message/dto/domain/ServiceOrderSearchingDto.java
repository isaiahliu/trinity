package org.trinity.yqyl.common.message.dto.domain;

import java.util.ArrayList;
import java.util.List;

import org.trinity.common.dto.object.PagingDto;

public class ServiceOrderSearchingDto extends PagingDto {
    private String receiverUserName;
    private Long serviceSupplierClientId;

    private List<String> status;

    public String getReceiverUserName() {
        return receiverUserName;
    }

    public Long getServiceSupplierClientId() {
        return serviceSupplierClientId;
    }

    public List<String> getStatus() {
        if (status == null) {
            status = new ArrayList<>();
        }
        return status;
    }

    public void setReceiverUserName(final String receiverUserName) {
        this.receiverUserName = receiverUserName;
    }

    public void setServiceSupplierClientId(final Long serviceSupplierClientId) {
        this.serviceSupplierClientId = serviceSupplierClientId;
    }

    public void setStatus(final List<String> status) {
        this.status = status;
    }
}
