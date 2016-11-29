package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.common.util.Tuple2;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.AccountDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientDto;
import org.trinity.yqyl.common.message.lookup.FamilyRelationship;
import org.trinity.yqyl.common.message.lookup.Gender;
import org.trinity.yqyl.common.message.lookup.ServiceReceiverClientStatus;
import org.trinity.yqyl.repository.business.entity.Account;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClient;

@Component
public class ServiceReceiverClientConverter extends AbstractLookupSupportObjectConverter<ServiceReceiverClient, ServiceReceiverClientDto> {
    private static enum ServiceReceiverClientRelationship {
        ACCOUNT,
        NA
    }

    private static final String DATE_FORMAT = "yyyy/MM/dd";
    @Autowired
    private IObjectConverter<Account, AccountDto> accountConverter;

    @Autowired
    public ServiceReceiverClientConverter(final IObjectConverter<Tuple2<ILookupMessage<?>, String[]>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final ServiceReceiverClientDto source, final ServiceReceiverClient target,
            final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, ServiceReceiverClientStatus.class, copyPolicy);
        copyLookup(source::getFamilyRelationship, target::getFamilyRelationship, target::setFamilyRelationship, FamilyRelationship.class,
                copyPolicy);
        copyLookup(source::getGender, target::getGender, target::setGender, Gender.class, copyPolicy);
        copyObject(source::getAddress, target::getAddress, target::setAddress, copyPolicy);
        copyObject(source::getCellphoneNo, target::getCellphoneNo, target::setCellphoneNo, copyPolicy);
        copyDateString(source::getDob, target::getDob, target::setDob, DATE_FORMAT, copyPolicy);
        copyObject(source::getEmail, target::getEmail, target::setEmail, copyPolicy);
        copyObject(source::getHomephoneNo, target::getHomephoneNo, target::setHomephoneNo, copyPolicy);
        copyObject(source::getIdentityCard, target::getIdentityCard, target::setIdentityCard, copyPolicy);
        copyObject(source::getIdentityCardCopy, target::getIdentityCardCopy, target::setIdentityCardCopy, copyPolicy);
        copyObject(source::getName, target::getName, target::setName, copyPolicy);
    }

    @Override
    protected void convertInternal(final ServiceReceiverClient source, final ServiceReceiverClientDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
        copyMessage(source::getGender, target::getGender, target::setGender, copyPolicy);
        copyMessage(source::getFamilyRelationship, target::getFamilyRelationship, target::setFamilyRelationship, copyPolicy);
        copyObject(source::getAddress, target::getAddress, target::setAddress, copyPolicy);
        copyObject(source::getCellphoneNo, target::getCellphoneNo, target::setCellphoneNo, copyPolicy);
        copyDate(source::getDob, target::getDob, target::setDob, DATE_FORMAT, copyPolicy);
        copyObject(source::getEmail, target::getEmail, target::setEmail, copyPolicy);
        copyObject(source::getHomephoneNo, target::getHomephoneNo, target::setHomephoneNo, copyPolicy);
        copyObject(source::getIdentityCard, target::getIdentityCard, target::setIdentityCard, copyPolicy);
        copyObject(source::getIdentityCardCopy, target::getIdentityCardCopy, target::setIdentityCardCopy, copyPolicy);
        copyObject(source::getName, target::getName, target::setName, copyPolicy);
    }

    @Override
    protected void convertRelationshipInternal(final ServiceReceiverClient source, final ServiceReceiverClientDto target,
            final RelationshipExpression relationshipExpression) {
        switch (relationshipExpression.getName(ServiceReceiverClientRelationship.class)) {
        case ACCOUNT:
            copyRelationship(source::getAccount, target::setAccount, accountConverter, relationshipExpression);
            break;
        case NA:
        default:
            break;
        }
    }

    @Override
    protected ServiceReceiverClient createFromInstance() {
        return new ServiceReceiverClient();
    }

    @Override
    protected ServiceReceiverClientDto createToInstance() {
        return new ServiceReceiverClientDto();
    }
}
