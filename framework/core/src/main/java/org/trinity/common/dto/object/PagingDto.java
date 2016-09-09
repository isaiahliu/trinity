package org.trinity.common.dto.object;

/**
 * GET:URL?pageIndex=3&pageSize=20&sortedBy=id_asc,name_desc,age
 *
 * @author Isaiah Liu
 */
public class PagingDto implements IDto {
	private static int DEFAULT_PAGE_INDEX = 0;
	private static int DEFAULT_PAGE_SIZE = 10;

	private int pageIndex = DEFAULT_PAGE_INDEX;
	private int pageSize = DEFAULT_PAGE_SIZE;
	private int pageCount;
	private long itemCount;

	private String sortedBy;

	public long getItemCount() {
		return itemCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getSortedBy() {
		return sortedBy;
	}

	public void setItemCount(final long itemCount) {
		this.itemCount = itemCount;
	}

	public void setPageCount(final int pageCount) {
		this.pageCount = pageCount;
	}

	public void setPageIndex(final int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}

	public void setSortedBy(final String sortedBy) {
		this.sortedBy = sortedBy;
	}
}
