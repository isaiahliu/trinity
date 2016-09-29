package org.trinity.yqyl.common.message.dto.domain;

import java.util.ArrayList;
import java.util.List;

import org.trinity.common.dto.object.PagingDto;

public class ServiceOrderSearchingDto extends PagingDto {
    private String supplierUserName;
    private String receiverUserName;

    private List<String> status;

    public String getReceiverUserName() {
        return receiverUserName;
    }

    public List<String> getStatus() {
        if (status == null) {
            status = new ArrayList<>();
        }
        return status;
    }

    public String getSupplierUserName() {
        return supplierUserName;
    }

    public void setReceiverUserName(final String receiverUserName) {
        this.receiverUserName = receiverUserName;
    }

    public void setStatus(final List<String> status) {
        this.status = status;
    }

    public void setSupplierUserName(final String supplierUserName) {
        this.supplierUserName = supplierUserName;
    }

}