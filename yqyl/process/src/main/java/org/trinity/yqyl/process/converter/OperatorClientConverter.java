package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.OperatorClientDto;
import org.trinity.yqyl.repository.business.entity.OperatorClient;

@Component
public class OperatorClientConverter extends AbstractLookupSupportObjectConverter<OperatorClient, OperatorClientDto> {

    @Override
    protected void convertBackInternal(final OperatorClientDto source, final OperatorClient target,
            final CopyPolicy copyPolicy) {

    }

    @Override
    protected void convertInternal(final OperatorClient source, final OperatorClientDto target,
            final CopyPolicy copyPolicy) {

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
