package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.LookupDto;

public class UserRealnameDto extends AbstractBusinessDto {
    private String credentialCopy;

    private String credentialNo;

    private LookupDto credentialType;

    private String name;

    private LookupDto status;

    public String getCredentialCopy() {
        return credentialCopy;
    }

    public String getCredentialNo() {
        return credentialNo;
    }

    public LookupDto getCredentialType() {
        return credentialType;
    }

    public String getName() {
        return name;
    }

    public LookupDto getStatus() {
        return status;
    }

    public void setCredentialCopy(final String credentialCopy) {
        this.credentialCopy = credentialCopy;
    }

    public void setCredentialNo(final String credentialNo) {
        this.credentialNo = credentialNo;
    }

    public void setCredentialType(final LookupDto credentialType) {
        this.credentialType = credentialType;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }
}
