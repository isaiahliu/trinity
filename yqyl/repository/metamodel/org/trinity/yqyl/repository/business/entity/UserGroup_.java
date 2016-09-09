package org.trinity.yqyl.repository.business.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.RecordStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserGroup.class)
public abstract class UserGroup_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile ListAttribute<UserGroup, Role> roles;
	public static volatile SingularAttribute<UserGroup, String> name;
	public static volatile SingularAttribute<UserGroup, String> description;
	public static volatile SingularAttribute<UserGroup, Long> id;
	public static volatile ListAttribute<UserGroup, User> users;
	public static volatile SingularAttribute<UserGroup, RecordStatus> status;

}

