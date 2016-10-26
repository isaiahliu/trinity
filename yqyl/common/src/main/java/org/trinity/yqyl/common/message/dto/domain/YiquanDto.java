package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.LookupDto;

public class YiquanDto extends AbstractBusinessDto {
    private Double amount;

    private String cellphone;

    private String code;

    private LookupDto status;

    public Double getAmount() {
        return amount;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getCode() {
        return code;
    }

    public LookupDto getStatus() {
        return status;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }

    public void setCellphone(final String cellphone) {
        this.cellphone = cellphone;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }
}
