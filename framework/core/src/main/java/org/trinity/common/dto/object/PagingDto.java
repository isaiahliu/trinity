package org.trinity.common.dto.object;

/**
 * GET:URL?pageIndex=3&pageSize=20&sortedBy=id_asc,name_desc,age
 *
 * @author Isaiah Liu
 */
public class PagingDto implements IPagingDto {
    private static int DEFAULT_PAGE_INDEX = 0;
    private static int DEFAULT_PAGE_SIZE = 10;

    private int pageIndex = DEFAULT_PAGE_INDEX;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private int pageCount;
    private long itemCount;

    private String sortedBy;

    @Override
    public long getItemCount() {
        return itemCount;
    }

    @Override
    public int getPageCount() {
        return pageCount;
    }

    @Override
    public int getPageIndex() {
        return pageIndex;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public String getSortedBy() {
        return sortedBy;
    }

    @Override
    public void setItemCount(final long itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public void setPageCount(final int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public void setPageIndex(final int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public void setSortedBy(final String sortedBy) {
        this.sortedBy = sortedBy;
    }
}
