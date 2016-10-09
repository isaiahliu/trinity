package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

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
import org.trinity.yqyl.repository.business.dataaccess.IServiceInfoRepository;
import org.trinity.yqyl.repository.business.entity.ServiceInfo;
import org.trinity.yqyl.repository.business.entity.ServiceInfo_;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient_;

@Service
public class ServiceInfoProcessController
		extends AbstractAutowiredCrudProcessController<ServiceInfo, ServiceInfoDto, ServiceInfoSearchingDto, IServiceInfoRepository>
		implements IServiceProcessController {

	public ServiceInfoProcessController() {
		super(ServiceInfo.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_INFO);
	}

	@Override
	public Page<ServiceInfoDto> getAll(final ServiceInfoSearchingDto dto) throws IException {
		final Pageable pagable = pagingConverter.convert(dto);

		final Specification<ServiceInfo> specification = (root, query, cb) -> {
			final List<Predicate> predicates = new ArrayList<>();

			if (dto.getServiceSupplierClientId() != null) {
				predicates.add(cb.equal(root.join(ServiceInfo_.serviceSupplierClient).get(ServiceSupplierClient_.userId),
						dto.getServiceSupplierClientId()));
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};

		final Page<ServiceInfo> serviceInfos = getDomainEntityRepository().findAll(specification, pagable);

		return serviceInfos.map(item -> getDomainObjectConverter().convert(item));
	}

}
