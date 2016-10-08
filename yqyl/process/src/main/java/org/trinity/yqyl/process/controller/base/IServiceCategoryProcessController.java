package org.trinity.yqyl.process.controller.base;

import java.util.List;

import org.trinity.common.exception.IException;
import org.trinity.process.controller.ICrudProcessController;
import org.trinity.yqyl.common.message.dto.domain.ServiceCategoryDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceCategorySearchingDto;

public interface IServiceCategoryProcessController extends ICrudProcessController<ServiceCategoryDto, ServiceCategorySearchingDto> {
	List<ServiceCategoryDto> getParentServiceCategories() throws IException;

	List<ServiceCategoryDto> getSubServiceCategories(long parentServiceCateogryId) throws IException;
}
