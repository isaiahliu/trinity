package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.ServiceCategoryDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceCategorySearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceCategoryProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceCategoryRepository;
import org.trinity.yqyl.repository.business.entity.ServiceCategory;
import org.trinity.yqyl.repository.business.entity.ServiceCategory_;

@Service
public class ServiceCategoryProcessController extends
		AbstractAutowiredCrudProcessController<ServiceCategory, ServiceCategoryDto, ServiceCategorySearchingDto, IServiceCategoryRepository>
		implements IServiceCategoryProcessController {
	public ServiceCategoryProcessController() {
		super(ServiceCategory.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_CATEGORY);
	}

	@Override
	@Transactional
	public List<ServiceCategoryDto> getAllParentServiceCategories() throws IException {
		final List<ServiceCategory> serviceCategories = getDomainEntityRepository().findAllByParent(null);
		return getDomainObjectConverter().convert(serviceCategories);
	}

	@Override
	@Transactional
	public ServiceCategoryDto getOne(final Long id) throws IException {
		final ServiceCategory entity = getDomainEntityRepository().findOne(id);
		if (entity == null) {
			throw getExceptionFactory().createException(getNoInstanceFoundError(), String.valueOf(id));
		}

		final ServiceCategoryDto dto = getDomainObjectConverter().convert(entity);

		entity.getChildren().forEach(item -> dto.getServiceSubCategories().add(getDomainObjectConverter().convert(item)));

		return dto;
	}

	@Override
	@Transactional
	public List<ServiceCategoryDto> getParentServiceCategoriesWithChildren(final ServiceCategorySearchingDto data) throws IException {
		final Specification<ServiceCategory> specification = (root, query, cb) -> {
			final List<Predicate> predicates = new ArrayList<>();

			if (!StringUtils.isEmpty(data.getName())) {
				predicates.add(cb.like(root.get(ServiceCategory_.name), "%" + data.getName() + "%"));
			}

			predicates.add(cb.isNull(root.get(ServiceCategory_.parent)));

			return cb.and(predicates.toArray(new Predicate[0]));
		};

		final List<ServiceCategory> serviceCategories = getDomainEntityRepository().findAll(specification);
		return serviceCategories.stream().map(serviceCategory -> {
			final ServiceCategoryDto serviceCategoryDto = getDomainObjectConverter().convert(serviceCategory);

			serviceCategory.getChildren()
					.forEach(item -> serviceCategoryDto.getServiceSubCategories().add(getDomainObjectConverter().convert(item)));

			return serviceCategoryDto;
		}).collect(Collectors.toList());
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

	@Override
	protected void addRelationship(final ServiceCategory entity, final ServiceCategoryDto dto) throws IException {
		if (dto.getParent() != null && dto.getParent().getId() != null && dto.getParent().getId() != 0) {
			entity.setParent(getDomainEntityRepository().findOne(dto.getParent().getId()));
		}
	}
}
