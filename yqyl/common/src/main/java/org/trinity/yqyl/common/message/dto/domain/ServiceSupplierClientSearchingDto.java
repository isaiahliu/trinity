package org.trinity.yqyl.common.message.dto.domain;

import java.util.ArrayList;
import java.util.List;

import org.trinity.common.dto.object.PagingDto;

public class ServiceSupplierClientSearchingDto extends PagingDto {
    private Long categoryParent;
    private List<Long> categoryChildren;

    public List<Long> getCategoryChildren() {
        if (categoryChildren == null) {
            categoryChildren = new ArrayList<>();
        }
        return categoryChildren;
    }

    public Long getCategoryParent() {
        return categoryParent;
    }

    public void setCategoryChildren(final List<Long> categoryChildren) {
        this.categoryChildren = categoryChildren;
    }

    public void setCategoryParent(final Long categoryParent) {
        this.categoryParent = categoryParent;
    }

}
