package org.trinity.yqyl.common.message.dto.domain;

import java.util.Date;

import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.LookupDto;

public class ServiceOrderOperationDto extends AbstractBusinessDto {
    private LookupDto operation;

    private String operator;

    private LookupDto orderStatus;

    private Date timestamp;

    public LookupDto getOperation() {
        return operation;
    }

    public String getOperator() {
        return operator;
    }

    public LookupDto getOrderStatus() {
        return orderStatus;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setOperation(final LookupDto operation) {
        this.operation = operation;
    }

    public void setOperator(final String operator) {
        this.operator = operator;
    }

    public void setOrderStatus(final LookupDto orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

}
