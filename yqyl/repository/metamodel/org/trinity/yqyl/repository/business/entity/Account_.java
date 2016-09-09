package org.trinity.yqyl.repository.business.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.AccountStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Account.class)
public abstract class Account_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile ListAttribute<Account, AccountBalance> accountBalances;
	public static volatile SingularAttribute<Account, Long> id;
	public static volatile SingularAttribute<Account, User> user;
	public static volatile SingularAttribute<Account, AccountStatus> status;

}

