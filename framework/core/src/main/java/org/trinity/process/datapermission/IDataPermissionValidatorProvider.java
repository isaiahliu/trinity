package org.trinity.process.datapermission;

public interface IDataPermissionValidatorProvider {
    IDataPermissionValidator<?> getValidator(final Class<?> entityType);

    void registerDataPermissionValidator(Class<?> entityType, IDataPermissionValidator<?> validator);
}
