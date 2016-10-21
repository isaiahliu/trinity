package org.trinity.common.dto.object;

public abstract class AbstractSearchingDto extends PagingDto implements ISearchingDto {
    private boolean searchAll = false;

    @Override
    public boolean isSearchAll() {
        return searchAll;
    }

    @Override
    public void setSearchAll(final boolean searchAll) {
        this.searchAll = searchAll;
    }
}
