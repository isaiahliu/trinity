package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class ServiceCategoryDto extends AbstractBusinessDto {
    private String name;
    private String description;
    private LookupDto status;

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public LookupDto getStatus() {
        return status;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }

}
