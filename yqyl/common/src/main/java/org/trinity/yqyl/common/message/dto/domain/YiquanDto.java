package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class YiquanDto extends AbstractBusinessDto {
    private Double amount;

    private String cellphone;

    private String code;

    public Double getAmount() {
        return amount;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getCode() {
        return code;
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
}
