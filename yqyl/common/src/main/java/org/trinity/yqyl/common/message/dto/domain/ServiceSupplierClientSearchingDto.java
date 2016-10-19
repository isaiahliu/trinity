package org.trinity.yqyl.common.message.dto.domain;

import java.util.ArrayList;
import java.util.List;

import org.trinity.common.dto.object.PagingDto;

public class ServiceSupplierClientSearchingDto extends PagingDto {
    private Long categoryParent;
    private List<Long> categoryChildren;
    private Long id;
    private String name;

    public List<Long> getCategoryChildren() {
        if (categoryChildren == null) {
            categoryChildren = new ArrayList<>();
        }
        return categoryChildren;
    }

    public Long getCategoryParent() {
        return categoryParent;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setCategoryChildren(final List<Long> categoryChildren) {
        this.categoryChildren = categoryChildren;
    }

    public void setCategoryParent(final Long categoryParent) {
        this.categoryParent = categoryParent;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
