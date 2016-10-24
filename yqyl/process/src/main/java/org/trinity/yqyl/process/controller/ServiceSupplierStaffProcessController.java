package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.trinity.common.accessright.ISecurityUtil.CheckMode;
import org.trinity.common.exception.IException;
import org.trinity.message.LookupParser;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.ServiceCategoryDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierStaffDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierStaffSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.StaffStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceSupplierStaffProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceSupplierStaffRepository;
import org.trinity.yqyl.repository.business.entity.ServiceCategory;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient_;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierStaff;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierStaff_;
import org.trinity.yqyl.repository.business.entity.User_;

@Service
public class ServiceSupplierStaffProcessController extends
        AbstractAutowiredCrudProcessController<ServiceSupplierStaff, ServiceSupplierStaffDto, ServiceSupplierStaffSearchingDto, IServiceSupplierStaffRepository>
        implements IServiceSupplierStaffProcessController {
    @Autowired
    private IObjectConverter<ServiceCategory, ServiceCategoryDto> serviceCategoryConverter;

    public ServiceSupplierStaffProcessController() {
        super(ServiceSupplierStaff.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_SUPPLIER_STAFF);
    }

    @Override
    public Page<ServiceSupplierStaffDto> getAll(final ServiceSupplierStaffSearchingDto searchingData) throws IException {
        final String username = getSecurityUtil().getCurrentToken().getUsername();
        final Specification<ServiceSupplierStaff> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (!searchingData.isSearchAll()) {
                predicates.add(cb.equal(
                        root.join(ServiceSupplierStaff_.serviceSupplierClient).join(ServiceSupplierClient_.user).get(User_.username),
                        username));
            }

            if (!StringUtils.isEmpty(searchingData.getName())) {
                predicates.add(cb.like(root.get(ServiceSupplierStaff_.name), "%" + searchingData.getName() + "%"));
            }

            if (!StringUtils.isEmpty(searchingData.getStatus())) {
                final StaffStatus status = LookupParser.parse(StaffStatus.class, searchingData.getStatus());
                if (status != null) {
                    predicates.add(cb.equal(root.get(ServiceSupplierStaff_.status), status));
                }
            }

            if (searchingData.getId() != null && searchingData.getId() != 0) {
                predicates.add(cb.equal(root.get(ServiceSupplierStaff_.id), searchingData.getId()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return getDomainEntityRepository().findAll(specification, getPagingConverter().convert(searchingData)).map(item -> {
            final ServiceSupplierStaffDto dto = getDomainObjectConverter().convert(item);
            dto.setServiceCategories(serviceCategoryConverter.convert(item.getServiceCategories()));
            return dto;
        });
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
