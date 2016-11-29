package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.common.util.Tuple2;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.AccountBalanceDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientYiquanDto;
import org.trinity.yqyl.common.message.lookup.AccountCategory;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.repository.business.entity.AccountBalance;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientYiquan;

@Component
public class ServiceReceiverClientYiquanConverter
        extends AbstractLookupSupportObjectConverter<ServiceReceiverClientYiquan, ServiceReceiverClientYiquanDto> {
    private static enum ServiceReceiverClientYiquanRelationship {
        BALANCE,
        NA
    }

    @Autowired
    private IObjectConverter<AccountBalance, AccountBalanceDto> accountBalanceConverter;

    @Autowired
    public ServiceReceiverClientYiquanConverter(final IObjectConverter<Tuple2<ILookupMessage<?>, String[]>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final ServiceReceiverClientYiquanDto source, final ServiceReceiverClientYiquan target,
            final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getServiceReceiverClientId, target::setServiceReceiverClientId, copyPolicy);
        copyObject(source::getCellphone, target::getCellphone, target::setCellphone, copyPolicy);
        copyObject(source::getCode, target::getCode, target::setCode, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, RecordStatus.class, copyPolicy);
    }

    @Override
    protected void convertInternal(final ServiceReceiverClientYiquan source, final ServiceReceiverClientYiquanDto target,
            final CopyPolicy copyPolicy) {
        copyObject(source::getServiceReceiverClientId, target::getId, target::setId, copyPolicy);
        copyObject(source::getCellphone, target::getCellphone, target::setCellphone, copyPolicy);
        copyObject(source::getCode, target::getCode, target::setCode, copyPolicy);
        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
    }

    @Override
    protected void convertRelationshipInternal(final ServiceReceiverClientYiquan source, final ServiceReceiverClientYiquanDto target,
            final RelationshipExpression relationshipExpression) {
        switch (relationshipExpression.getName(ServiceReceiverClientYiquanRelationship.class)) {
        case BALANCE:
            copyRelationship(() -> {
                return source.getServiceReceiverClient().getAccount().getBalances().stream()
                        .filter(item -> item.getCategory() == AccountCategory.YIQUAN).findFirst().get();
            }, target::setBalance, accountBalanceConverter, relationshipExpression);
            break;
        case NA:
        default:
            break;
        }
    }

    @Override
    protected ServiceReceiverClientYiquan createFromInstance() {
        return new ServiceReceiverClientYiquan();
    }

    @Override
    protected ServiceReceiverClientYiquanDto createToInstance() {
        return new ServiceReceiverClientYiquanDto();
    }
}
