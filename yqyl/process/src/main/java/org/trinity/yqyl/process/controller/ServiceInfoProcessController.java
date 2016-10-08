package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.ServiceInfoDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceInfoSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceCategoryRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceInfoRepository;
import org.trinity.yqyl.repository.business.entity.ServiceCategory;
import org.trinity.yqyl.repository.business.entity.ServiceInfo;
import org.trinity.yqyl.repository.business.entity.ServiceInfo_;

@Service
public class ServiceInfoProcessController
		extends AbstractAutowiredCrudProcessController<ServiceInfo, ServiceInfoDto, ServiceInfoSearchingDto, IServiceInfoRepository>
		implements IServiceProcessController {
	@Autowired
	private IServiceCategoryRepository serviceCategoryRepository;

	public ServiceInfoProcessController() {
		super(ServiceInfo.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_INFO);
	}

	@Override
	@Transactional
	public Page<ServiceInfoDto> getAll(final ServiceInfoSearchingDto searchingData) throws IException {
		final List<ServiceCategory> categories = new ArrayList<>();
		final Pageable pagable = pagingConverter.convert(searchingData);

		if (searchingData.getCategoryChildren().isEmpty()) {
			if (searchingData.getCategoryParent() != null) {
				final ServiceCategory category = serviceCategoryRepository.findOne(searchingData.getCategoryParent());
				if (category != null) {
					categories.addAll(serviceCategoryRepository.findAllByParent(category));
				}
			}
		} else {
			serviceCategoryRepository.findAll(searchingData.getCategoryChildren()).forEach(item -> categories.add(item));
		}

		final Specification<ServiceInfo> specification = (root, query, cb) -> {
			final List<Predicate> predicates = new ArrayList<>();

			if (!categories.isEmpty()) {
				predicates.add(cb.in(root.get(ServiceInfo_.serviceCategories)).value(categories));
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};

		return getDomainEntityRepository().findAll(specification, pagable).map(item -> getDomainObjectConverter().convert(item));
	}
}
