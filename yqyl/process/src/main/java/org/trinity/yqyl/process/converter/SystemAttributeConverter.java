package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.SystemAttributeDto;
import org.trinity.yqyl.repository.business.entity.SystemAttribute;

@Component
public class SystemAttributeConverter extends AbstractLookupSupportObjectConverter<SystemAttribute, SystemAttributeDto> {
@Override
protected void convertBackInternal(final SystemAttributeDto source, final SystemAttribute target, final CopyPolicy copyPolicy) {
}

@Override
protected void convertInternal(final SystemAttribute source, final SystemAttributeDto target, final CopyPolicy copyPolicy) {
}

@Override
protected SystemAttribute createFromInstance() {
return new SystemAttribute();
}

@Override
protected SystemAttributeDto createToInstance() {
return new SystemAttributeDto();
}
}
