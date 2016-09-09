package org.trinity.yqyl.repository.business.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.common.message.lookup.TransactionCategory;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AccountTransaction.class)
public abstract class AccountTransaction_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile ListAttribute<AccountTransaction, AccountPosting> accountPostings;
	public static volatile SingularAttribute<AccountTransaction, Long> id;
	public static volatile SingularAttribute<AccountTransaction, TransactionCategory> category;
	public static volatile SingularAttribute<AccountTransaction, RecordStatus> status;

}

