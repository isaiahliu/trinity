package org.trinity.yqyl.repository.usertype;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.trinity.repository.type.MessageUserType;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.AccountBalanceCategory;
import org.trinity.yqyl.common.message.lookup.AccountBalanceStatus;
import org.trinity.yqyl.common.message.lookup.AccountPostingStatus;
import org.trinity.yqyl.common.message.lookup.AccountStatus;
import org.trinity.yqyl.common.message.lookup.AnnouncementStatus;
import org.trinity.yqyl.common.message.lookup.ClientType;
import org.trinity.yqyl.common.message.lookup.CredentialType;
import org.trinity.yqyl.common.message.lookup.Currency;
import org.trinity.yqyl.common.message.lookup.FamilyRelationship;
import org.trinity.yqyl.common.message.lookup.FavoriteCategory;
import org.trinity.yqyl.common.message.lookup.FlagStatus;
import org.trinity.yqyl.common.message.lookup.FrequencyStatus;
import org.trinity.yqyl.common.message.lookup.Gender;
import org.trinity.yqyl.common.message.lookup.Language;
import org.trinity.yqyl.common.message.lookup.LookupType;
import org.trinity.yqyl.common.message.lookup.MessageStatus;
import org.trinity.yqyl.common.message.lookup.OperatorClientStatus;
import org.trinity.yqyl.common.message.lookup.OrderStatus;
import org.trinity.yqyl.common.message.lookup.PersonalType;
import org.trinity.yqyl.common.message.lookup.RealnameStatus;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.common.message.lookup.RoleName;
import org.trinity.yqyl.common.message.lookup.ServiceReceiverClientStatus;
import org.trinity.yqyl.common.message.lookup.ServiceStatus;
import org.trinity.yqyl.common.message.lookup.ServiceSupplierClientStatus;
import org.trinity.yqyl.common.message.lookup.SmokerAge;
import org.trinity.yqyl.common.message.lookup.SystemAttributeKey;
import org.trinity.yqyl.common.message.lookup.TokenStatus;
import org.trinity.yqyl.common.message.lookup.TransactionCategory;
import org.trinity.yqyl.common.message.lookup.UserStatus;
import org.trinity.yqyl.common.message.lookup.ValueType;

@TypeDefs({
        @TypeDef(name = "CredentialType", defaultForType = CredentialType.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.CredentialType")),
        @TypeDef(name = "RealnameStatus", defaultForType = RealnameStatus.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.RealnameStatus")),
        @TypeDef(name = "FlagStatus", defaultForType = FlagStatus.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.FlagStatus")),
        @TypeDef(name = "FrequencyStatus", defaultForType = FrequencyStatus.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.FrequencyStatus")),
        @TypeDef(name = "SmokerAge", defaultForType = SmokerAge.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.SmokerAge")),
        @TypeDef(name = "RecordStatus", defaultForType = RecordStatus.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.RecordStatus")),
        @TypeDef(name = "Gender", defaultForType = Gender.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.Gender")),
        @TypeDef(name = "AccountBalanceCategory", defaultForType = AccountBalanceCategory.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.AccountBalanceCategory")),
        @TypeDef(name = "AccountBalanceStatus", defaultForType = AccountBalanceStatus.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.AccountBalanceStatus")),
        @TypeDef(name = "AccountPostingStatus", defaultForType = AccountPostingStatus.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.AccountPostingStatus")),
        @TypeDef(name = "AccountStatus", defaultForType = AccountStatus.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.AccountStatus")),
        @TypeDef(name = "AnnouncementStatus", defaultForType = AnnouncementStatus.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.AnnouncementStatus")),
        @TypeDef(name = "OperatorClientStatus", defaultForType = OperatorClientStatus.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.OperatorClientStatus")),
        @TypeDef(name = "ClientType", defaultForType = ClientType.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.ClientType")),
        @TypeDef(name = "Currency", defaultForType = Currency.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.Currency")),
        @TypeDef(name = "Language", defaultForType = Language.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.Language")),
        @TypeDef(name = "MessageStatus", defaultForType = MessageStatus.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.MessageStatus")),
        @TypeDef(name = "OrderStatus", defaultForType = OrderStatus.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.OrderStatus")),
        @TypeDef(name = "PersonalType", defaultForType = PersonalType.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.PersonalType")),
        @TypeDef(name = "ServiceStatus", defaultForType = ServiceStatus.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.ServiceStatus")),
        @TypeDef(name = "ServiceSupplierClientStatus", defaultForType = ServiceSupplierClientStatus.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.ServiceSupplierClientStatus")),
        @TypeDef(name = "ServiceReceiverClientStatus", defaultForType = ServiceReceiverClientStatus.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.ServiceReceiverClientStatus")),
        @TypeDef(name = "TransactionCategory", defaultForType = TransactionCategory.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.TransactionCategory")),
        @TypeDef(name = "LookupType", defaultForType = LookupType.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.LookupType")),
        @TypeDef(name = "SystemAttributeKey", defaultForType = SystemAttributeKey.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.SystemAttributeKey")),
        @TypeDef(name = "ValueType", defaultForType = ValueType.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.ValueType")),
        @TypeDef(name = "UserStatus", defaultForType = UserStatus.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.UserStatus")),
        @TypeDef(name = "FamilyRelationship", defaultForType = FamilyRelationship.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.FamilyRelationship")),
        @TypeDef(name = "TokenStatus", defaultForType = TokenStatus.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.TokenStatus")),
        @TypeDef(name = "FavoriteCategory", defaultForType = FavoriteCategory.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.FavoriteCategory")),
        @TypeDef(name = "RoleName", defaultForType = RoleName.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.RoleName")),
        @TypeDef(name = "AccessRight", defaultForType = AccessRight.class, typeClass = MessageUserType.class, parameters = @Parameter(name = "class", value = "org.trinity.yqyl.common.message.lookup.AccessRight")) })
@MappedSuperclass
public class UserTypeRegister {

}
