package org.trinity.yqyl.common.message.dto.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.trinity.yqyl.common.validation.IValidationMessage;
import org.trinity.yqyl.common.validation.IValidationScenario;

public class SecurityDto {
    @Length(min = 2, max = 40, groups = { IValidationScenario.IAuthenticate.class,
            IValidationScenario.IRegister.class }, message = IValidationMessage.LENGTH)
    private String username;

    @Length(min = 6, max = 20, groups = { IValidationScenario.IRegister.class }, message = IValidationMessage.LENGTH)
    private String password;

    private boolean servicer = false;

    @Length(min = 11, max = 11, groups = { IValidationScenario.IRegister.class,
            IValidationScenario.IRegisterVerify.class }, message = IValidationMessage.LENGTH)
    private String cellphone;

    @NotNull(groups = { IValidationScenario.IRegister.class })
    private String verifyCode;

    public String getCellphone() {
        return cellphone;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public boolean isServicer() {
        return servicer;
    }

    public void setCellphone(final String cellphone) {
        this.cellphone = cellphone;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setServicer(final boolean servicer) {
        this.servicer = servicer;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setVerifyCode(final String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
