package org.trinity.yqyl.repository.business.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.ServiceStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Service.class)
public abstract class Service_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile ListAttribute<Service, Favorite> favorites;
	public static volatile SingularAttribute<Service, BigDecimal> price;
	public static volatile SingularAttribute<Service, Service> service;
	public static volatile SingularAttribute<Service, String> name;
	public static volatile SingularAttribute<Service, String> description;
	public static volatile ListAttribute<Service, Order> orders;
	public static volatile SingularAttribute<Service, Long> id;
	public static volatile ListAttribute<Service, Service> services;
	public static volatile SingularAttribute<Service, ServiceStatus> status;

}

