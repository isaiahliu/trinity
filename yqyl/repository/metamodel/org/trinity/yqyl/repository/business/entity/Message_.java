package org.trinity.yqyl.repository.business.entity;

import java.awt.TrayIcon.MessageType;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.MessageStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Message.class)
public abstract class Message_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile SingularAttribute<Message, Long> id;
	public static volatile SingularAttribute<Message, MessageType> type;
	public static volatile ListAttribute<Message, Announcement> announcements;
	public static volatile SingularAttribute<Message, String> content;
	public static volatile SingularAttribute<Message, MessageStatus> status;

}

