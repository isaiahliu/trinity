package org.trinity.yqyl.repository.business.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.UserStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile ListAttribute<User, AllowanceSupplierClient> allowanceSupplierClients;
	public static volatile ListAttribute<User, ServiceSupplierClient> serviceSupplierClients;
	public static volatile ListAttribute<User, ServiceReceiverClient> serviceReceiverClients;
	public static volatile ListAttribute<User, UserGroup> userGroups;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> cellphone;
	public static volatile ListAttribute<User, OperatorClient> operatorClients;
	public static volatile ListAttribute<User, Token> tokens;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile ListAttribute<User, Account> accounts;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, UserStatus> status;
	public static volatile SingularAttribute<User, String> username;

}

