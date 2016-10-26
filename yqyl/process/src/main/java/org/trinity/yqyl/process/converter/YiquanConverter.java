package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.YiquanDto;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.repository.business.entity.Yiquan;

@Component
public class YiquanConverter extends AbstractLookupSupportObjectConverter<Yiquan, YiquanDto> {
    private static enum YiquanRelationship {
    }

    @Autowired
    public YiquanConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final YiquanDto source, final Yiquan target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getAmount, target::getAmount, target::setAmount, copyPolicy);
        copyObject(source::getCellphone, target::getCellphone, target::setCellphone, copyPolicy);
        copyObject(source::getCode, target::getCode, target::setCode, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, RecordStatus.class, copyPolicy);
    }

    @Override
    protected void convertInternal(final Yiquan source, final YiquanDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getAmount, target::getAmount, target::setAmount, copyPolicy);
        copyObject(source::getCellphone, target::getCellphone, target::setCellphone, copyPolicy);
        copyObject(source::getCode, target::getCode, target::setCode, copyPolicy);
        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
    }

    @Override
    protected void convertRelationshipInternal(final Yiquan source, final YiquanDto target,
            final RelationshipExpression relationshipExpression) {
        switch (relationshipExpression.getName(YiquanRelationship.class)) {
        default:
            break;
        }
    }

    @Override
    protected Yiquan createFromInstance() {
        return new Yiquan();
    }

    @Override
    protected YiquanDto createToInstance() {
        return new YiquanDto();
    }
}
