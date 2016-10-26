package org.trinity.common.dto.object;

import java.util.List;

public interface ISearchingDto extends IPagingDto {
    RelationshipExpression generateRelationship();

    Long getId();

    String getRsexp();

    List<String> getStatus();

    boolean isSearchAll();

    void setId(final Long id);

    void setRsexp(final String rsexp);

    void setSearchAll(boolean searchAll);

    void setStatus(List<String> status);
}
