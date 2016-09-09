package org.trinity.yqyl.repository.business.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.ClientStatus;
import org.trinity.yqyl.common.message.lookup.PersonalType;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AllowanceSupplierClient.class)
public abstract class AllowanceSupplierClient_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile SingularAttribute<AllowanceSupplierClient, Long> id;
	public static volatile SingularAttribute<AllowanceSupplierClient, PersonalType> type;
	public static volatile SingularAttribute<AllowanceSupplierClient, User> user;
	public static volatile SingularAttribute<AllowanceSupplierClient, ClientStatus> status;

}

