package org.trinity.yqyl.repository.business.entity;

import java.math.BigDecimal;
import java.util.Currency;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.AccountBalanceCategory;
import org.trinity.yqyl.common.message.lookup.AccountBalanceStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AccountBalance.class)
public abstract class AccountBalance_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile SingularAttribute<AccountBalance, BigDecimal> amount;
	public static volatile SingularAttribute<AccountBalance, Currency> currency;
	public static volatile ListAttribute<AccountBalance, AccountPosting> accountPostings;
	public static volatile SingularAttribute<AccountBalance, Long> id;
	public static volatile SingularAttribute<AccountBalance, AccountBalanceCategory> category;
	public static volatile SingularAttribute<AccountBalance, Account> account;
	public static volatile SingularAttribute<AccountBalance, AccountBalanceStatus> status;

}

