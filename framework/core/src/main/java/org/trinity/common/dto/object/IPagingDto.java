package org.trinity.common.dto.object;

public interface IPagingDto extends IDto {
    long getItemCount();

    int getPageCount();

    int getPageIndex();

    int getPageSize();

    String getSortedBy();

    void setItemCount(long itemCount);

    void setPageCount(int pageCount);

    void setPageIndex(int pageIndex);

    void setPageSize(int pageSize);

    void setSortedBy(String sortedBy);
}
