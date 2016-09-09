package org.trinity.yqyl.common.message.dto.domain;

import java.util.ArrayList;
import java.util.List;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class AccessrightDto extends AbstractBusinessDto {

    private LookupDto name;

    private String description;

    private LookupDto status;

    private List<AccessrightDto> children;

    public List<AccessrightDto> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
        return children;
    }

    public String getDescription() {
        return description;
    }

    public LookupDto getName() {
        return name;
    }

    public LookupDto getStatus() {
        return status;
    }

    public void setChildren(final List<AccessrightDto> children) {
        this.children = children;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setName(final LookupDto name) {
        this.name = name;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }
}
