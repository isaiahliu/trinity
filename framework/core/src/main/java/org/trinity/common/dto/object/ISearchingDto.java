package org.trinity.common.dto.object;

import java.util.List;

public interface ISearchingDto extends IPagingDto {
    Long getId();

    List<String> getStatus();

    boolean isSearchAll();

    void setId(final Long id);

    void setSearchAll(boolean searchAll);

    void setStatus(List<String> status);
}
