package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.process.converter.AbstractObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.AllowanceSupplierClientDto;
import org.trinity.yqyl.repository.business.entity.AllowanceSupplierClient;

@Component
public class AllowanceSupplierClientConverter
        extends AbstractObjectConverter<AllowanceSupplierClient, AllowanceSupplierClientDto> {

    @Override
    protected void convertBackInternal(final AllowanceSupplierClientDto source, final AllowanceSupplierClient target,
            final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
    }

    @Override
    protected void convertInternal(final AllowanceSupplierClient source, final AllowanceSupplierClientDto target,
            final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
    }

    @Override
    protected AllowanceSupplierClient createFromInstance() {
        return new AllowanceSupplierClient();
    }

    @Override
    protected AllowanceSupplierClientDto createToInstance() {
        return new AllowanceSupplierClientDto();
    }
}
