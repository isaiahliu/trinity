package org.trinity.yqyl.repository.business.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.RecordStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Role.class)
public abstract class Role_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile ListAttribute<Role, Accessright> accessrights;
	public static volatile ListAttribute<Role, UserGroup> userGroups;
	public static volatile SingularAttribute<Role, String> name;
	public static volatile SingularAttribute<Role, String> description;
	public static volatile SingularAttribute<Role, Long> id;
	public static volatile SingularAttribute<Role, RecordStatus> status;

}

