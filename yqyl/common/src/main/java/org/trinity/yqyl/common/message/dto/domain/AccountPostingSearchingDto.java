package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.object.AbstractSearchingDto;

public class AccountPostingSearchingDto extends AbstractSearchingDto {
    private String fromDate;
    private String toDate;
    private String category;

    public String getCategory() {
        return category;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public void setFromDate(final String fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(final String toDate) {
        this.toDate = toDate;
    }
}
