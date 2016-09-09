package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class ServiceSupplierClientDto extends AbstractBusinessDto {
    private LookupDto type;
    private LookupDto status;
    private String email;
    private String identity;
    private String name;
    private String licenseCopy;
    private String identityCopy;

    public String getEmail() {
        return email;
    }

    public String getIdentity() {
        return identity;
    }

    public String getIdentityCopy() {
        return identityCopy;
    }

    public String getLicenseCopy() {
        return licenseCopy;
    }

    public String getName() {
        return name;
    }

    public LookupDto getStatus() {
        return status;
    }

    public LookupDto getType() {
        return type;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setIdentity(final String identity) {
        this.identity = identity;
    }

    public void setIdentityCopy(final String identityCopy) {
        this.identityCopy = identityCopy;
    }

    public void setLicenseCopy(final String licenseCopy) {
        this.licenseCopy = licenseCopy;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }

    public void setType(final LookupDto type) {
        this.type = type;
    }
}
