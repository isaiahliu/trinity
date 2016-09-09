package org.trinity.common.locale;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public abstract class AbstractLocaleInterceptor extends HandlerInterceptorAdapter {
    private final LocaleResolver localeResolver;
    private Locale defaultLocale = Locale.SIMPLIFIED_CHINESE;

    public AbstractLocaleInterceptor(final LocaleResolver localeResolver) {
        super();
        this.localeResolver = localeResolver;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        Locale locale = localeResolver.resolveLocale(request);

        if (!supportLocale(locale)) {
            locale = defaultLocale;
        }
        localeResolver.setLocale(request, response, locale);

        LocaleContextHolder.setLocale(locale);

        return true;
    }

    public void setDefaultLocale(final Locale defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    protected abstract boolean supportLocale(Locale locale);
}
