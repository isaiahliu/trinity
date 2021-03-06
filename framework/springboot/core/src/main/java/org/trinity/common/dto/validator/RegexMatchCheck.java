package org.trinity.common.dto.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegexMatchValidator.class)
@Documented
public @interface RegexMatchCheck
{
	/**
	 * Description: the validation groups if any
	 *
	 * @return
	 */
	Class<?>[]groups() default {};

	String message();

	/**
	 * Description: {@link Payload} that needs to be used during validation
	 *
	 * @return
	 */
	Class<? extends Payload>[]payload() default {};

	String regex();
}