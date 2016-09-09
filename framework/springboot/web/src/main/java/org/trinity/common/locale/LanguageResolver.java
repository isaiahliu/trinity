package org.trinity.common.locale;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;

public class LanguageResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(final HttpServletRequest request) {
        return request.getLocale();
    }

    @Override
    public void setLocale(final HttpServletRequest request, final HttpServletResponse response, final Locale locale) {
        response.setLocale(locale);
    }

}
