package org.trinity.yqyl.process.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trinity.common.accessright.ISecurityUtil.CheckMode;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderAppraiseDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderAppraiseSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.OrderStatus;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceOrderAppraiseProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceOrderAppraiseRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceOrderRepository;
import org.trinity.yqyl.repository.business.entity.ServiceOrder;
import org.trinity.yqyl.repository.business.entity.ServiceOrderAppraise;

@Service
public class ServiceOrderAppraiseProcessController extends
        AbstractAutowiredCrudProcessController<ServiceOrderAppraise, ServiceOrderAppraiseDto, ServiceOrderAppraiseSearchingDto, IServiceOrderAppraiseRepository>
        implements IServiceOrderAppraiseProcessController {
    @Autowired
    private IServiceOrderRepository serviceOrderRepository;

    @Override
    @Transactional
    public List<ServiceOrderAppraiseDto> addAll(final List<ServiceOrderAppraiseDto> data) throws IException {
        for (final ServiceOrderAppraiseDto dto : data) {
            final ServiceOrder serviceOrder = serviceOrderRepository.findOne(dto.getId());

            if (!serviceOrder.getUser().getUsername().equals(getSecurityUtil().getCurrentToken().getUsername())) {
                throw getExceptionFactory().createException(ErrorMessage.INSUFFICIENT_ACCESSRIGHT);
            }

            if (serviceOrder.getStatus() != OrderStatus.AWAITING_APPRAISE) {
                throw getExceptionFactory().createException(ErrorMessage.INCORRECT_SERVICE_ORDER_STATUS);
            }

            final ServiceOrderAppraise serviceOrderAppraise = getDomainObjectConverter().convertBack(dto);
            serviceOrderAppraise.setStatus(RecordStatus.ACTIVE);

            serviceOrder.setStatus(OrderStatus.SETTLED);

            getDomainEntityRepository().save(serviceOrderAppraise);
            serviceOrderRepository.save(serviceOrder);
        }

        return data;
    }

    @Override
    protected boolean canAccessAllStatus() {
        return getSecurityUtil().hasAccessRight(CheckMode.ANY, AccessRight.ADMINISTRATOR, AccessRight.SERVICE_SUPPLIER);
    }
}
