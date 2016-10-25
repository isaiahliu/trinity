package org.trinity.common.dto.object;

public interface ISearchingDto extends IPagingDto {
    Long getId();

    boolean isSearchAll();

    void setId(final Long id);

    void setSearchAll(boolean searchAll);
}
