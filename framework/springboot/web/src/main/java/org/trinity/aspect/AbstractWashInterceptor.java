package org.trinity.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.trinity.common.dto.washer.KeepAfterWashed;
import org.trinity.common.dto.washer.OnWash;

public abstract class AbstractWashInterceptor {

	public abstract void findWashAnnotation();

	public void wash(final JoinPoint jp) throws ConstraintViolationException {
		final Signature signature = jp.getSignature();
		final Object[] args = jp.getArgs();
		final MethodSignature methodSignature = (MethodSignature) signature;
		final Method targetMethod = methodSignature.getMethod();
		final Annotation[][] paraAnnotations = targetMethod.getParameterAnnotations();

		if (paraAnnotations != null && paraAnnotations.length > 0) {
			for (int i = 0; i < paraAnnotations.length; i++) {
				final int paraAnnotationLength = paraAnnotations[i].length;

				if (paraAnnotationLength == 0) {
					continue;
				}
				for (int j = 0; j < paraAnnotationLength; j++) {
					if (paraAnnotations[i][j] instanceof OnWash) {
						final OnWash washed = (OnWash) paraAnnotations[i][j];
						final Class<?> group = washed.value();
						final Object validatedObj = args[i];
						executeWash(validatedObj, group);
						break;
					}
				}
			}
		}
	}

	private void executeWash(final Object validatedObj, final Class<?> group) {
		if (validatedObj == null) {
			return;
		}

		for (final Method method : validatedObj.getClass().getMethods()) {
			try {
				final String methodName = method.getName();
				if (methodName.startsWith("set")) {
					final KeepAfterWashed keepAfterWashed = method.getAnnotation(KeepAfterWashed.class);
					if (keepAfterWashed == null) {
						method.invoke(validatedObj, new Object[] { null });
					} else if (keepAfterWashed.value().length > 0) {
						if (!Arrays.stream(keepAfterWashed.value()).filter(item -> item == group).findAny().isPresent()) {
							method.invoke(validatedObj, new Object[] { null });
						}
					}
				} else if (methodName.startsWith("get")) {
					if (method.getAnnotation(OnWash.class) != null) {
						final Object property = method.invoke(validatedObj);

						if (property instanceof List<?>) {
							for (final Object o : (List<?>) property) {
								executeWash(o, group);
							}
						} else {
							executeWash(property, group);
						}
					}
				}

				if (method.getParameterCount() > 0 || method.getAnnotation(OnWash.class) == null) {
					continue;
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			}

		}
	}
}
