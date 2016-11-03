package org.trinity.common.dto.object;

import java.util.ArrayList;
import java.util.List;

public class AccessrightDto extends LookupDto {
    private List<AccessrightDto> children;

    public AccessrightDto() {
        super();
    }

    public AccessrightDto(final String code, final String message) {
        super(code, message);
    }

    public List<AccessrightDto> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
        return children;
    }

    public void setChildren(final List<AccessrightDto> children) {
        this.children = children;
    }
}
