package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.object.PagingDto;

public class ServiceSupplierClientSearchingDto extends PagingDto {
    private Long id;
    private String name;
    private String identity;
    private String email;
    private String status;

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getIdentity() {
        return identity;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setIdentity(final String identity) {
        this.identity = identity;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}
