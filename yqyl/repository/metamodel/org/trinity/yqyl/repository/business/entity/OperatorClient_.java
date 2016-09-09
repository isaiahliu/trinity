package org.trinity.yqyl.repository.business.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.ClientStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OperatorClient.class)
public abstract class OperatorClient_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile SingularAttribute<OperatorClient, Long> id;
	public static volatile SingularAttribute<OperatorClient, User> user;
	public static volatile SingularAttribute<OperatorClient, ClientStatus> status;

}

