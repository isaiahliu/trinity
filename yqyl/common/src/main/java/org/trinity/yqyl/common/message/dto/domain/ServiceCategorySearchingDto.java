package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.object.AbstractSearchingDto;

public class ServiceCategorySearchingDto extends AbstractSearchingDto {
    private String name;
    private boolean includeChildren = false;
    private Long parentId;

    public String getName() {
        return name;
    }

    public Long getParentId() {
        return parentId;
    }

    public boolean isIncludeChildren() {
        return includeChildren;
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

}
