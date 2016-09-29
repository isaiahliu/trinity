package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderDto;
import org.trinity.yqyl.common.message.lookup.OrderStatus;
import org.trinity.yqyl.repository.business.entity.ServiceOrder;

@Component
public class ServiceOrderConverter extends AbstractLookupSupportObjectConverter<ServiceOrder, ServiceOrderDto> {
    @Override
    protected void convertBackInternal(final ServiceOrderDto source, final ServiceOrder target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getPrice, target::getPrice, target::setPrice, copyPolicy);
        copyObject(source::getServiceTime, target::getServiceTime, target::setServiceTime, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, OrderStatus.class, copyPolicy);
    }

    @Override
    protected void convertInternal(final ServiceOrder source, final ServiceOrderDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getPrice, target::getPrice, target::setPrice, copyPolicy);
        copyObject(source::getServiceTime, target::getServiceTime, target::setServiceTime, copyPolicy);
        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
    }

    @Override
    protected ServiceOrder createFromInstance() {
        return new ServiceOrder();
    }

    @Override
    protected ServiceOrderDto createToInstance() {
        return new ServiceOrderDto();
    }
}
