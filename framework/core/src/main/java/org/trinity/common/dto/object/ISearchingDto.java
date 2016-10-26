package org.trinity.common.dto.object;

import java.util.List;

public interface ISearchingDto extends IPagingDto {
    public final String SEARCH_ALL = "all";
    public final String SEARCH_ME = "me";

    RelationshipExpression generateRelationship();

    Long getId();

    String getRsexp();

    String getSearchScope();

    List<String> getStatus();

    default boolean isSearchAll() {
        return SEARCH_ALL.equals(getSearchScope());
    }

    void setId(final Long id);

    void setRsexp(final String rsexp);

    void setSearchScope(String searchScope);

    void setStatus(List<String> status);
}
