package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.object.PagingDto;

public class OperatorClientSearchingDto extends PagingDto {
    private String username;
    private String status;
    private String staffNo;
    private String name;

    public String getName() {
        return name;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public String getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setStaffNo(final String staffNo) {
        this.staffNo = staffNo;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setUsername(final String username) {
        this.username = username;
    }
}
