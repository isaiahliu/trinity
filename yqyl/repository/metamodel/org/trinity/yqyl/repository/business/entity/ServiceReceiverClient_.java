package org.trinity.yqyl.repository.business.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.Gender;
import org.trinity.yqyl.common.message.lookup.ServiceReceiverClientStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ServiceReceiverClient.class)
public abstract class ServiceReceiverClient_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile ListAttribute<ServiceReceiverClient, Favorite> favorites;
	public static volatile SingularAttribute<ServiceReceiverClient, String> address;
	public static volatile SingularAttribute<ServiceReceiverClient, Gender> gender;
	public static volatile SingularAttribute<ServiceReceiverClient, String> cellphoneNo;
	public static volatile SingularAttribute<ServiceReceiverClient, String> identityCard;
	public static volatile SingularAttribute<ServiceReceiverClient, String> homephoneNo;
	public static volatile SingularAttribute<ServiceReceiverClient, String> yijinCode;
	public static volatile SingularAttribute<ServiceReceiverClient, Date> dob;
	public static volatile SingularAttribute<ServiceReceiverClient, String> name;
	public static volatile ListAttribute<ServiceReceiverClient, Order> orders;
	public static volatile SingularAttribute<ServiceReceiverClient, Long> id;
	public static volatile SingularAttribute<ServiceReceiverClient, User> user;
	public static volatile SingularAttribute<ServiceReceiverClient, String> email;
	public static volatile SingularAttribute<ServiceReceiverClient, ServiceReceiverClientStatus> status;

}

