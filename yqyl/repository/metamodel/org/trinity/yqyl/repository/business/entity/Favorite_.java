package org.trinity.yqyl.repository.business.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.RecordStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Favorite.class)
public abstract class Favorite_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile SingularAttribute<Favorite, ServiceReceiverClient> serviceReceiverClient;
	public static volatile SingularAttribute<Favorite, Service> service;
	public static volatile SingularAttribute<Favorite, Long> id;
	public static volatile SingularAttribute<Favorite, RecordStatus> status;

}

