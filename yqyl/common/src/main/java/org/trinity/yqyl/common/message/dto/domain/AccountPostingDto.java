package org.trinity.yqyl.common.message.dto.domain;

import java.util.Date;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class AccountPostingDto extends AbstractBusinessDto {
    private Double amount;
    private AccountBalanceDto balance;
    private Date timestamp;

    public Double getAmount() {
        return amount;
    }

    public AccountBalanceDto getBalance() {
        return balance;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }

    public void setBalance(final AccountBalanceDto balance) {
        this.balance = balance;
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }
}
