package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.object.AbstractSearchingDto;

public class ServiceReceiverClientSearchingDto extends AbstractSearchingDto {
    private String name;
    private String yijincode;
    private String identity;
    private String status;

    public String getIdentity() {
        return identity;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getYijincode() {
        return yijincode;
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

    public void setYijincode(final String yijincode) {
        this.yijincode = yijincode;
    }
}
