package org.trinity.yqyl.common.message.dto.domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.NotEmpty;
import org.trinity.common.dto.domain.AbstractBusinessDto;
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
    private LookupDto status;

    private List<LookupDto> accessRights;

    public List<LookupDto> getAccessRights() {
        if (accessRights == null) {
            accessRights = new ArrayList<>();
        }
        return accessRights;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LookupDto getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    public String getYiquanCode() {
        return yiquanCode;
    }

    public void setAccessRights(final List<LookupDto> accessRights) {
        this.accessRights = accessRights;
    }

    public void setCellphone(final String cellphone) {
        this.cellphone = cellphone;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setYiquanCode(final String yiquanCode) {
        this.yiquanCode = yiquanCode;
    }
}
