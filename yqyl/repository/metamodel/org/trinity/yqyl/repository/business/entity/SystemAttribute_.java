package org.trinity.yqyl.repository.business.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.common.message.lookup.SystemAttributeKey;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SystemAttribute.class)
public abstract class SystemAttribute_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile SingularAttribute<SystemAttribute, String> valueType;
	public static volatile SingularAttribute<SystemAttribute, String> format;
	public static volatile SingularAttribute<SystemAttribute, Long> id;
	public static volatile SingularAttribute<SystemAttribute, String> value;
	public static volatile SingularAttribute<SystemAttribute, SystemAttributeKey> key;
	public static volatile SingularAttribute<SystemAttribute, RecordStatus> status;

}

