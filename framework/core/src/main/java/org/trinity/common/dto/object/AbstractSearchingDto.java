package org.trinity.common.dto.object;

public abstract class AbstractSearchingDto extends PagingDto implements ISearchingDto {
    private boolean searchAll = false;
    private Long id;

    @Override
    public Long getId() {
        return id;
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
}
