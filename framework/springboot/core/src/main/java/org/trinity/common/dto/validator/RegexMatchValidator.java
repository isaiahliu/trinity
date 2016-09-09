package org.trinity.common.dto.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegexMatchValidator implements ConstraintValidator<RegexMatchCheck, String>
{
	private String regex = "^$";

	@Override
	public void initialize(RegexMatchCheck check)
	{
		regex = check.regex();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context)
	{
		return value.matches(regex);
	}
}
