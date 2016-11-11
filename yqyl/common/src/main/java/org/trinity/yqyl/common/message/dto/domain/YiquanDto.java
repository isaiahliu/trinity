package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class YiquanDto extends AbstractBusinessDto {
    private String cellphone;

    private String code;

    private Double topUpAmount;

    private AccountBalanceDto balance;

    public AccountBalanceDto getBalance() {
        return balance;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getCode() {
        return code;
    }

    public Double getTopUpAmount() {
        return topUpAmount;
    }

    public void setBalance(final AccountBalanceDto balance) {
        this.balance = balance;
    }

    public void setCellphone(final String cellphone) {
        this.cellphone = cellphone;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setTopUpAmount(final Double topUpAmount) {
        this.topUpAmount = topUpAmount;
    }
}
