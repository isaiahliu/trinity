package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.ServiceCategoryDto;
import org.trinity.yqyl.repository.business.entity.ServiceCategory;

@Component
public class ServiceCategoryConverter extends AbstractLookupSupportObjectConverter<ServiceCategory, ServiceCategoryDto> {
@Override
protected void convertBackInternal(final ServiceCategoryDto source, final ServiceCategory target, final CopyPolicy copyPolicy) {
}

@Override
protected void convertInternal(final ServiceCategory source, final ServiceCategoryDto target, final CopyPolicy copyPolicy) {
}

@Override
protected ServiceCategory createFromInstance() {
return new ServiceCategory();
}

@Override
protected ServiceCategoryDto createToInstance() {
return new ServiceCategoryDto();
}
}
