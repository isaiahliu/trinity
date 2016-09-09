package org.trinity.yqyl.process.datapermission;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.trinity.common.accessright.ITokenAwareAuthentication;
import org.trinity.common.exception.IException;
import org.trinity.process.datapermission.IDataPermissionValidator;
import org.trinity.process.datapermission.IDataPermissionValidatorProvider;

public abstract class AbstractDataPermissionValidator<T> implements IDataPermissionValidator<T>, ApplicationContextAware {
	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		applicationContext.getBean(IDataPermissionValidatorProvider.class).registerDataPermissionValidator(getEntityType(), this);
	}

	@Override
	public <Dto> void validate(final List<Dto> data, final Function<Dto, Long> idGetter) throws IException {
		for (final Dto item : data) {
			validate(idGetter.apply(item));
		}
	}

	@Override
	public final void validate(final Long id) throws IException {
		try {
			checkSpecialPermission();
		} catch (final IException e) {
			validateData(getCurrentUser(), id);
		}
	}

	protected void checkSpecialPermission() throws IException {
	}

	protected String getCurrentUser() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return ((ITokenAwareAuthentication) authentication).getToken().getUsername();
	}

	protected abstract void validateData(String username, Long id) throws IException;
}
