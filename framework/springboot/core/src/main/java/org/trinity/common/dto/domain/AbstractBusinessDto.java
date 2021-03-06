package org.trinity.common.dto.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.trinity.common.dto.object.IDto;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.request.AbstractDataRequest.DeleteData;
import org.trinity.common.dto.washer.KeepAfterWashed;

public abstract class AbstractBusinessDto implements IDto {
	@NotNull(groups = { DeleteData.class })
	private Long id;

	private LookupDto status;

	private String createdBy;

	private Date createdDate;

	private String lastModifiedBy;

	private Date lastModifiedDate;

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Long getId() {
		return id;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public LookupDto getStatus() {
		return status;
	}

	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}

	@KeepAfterWashed
	public void setId(final Long id) {
		this.id = id;
	}

	public void setLastModifiedBy(final String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public void setLastModifiedDate(final Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public void setStatus(final LookupDto status) {
		this.status = status;
	}
}
