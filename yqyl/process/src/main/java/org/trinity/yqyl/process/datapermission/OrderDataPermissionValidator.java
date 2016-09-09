package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.Order;

@Component
public class OrderDataPermissionValidator extends AbstractDataPermissionValidator<Order> {
	@Override
	public Class<Order> getEntityType() {
		return Order.class;
	}

	@Override
	protected void validateData(final String username, final Long id) throws IException {
	}
}
