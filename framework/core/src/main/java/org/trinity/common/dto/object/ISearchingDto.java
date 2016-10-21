package org.trinity.common.dto.object;

public interface ISearchingDto extends IPagingDto {
    boolean isSearchAll();

    void setSearchAll(boolean searchAll);
}
