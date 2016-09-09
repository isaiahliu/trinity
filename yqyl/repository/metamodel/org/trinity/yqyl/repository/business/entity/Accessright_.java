package org.trinity.yqyl.repository.business.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.RecordStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Accessright.class)
public abstract class Accessright_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile SingularAttribute<Accessright, Accessright> parent;
	public static volatile ListAttribute<Accessright, Accessright> children;
	public static volatile ListAttribute<Accessright, Role> roles;
	public static volatile SingularAttribute<Accessright, AccessRight> name;
	public static volatile SingularAttribute<Accessright, String> description;
	public static volatile SingularAttribute<Accessright, Long> id;
	public static volatile SingularAttribute<Accessright, RecordStatus> status;

}

