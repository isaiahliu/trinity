package org.trinity.rest.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;

public class SimplifiedChineseLocaleResolver implements LocaleResolver
{
	@Override
	public Locale resolveLocale(final HttpServletRequest request)
	{
		return Locale.SIMPLIFIED_CHINESE;
	}

	@Override
	public void setLocale(final HttpServletRequest request, final HttpServletResponse response, final Locale locale)
	{
		response.setLocale(Locale.SIMPLIFIED_CHINESE);
	}

}
