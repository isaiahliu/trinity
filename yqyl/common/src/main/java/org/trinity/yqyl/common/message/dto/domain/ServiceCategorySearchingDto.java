package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.object.AbstractSearchingDto;

public class ServiceCategorySearchingDto extends AbstractSearchingDto {
    private String name;
    private String status;
    private boolean includeChildren = false;
    private Long id;
    private Long parentId;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getParentId() {
        return parentId;
    }

    public String getStatus() {
        return status;
    }

    public boolean isIncludeChildren() {
        return includeChildren;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setIncludeChildren(final boolean includeChildren) {
        this.includeChildren = includeChildren;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setParentId(final Long parentId) {
        this.parentId = parentId;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}
