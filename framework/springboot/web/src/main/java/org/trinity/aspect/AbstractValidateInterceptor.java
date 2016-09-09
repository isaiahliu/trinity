package org.trinity.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.trinity.common.dto.ValidationResult;
import org.trinity.common.dto.validator.OnValid;

public abstract class AbstractValidateInterceptor {
	private final LocalValidatorFactoryBean validator;

	protected AbstractValidateInterceptor(final LocalValidatorFactoryBean validator) {
		this.validator = validator;
	}

	public abstract void findValidateAnnotation();

	public void validate(final JoinPoint jp) throws ConstraintViolationException {
		final Signature signature = jp.getSignature();
		final Object[] args = jp.getArgs();
		final MethodSignature methodSignature = (MethodSignature) signature;
		final Method targetMethod = methodSignature.getMethod();
		final Set<ConstraintViolation<?>> result = new HashSet<ConstraintViolation<?>>();
		final Annotation[][] paraAnnotations = targetMethod.getParameterAnnotations();
		final Class<?>[] parameterTypes = methodSignature.getParameterTypes();

		if (paraAnnotations != null && paraAnnotations.length > 0) {
			for (int i = 0; i < paraAnnotations.length; i++) {
				final int paraAnnotationLength = paraAnnotations[i].length;

				if (paraAnnotationLength == 0) {
					continue;
				}
				for (int j = 0; j < paraAnnotationLength; j++) {
					if (paraAnnotations[i][j] instanceof OnValid) {
						final OnValid validated = (OnValid) paraAnnotations[i][j];
						final Class<?>[] groups = validated.value();
						final Object validatedObj = args[i];
						executeValidate(validatedObj, groups, result);
						break;
					}
				}
			}
		}
		ConstraintViolationException error = null;
		if (!result.isEmpty()) {
			error = new ConstraintViolationException(result);
		}

		for (int index = 0; index < parameterTypes.length; index++) {
			if (parameterTypes[index] == ValidationResult.class) {
				final ValidationResult validationResult = new ValidationResult();

				validationResult.setException(error);

				args[index] = validationResult;

				return;
			}
		}

		if (error != null) {
			throw error;
		}
	}

	private void executeValidate(final Object validatedObj, final Class<?>[] groups, final Set<ConstraintViolation<?>> result) {
		if (validatedObj == null) {
			return;
		}

		final Set<ConstraintViolation<Object>> constraintViolations = validator.validate(validatedObj, groups);

		if (!constraintViolations.isEmpty()) {
			result.addAll(constraintViolations);
		}

		for (final Method method : validatedObj.getClass().getMethods()) {
			if (method.getParameterCount() > 0 || method.getAnnotation(OnValid.class) == null) {
				return;
			}

			try {
				method.setAccessible(true);
				final Object property = method.invoke(validatedObj);

				if (property instanceof List<?>) {
					for (final Object o : (List<?>) property) {
						executeValidate(o, groups, result);
					}
				} else {
					executeValidate(property, groups, result);
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			}

		}
	}
}
