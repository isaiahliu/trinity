package org.trinity.yqyl.repository.business.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.trinity.yqyl.common.message.lookup.Language;
import org.trinity.yqyl.common.message.lookup.LookupType;
import org.trinity.yqyl.common.message.lookup.RecordStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Lookup.class)
public abstract class Lookup_ extends org.trinity.repository.entity.AbstractAuditableEntity_ {

	public static volatile SingularAttribute<Lookup, String> code;
	public static volatile SingularAttribute<Lookup, String> description;
	public static volatile SingularAttribute<Lookup, Language> language;
	public static volatile SingularAttribute<Lookup, Long> id;
	public static volatile SingularAttribute<Lookup, LookupType> category;
	public static volatile SingularAttribute<Lookup, RecordStatus> status;

}

