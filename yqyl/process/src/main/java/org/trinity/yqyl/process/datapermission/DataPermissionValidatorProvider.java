package org.trinity.yqyl.process.datapermission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.process.datapermission.IDataPermissionValidator;
import org.trinity.process.datapermission.IDataPermissionValidatorProvider;

@Component
public class DataPermissionValidatorProvider implements IDataPermissionValidatorProvider {
    private static class DefaultDataPermissionValidator implements IDataPermissionValidator<Void> {

        @Override
        public Class<Void> getEntityType() {
            return Void.class;
        }

        @Override
        public <Dto> void validate(final List<Dto> data, final Function<Dto, Long> idGetter) throws IException {
        }

        @Override
        public void validate(final Long id) throws IException {
        }
    }

    private static IDataPermissionValidator<?> defaultValidator;

    private static IDataPermissionValidator<?> getDefaultValidator() {
        if (defaultValidator == null) {
            defaultValidator = new DefaultDataPermissionValidator();
        }
        return defaultValidator;
    }

    private final Map<Class<?>, IDataPermissionValidator<?>> map;

    public DataPermissionValidatorProvider() {
        map = new HashMap<>();
    }

    @Override
    public IDataPermissionValidator<?> getValidator(final Class<?> entityType) {
        final IDataPermissionValidator<?> dataPermissionValidator = map.get(entityType);
        if (dataPermissionValidator == null) {
            return getDefaultValidator();
        }
        return dataPermissionValidator;
    }

    @Override
    public void registerDataPermissionValidator(final Class<?> entityType,
            final IDataPermissionValidator<?> validator) {
        map.put(entityType, validator);
    }

}
