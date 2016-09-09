package org.trinity.repository.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AbstractAuditableEntity.class)
public abstract class AbstractAuditableEntity_ extends org.trinity.repository.entity.AbstractEntity_ {

	public static volatile SingularAttribute<AbstractAuditableEntity, Date> createdDate;
	public static volatile SingularAttribute<AbstractAuditableEntity, String> createdBy;
	public static volatile SingularAttribute<AbstractAuditableEntity, Date> lastModifiedDate;
	public static volatile SingularAttribute<AbstractAuditableEntity, String> lastModifiedBy;
	public static volatile SingularAttribute<AbstractAuditableEntity, Long> version;

}

