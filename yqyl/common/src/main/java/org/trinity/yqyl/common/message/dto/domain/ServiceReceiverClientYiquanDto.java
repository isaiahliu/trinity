package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class ServiceReceiverClientYiquanDto extends AbstractBusinessDto {
    private String code;

    private Double topUpAmount;

    private AccountBalanceDto balance;

    public AccountBalanceDto getBalance() {
        return balance;
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

    public void setCode(final String code) {
        this.code = code;
    }

    public void setTopUpAmount(final Double topUpAmount) {
        this.topUpAmount = topUpAmount;
    }
}
