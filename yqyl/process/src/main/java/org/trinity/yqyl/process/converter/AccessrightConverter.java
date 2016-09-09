package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.AccessrightDto;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.repository.business.entity.Accessright;

@Component
public class AccessrightConverter extends AbstractLookupSupportObjectConverter<Accessright, AccessrightDto> {
	@Override
	protected void convertBackInternal(final AccessrightDto source, final Accessright target, final CopyPolicy copyPolicy) {
		copyObject(source::getId, target::getId, target::setId, copyPolicy);
		copyLookup(source::getName, target::getName, target::setName, AccessRight.class, copyPolicy);
		copyObject(source::getDescription, target::getDescription, target::setDescription, copyPolicy);
		copyLookup(source::getStatus, target::getStatus, target::setStatus, RecordStatus.class, copyPolicy);
	}

	@Override
	protected void convertInternal(final Accessright source, final AccessrightDto target, final CopyPolicy copyPolicy) {
		copyObject(source::getId, target::getId, target::setId, copyPolicy);
		copyMessage(source::getName, target::getName, target::setName, copyPolicy);
		copyObject(source::getDescription, target::getDescription, target::setDescription, copyPolicy);
		copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);

		for (final Accessright child : source.getChildren()) {
			target.getChildren().add(convert(child));
		}
	}

	@Override
	protected Accessright createFromInstance() {
		return new Accessright();
	}

	@Override
	protected AccessrightDto createToInstance() {
		return new AccessrightDto();
	}
}
