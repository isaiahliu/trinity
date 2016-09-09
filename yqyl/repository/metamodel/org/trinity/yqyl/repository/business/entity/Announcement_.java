package org.trinity.yqyl.repository.business.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.AnnouncementStatus;
import org.trinity.yqyl.common.message.lookup.ClientType;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Announcement.class)
public abstract class Announcement_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile SingularAttribute<Announcement, Long> clientId;
	public static volatile SingularAttribute<Announcement, ClientType> clientType;
	public static volatile SingularAttribute<Announcement, Long> id;
	public static volatile SingularAttribute<Announcement, Message> message;
	public static volatile SingularAttribute<Announcement, AnnouncementStatus> status;

}

