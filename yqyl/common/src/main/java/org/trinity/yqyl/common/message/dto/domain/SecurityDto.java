package org.trinity.yqyl.common.message.dto.domain;

import org.hibernate.validator.constraints.Length;
import org.trinity.yqyl.common.validation.IValidationMessage;
import org.trinity.yqyl.common.validation.IValidationScenario;

public class SecurityDto {
    @Length(min = 4, groups = { IValidationScenario.IAuthenticate.class,
            IValidationScenario.IRegister.class }, message = IValidationMessage.LENGTH)
    private String username;

    @Length(min = 4, groups = { IValidationScenario.IAuthenticate.class,
            IValidationScenario.IRegister.class }, message = IValidationMessage.LENGTH)
    private String password;

    private boolean servicer;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isServicer() {
        return servicer;
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
}
