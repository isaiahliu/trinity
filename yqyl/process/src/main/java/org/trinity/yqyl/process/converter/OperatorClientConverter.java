package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.OperatorClientDto;
import org.trinity.yqyl.common.message.lookup.OperatorClientStatus;
import org.trinity.yqyl.repository.business.entity.OperatorClient;

@Component
public class OperatorClientConverter extends AbstractLookupSupportObjectConverter<OperatorClient, OperatorClientDto> {

    @Override
    protected void convertBackInternal(final OperatorClientDto source, final OperatorClient target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, OperatorClientStatus.class, copyPolicy);
        copyObject(source::getName, target::getName, target::setName, copyPolicy);
        copyObject(source::getStaffNo, target::getStaffNo, target::setStaffNo, copyPolicy);
    }

    @Override
    protected void convertInternal(final OperatorClient source, final OperatorClientDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
        copyObject(source::getName, target::getName, target::setName, copyPolicy);
        copyObject(source::getStaffNo, target::getStaffNo, target::setStaffNo, copyPolicy);
        copyObject(() -> source.getUser().getUsername(), target::getUsername, target::setUsername, copyPolicy);
    }

    @Override
    protected OperatorClient createFromInstance() {
        return new OperatorClient();
    }

    @Override
    protected OperatorClientDto createToInstance() {
        return new OperatorClientDto();
    }
}
