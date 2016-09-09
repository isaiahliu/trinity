package org.trinity.yqyl.repository.business.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.TokenStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Token.class)
public abstract class Token_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile SingularAttribute<Token, Date> activeTimestamp;
	public static volatile SingularAttribute<Token, String> deviceIdentity;
	public static volatile SingularAttribute<Token, Long> id;
	public static volatile SingularAttribute<Token, User> user;
	public static volatile SingularAttribute<Token, Date> lastActiveTimestamp;
	public static volatile SingularAttribute<Token, TokenStatus> status;
	public static volatile SingularAttribute<Token, String> token;

}

