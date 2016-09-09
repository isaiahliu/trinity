package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.OrderDto;
import org.trinity.yqyl.repository.business.entity.Order;

@Component
public class OrderConverter extends AbstractLookupSupportObjectConverter<Order, OrderDto> {
@Override
protected void convertBackInternal(final OrderDto source, final Order target, final CopyPolicy copyPolicy) {
}

@Override
protected void convertInternal(final Order source, final OrderDto target, final CopyPolicy copyPolicy) {
}

@Override
protected Order createFromInstance() {
return new Order();
}

@Override
protected OrderDto createToInstance() {
return new OrderDto();
}
}
