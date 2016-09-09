package org.trinity.yqyl.repository.business.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.OrderStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile SingularAttribute<Order, ServiceReceiverClient> serviceReceiverClient;
	public static volatile SingularAttribute<Order, BigDecimal> price;
	public static volatile SingularAttribute<Order, ServiceSupplierClient> serviceSupplierClient;
	public static volatile SingularAttribute<Order, Long> id;
	public static volatile ListAttribute<Order, Service> services;
	public static volatile SingularAttribute<Order, OrderStatus> status;

}

