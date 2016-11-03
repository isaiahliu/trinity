package org.trinity.yqyl.repository.business.dataaccess;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.trinity.common.dto.object.ISearchingDto;
import org.trinity.message.LookupParser;
import org.trinity.repository.repository.IJpaRepository;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderSearchingDto;
import org.trinity.yqyl.common.message.lookup.OrderStatus;
import org.trinity.yqyl.repository.business.entity.ServiceCategory_;
import org.trinity.yqyl.repository.business.entity.ServiceInfo_;
import org.trinity.yqyl.repository.business.entity.ServiceOrder;
import org.trinity.yqyl.repository.business.entity.ServiceOrder_;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient_;
import org.trinity.yqyl.repository.business.entity.User_;

public interface IServiceOrderRepository extends IJpaRepository<ServiceOrder, ServiceOrderSearchingDto> {
    @Override
    default Page<ServiceOrder> query(final ServiceOrderSearchingDto dto, final Pageable pagable) {
        final Specification<ServiceOrder> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            switch (dto.getSearchScope()) {
            case ISearchingDto.SEARCH_ALL:
                break;
            case "supplier":
                predicates.add(cb.equal(root.join(ServiceOrder_.serviceInfo).join(ServiceInfo_.serviceSupplierClient)
                        .join(ServiceSupplierClient_.user).get(User_.username), dto.getCurrentUsername()));
                break;
            case "me":
            default:
                predicates.add(cb.equal(root.join(ServiceOrder_.user).get(User_.username), dto.getCurrentUsername()));
                break;
            }

            if (!StringUtils.isEmpty(dto.getReceiverUserName())) {
                predicates.add(cb.like(root.join(ServiceOrder_.user).get(User_.username), "%" + dto.getReceiverUserName() + "%"));
            }

            if (dto.getId() != null && dto.getId() > 0) {
                predicates.add(cb.equal(root.get(ServiceOrder_.id), dto.getId()));
            }

            if (!StringUtils.isEmpty(dto.getCategory())) {
                predicates.add(cb.equal(root.join(ServiceOrder_.serviceInfo).join(ServiceInfo_.serviceCategory).get(ServiceCategory_.name),
                        dto.getCategory()));
            }

            if (!dto.getStatus().isEmpty()) {
                final In<OrderStatus> in = cb.in(root.get(ServiceOrder_.status));
                dto.getStatus().forEach(item -> in.value(LookupParser.parse(OrderStatus.class, item)));
                predicates.add(in);
            }

            if (dto.getServiceSupplierClientId() != null) {
                predicates.add(cb.equal(
                        root.join(ServiceOrder_.serviceInfo).join(ServiceInfo_.serviceSupplierClient).get(ServiceSupplierClient_.userId),
                        dto.getServiceSupplierClientId()));

                query.distinct(true);
            }

            if (!StringUtils.isEmpty(dto.getSupplierUserName())) {
                predicates.add(cb.like(root.join(ServiceOrder_.serviceInfo).join(ServiceInfo_.serviceSupplierClient)
                        .join(ServiceSupplierClient_.user).get(User_.username), "%" + dto.getSupplierUserName() + "%"));
            }

            if (!StringUtils.isEmpty(dto.getServiceDate())) {
                final DateFormat format = new SimpleDateFormat("yyyyMMdd");
                try {
                    final Calendar calendar = Calendar.getInstance();
                    calendar.setTime(format.parse(dto.getServiceDate()));
                    predicates.add(cb.greaterThanOrEqualTo(root.get(ServiceOrder_.serviceTime), calendar.getTime()));

                    calendar.add(Calendar.DATE, 1);
                    predicates.add(cb.lessThan(root.get(ServiceOrder_.serviceTime), calendar.getTime()));
                } catch (final ParseException e) {
                }
            }

            if (!StringUtils.isEmpty(dto.getSettledDate())) {
                final DateFormat format = new SimpleDateFormat("yyyyMMdd");
                try {
                    final Calendar calendar = Calendar.getInstance();
                    calendar.setTime(format.parse(dto.getSettledDate()));
                    predicates.add(cb.greaterThanOrEqualTo(root.get(ServiceOrder_.settledTime), calendar.getTime()));

                    calendar.add(Calendar.DATE, 1);
                    predicates.add(cb.lessThan(root.get(ServiceOrder_.settledTime), calendar.getTime()));
                } catch (final ParseException e) {
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return findAll(specification, pagable);
    }
}
