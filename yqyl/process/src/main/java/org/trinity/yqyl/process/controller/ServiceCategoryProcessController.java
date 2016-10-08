package org.trinity.yqyl.process.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.ServiceCategoryDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceCategorySearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceCategoryProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceCategoryRepository;
import org.trinity.yqyl.repository.business.entity.ServiceCategory;

@Service
public class ServiceCategoryProcessController extends
		AbstractAutowiredCrudProcessController<ServiceCategory, ServiceCategoryDto, ServiceCategorySearchingDto, IServiceCategoryRepository>
		implements IServiceCategoryProcessController {
	public ServiceCategoryProcessController() {
		super(ServiceCategory.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_CATEGORY);
	}

	@Override
	@Transactional
	public List<ServiceCategoryDto> getParentServiceCategories() throws IException {
		final List<ServiceCategory> serviceCategories = getDomainEntityRepository().findAllByParent(null);
		return getDomainObjectConverter().convert(serviceCategories);
	}

	@Override
	@Transactional
	public List<ServiceCategoryDto> getSubServiceCategories(final long parentServiceCateogryId) throws IException {
		final ServiceCategory parent = getDomainEntityRepository().findOne(parentServiceCateogryId);
		if (parent == null) {
			throw getExceptionFactory().createException(ErrorMessage.UNABLE_TO_FIND_PARENT_CATEGORY);
		}

		final List<ServiceCategory> serviceCategories = getDomainEntityRepository().findAllByParent(parent);
		return getDomainObjectConverter().convert(serviceCategories);
	}
}
