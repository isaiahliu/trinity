package org.trinity.common.dto.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordCheck, String>
{
	@Override
	public void initialize(PasswordCheck check)
	{
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context)
	{
		return value.length() >= 6 && value.length() <= 20;
	}
}
