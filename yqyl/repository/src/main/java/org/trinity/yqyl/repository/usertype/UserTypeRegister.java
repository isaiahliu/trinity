package org.trinity.yqyl.repository.usertype;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.trinity.repository.type.EnumMessageType;
import org.trinity.yqyl.common.message.lookup.TokenStatus;
import org.trinity.yqyl.common.message.lookup.UserStatus;
import org.trinity.yqyl.repository.business.entity.Accessright;

@TypeDefs({
		@TypeDef(name = "UserStatus", defaultForType = UserStatus.class, typeClass = EnumMessageType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.UserStatus")),
		@TypeDef(name = "TokenStatus", defaultForType = TokenStatus.class, typeClass = EnumMessageType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.TokenStatus")),
		@TypeDef(name = "AccessRight", defaultForType = Accessright.class, typeClass = EnumMessageType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.AccessRight")) })
@MappedSuperclass
public class UserTypeRegister {

}
