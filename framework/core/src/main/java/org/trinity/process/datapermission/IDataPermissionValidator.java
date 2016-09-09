package org.trinity.process.datapermission;

import java.util.List;
import java.util.function.Function;

import org.trinity.common.exception.IException;

public interface IDataPermissionValidator<T> {
	Class<T> getEntityType();

	<Dto> void validate(List<Dto> data, Function<Dto, Long> idGetter) throws IException;

	void validate(Long id) throws IException;
}
