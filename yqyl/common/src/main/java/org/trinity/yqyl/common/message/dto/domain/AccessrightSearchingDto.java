package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.object.AbstractSearchingDto;

public class AccessrightSearchingDto extends AbstractSearchingDto {
    private boolean includeSuper = false;

    public boolean isIncludeSuper() {
        return includeSuper;
    }

    public void setIncludeSuper(final boolean includeSuper) {
        this.includeSuper = includeSuper;
    }
}
