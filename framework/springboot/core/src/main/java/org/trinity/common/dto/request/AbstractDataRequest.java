package org.trinity.common.dto.request;

import java.util.ArrayList;
import java.util.List;

import org.trinity.common.dto.object.IDto;
import org.trinity.common.dto.validator.OnValid;

public abstract class AbstractDataRequest<Dto extends IDto> extends AbstractRequest {
	public static interface AddData {
	}

	public static interface DeleteData {
	}

	public static interface UpdateData {
	}

	private List<Dto> data;

	@OnValid
	public List<Dto> getData() {
		if (data == null) {
			data = new ArrayList<>();
		}
		return data;
	}

	public void setData(final List<Dto> data) {
		this.data = data;
	}
}
