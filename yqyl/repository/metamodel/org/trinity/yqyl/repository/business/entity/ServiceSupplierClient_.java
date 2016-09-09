package org.trinity.yqyl.repository.business.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.PersonalType;
import org.trinity.yqyl.common.message.lookup.ServiceSupplierClientStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ServiceSupplierClient.class)
public abstract class ServiceSupplierClient_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile SingularAttribute<ServiceSupplierClient, String> licenseCopy;
	public static volatile SingularAttribute<ServiceSupplierClient, String> identityCopy;
	public static volatile SingularAttribute<ServiceSupplierClient, String> identity;
	public static volatile SingularAttribute<ServiceSupplierClient, String> name;
	public static volatile SingularAttribute<ServiceSupplierClient, Long> id;
	public static volatile SingularAttribute<ServiceSupplierClient, PersonalType> type;
	public static volatile SingularAttribute<ServiceSupplierClient, User> user;
	public static volatile SingularAttribute<ServiceSupplierClient, String> email;
	public static volatile SingularAttribute<ServiceSupplierClient, ServiceSupplierClientStatus> status;

}

