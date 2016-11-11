package org.trinity.yqyl.common.message.dto.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.NotEmpty;
import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.request.AbstractDataRequest.AddData;
import org.trinity.common.dto.request.AbstractDataRequest.UpdateData;

public class UserDto extends AbstractBusinessDto {

    @NotEmpty(groups = { AddData.class })
    private String username;

    @NotEmpty(groups = { AddData.class })
    private String password;

    private String cellphone;

    private String email;

    private String yiquanCode;

    @Null(groups = { AddData.class, UpdateData.class })
    private Date lastAccessDate;

    private List<LookupDto> accessrights;

    private AccountDto account;

    public List<LookupDto> getAccessrights() {
        return accessrights;
    }

    public AccountDto getAccount() {
        return account;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getEmail() {
        return email;
    }

    public Date getLastAccessDate() {
        return lastAccessDate;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getYiquanCode() {
        return yiquanCode;
    }

    public void setAccessrights(final List<LookupDto> accessrights) {
        this.accessrights = accessrights;
    }

    public void setAccount(final AccountDto account) {
        this.account = account;
    }

    public void setCellphone(final String cellphone) {
        this.cellphone = cellphone;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setLastAccessDate(final Date lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setYiquanCode(final String yiquanCode) {
        this.yiquanCode = yiquanCode;
    }
}
