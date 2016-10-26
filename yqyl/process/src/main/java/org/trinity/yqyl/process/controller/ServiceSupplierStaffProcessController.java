package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.trinity.common.accessright.ISecurityUtil.CheckMode;
import org.trinity.common.exception.IException;
import org.trinity.message.LookupParser;
import org.trinity.process.converter.IObjectConverter.CopyPolicy;
import org.trinity.yqyl.common.message.dto.domain.ServiceCategoryDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierStaffDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierStaffSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.common.message.lookup.StaffStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceSupplierStaffProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IContentRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceCategoryRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceSupplierClientRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceSupplierStaffRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.Content;
import org.trinity.yqyl.repository.business.entity.ServiceCategory;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient_;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierStaff;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierStaff_;
import org.trinity.yqyl.repository.business.entity.User_;

@Service
public class ServiceSupplierStaffProcessController extends
        AbstractAutowiredCrudProcessController<ServiceSupplierStaff, ServiceSupplierStaffDto, ServiceSupplierStaffSearchingDto, IServiceSupplierStaffRepository>
        implements IServiceSupplierStaffProcessController {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IServiceSupplierClientRepository serviceSupplierClientRepository;

    @Autowired
    private IContentRepository contentRepository;

    @Autowired
    private IServiceCategoryRepository serviceCategoryRepository;

    public ServiceSupplierStaffProcessController() {
        super(ServiceSupplierStaff.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_SUPPLIER_STAFF);
    }

    @Override
    @Transactional
    public List<ServiceSupplierStaffDto> addAll(final List<ServiceSupplierStaffDto> data) throws IException {
        for (final ServiceSupplierStaffDto dto : data) {
            final ServiceSupplierStaff entity = getDomainObjectConverter().convertBack(dto);
            final ServiceSupplierClient serviceSupplierClient = userRepository
                    .findOneByUsername(getSecurityUtil().getCurrentToken().getUsername()).getServiceSupplierClient();

            if (dto.getServiceSupplierClient() != null && dto.getServiceSupplierClient().getId() != null
                    && dto.getServiceSupplierClient().getId() > 0
                    && dto.getServiceSupplierClient().getId() != serviceSupplierClient.getUserId()) {
                getSecurityUtil().checkAccessRight(CheckMode.ANY, AccessRight.OPERATOR);

                entity.setServiceSupplierClient(serviceSupplierClientRepository.findOne(dto.getServiceSupplierClient().getId()));
            } else {
                entity.setServiceSupplierClient(serviceSupplierClient);
            }

            final Content content = new Content();
            content.setContent(new byte[0]);
            content.setStatus(RecordStatus.ACTIVE);
            content.setUuid(UUID.randomUUID().toString());
            contentRepository.save(content);

            entity.setPhoto(content.getUuid());

            if (!dto.getServiceCategories().isEmpty()) {
                final List<ServiceCategory> serviceCategories = new ArrayList<>();
                serviceCategoryRepository
                        .findAll(dto.getServiceCategories().stream().map(item -> item.getId()).collect(Collectors.toList()))
                        .forEach(item -> serviceCategories.add(item));

                entity.setServiceCategories(serviceCategories);
            }

            getDomainEntityRepository().save(entity);

            getDomainObjectConverter().convert(entity, dto, CopyPolicy.TARGET_IS_NULL);
        }

        return data;
    }

    @Override
    public Page<ServiceSupplierStaff> queryAll(final ServiceSupplierStaffSearchingDto searchingData) throws IException {
        final String username = getSecurityUtil().getCurrentToken().getUsername();
        final Specification<ServiceSupplierStaff> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (searchingData.isSearchAll()) {
                predicates.add(cb.equal(
                        root.join(ServiceSupplierStaff_.serviceSupplierClient).join(ServiceSupplierClient_.user).get(User_.username),
                        username));
            }

            if (!StringUtils.isEmpty(searchingData.getName())) {
                predicates.add(cb.like(root.get(ServiceSupplierStaff_.name), "%" + searchingData.getName() + "%"));
            }

            if (!searchingData.getStatus().isEmpty()) {
                final In<StaffStatus> in = cb.in(root.get(ServiceSupplierStaff_.status));
                searchingData.getStatus().forEach(item -> in.value(LookupParser.parse(StaffStatus.class, item)));
                predicates.add(in);
            }

            if (searchingData.getId() != null && searchingData.getId() != 0) {
                predicates.add(cb.equal(root.get(ServiceSupplierStaff_.id), searchingData.getId()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return getDomainEntityRepository().findAll(specification, getPagingConverter().convert(searchingData));
    }

    @Override
    protected void updateRelationship(final ServiceSupplierStaff entity, final ServiceSupplierStaffDto dto) {
        final List<ServiceCategoryDto> serviceCategories = dto.getServiceCategories();
        if (serviceCategories != null) {
            entity.getServiceCategories().clear();
            serviceCategoryRepository.findAll(serviceCategories.stream().map(item -> item.getId()).collect(Collectors.toList()))
                    .forEach(item -> entity.getServiceCategories().add(item));
        }
    }

    @Override
    protected void validateDataPermission(final ServiceSupplierStaffDto dto) throws IException {
        final String username = getDomainEntityRepository().findOne(dto.getId()).getServiceSupplierClient().getUser().getUsername();
        if (!getSecurityUtil().getCurrentToken().getUsername().equals(username)) {
            getSecurityUtil().checkAccessRight(CheckMode.ANY, AccessRight.SUPER_USER);
        }
        super.validateDataPermission(dto);
    }
}
