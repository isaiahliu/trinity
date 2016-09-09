package org.trinity.common.dto.object;

/**
 * @author Isaiah Liu
 */
public class MetaDto implements IDto {
    private PagingDto paging;

    public PagingDto getPaging() {
        return paging;
    }

    public void setPaging(final PagingDto paging) {
        this.paging = paging;
    }
}
