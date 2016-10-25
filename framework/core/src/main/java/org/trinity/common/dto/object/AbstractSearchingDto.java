package org.trinity.common.dto.object;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSearchingDto extends PagingDto implements ISearchingDto {
    private boolean searchAll = false;
    private Long id;
    private List<String> status;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public List<String> getStatus() {
        if (status == null) {
            status = new ArrayList<>();
        }
        return status;
    }

    @Override
    public boolean isSearchAll() {
        return searchAll;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    @Override
    public void setSearchAll(final boolean searchAll) {
        this.searchAll = searchAll;
    }

    @Override
    public void setStatus(final List<String> status) {
        this.status = status;
    }
}
