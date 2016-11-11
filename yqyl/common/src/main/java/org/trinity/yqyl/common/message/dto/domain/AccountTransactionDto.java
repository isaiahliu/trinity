package org.trinity.yqyl.common.message.dto.domain;

import java.util.List;

import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.LookupDto;

public class AccountTransactionDto extends AbstractBusinessDto {
    private String code;

    private LookupDto type;

    private List<AccountPostingDto> accountPostings;

    public List<AccountPostingDto> getAccountPostings() {
        return accountPostings;
    }

    public String getCode() {
        return code;
    }

    public LookupDto getType() {
        return type;
    }

    public void setAccountPostings(final List<AccountPostingDto> accountPostings) {
        this.accountPostings = accountPostings;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setType(final LookupDto type) {
        this.type = type;
    }
}
