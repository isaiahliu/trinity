package org.trinity.yqyl.repository.business.entity;

import java.math.BigDecimal;
import java.util.Currency;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.AccountPostingStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AccountPosting.class)
public abstract class AccountPosting_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile SingularAttribute<AccountPosting, BigDecimal> amount;
	public static volatile SingularAttribute<AccountPosting, AccountTransaction> accountTransaction;
	public static volatile SingularAttribute<AccountPosting, Currency> currency;
	public static volatile SingularAttribute<AccountPosting, Long> id;
	public static volatile SingularAttribute<AccountPosting, AccountBalance> accountBalance;
	public static volatile SingularAttribute<AccountPosting, AccountPostingStatus> status;

}

