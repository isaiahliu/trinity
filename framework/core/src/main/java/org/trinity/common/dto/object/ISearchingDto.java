package org.trinity.common.dto.object;

import java.util.List;

public interface ISearchingDto extends IPagingDto {
    public final String SEARCH_ALL = "ALL";
    public final String SEARCH_ME = "ME";

    RelationshipExpression generateRelationship();

    String getCurrentUsername();

    Long getId();

    String getRsexp();

    String getSearchScope();

    List<String> getStatus();

    default boolean isSearchAll() {
        return SEARCH_ALL.equals(getSearchScope());
    }

    boolean isSearchAllStatus();

    void setCurrentUsername(String currentUsername);

    void setId(final Long id);

    void setRsexp(final String rsexp);

    void setSearchAllStatus(boolean searchAllStatus);

    void setSearchScope(String searchScope);

    void setStatus(List<String> status);
}
