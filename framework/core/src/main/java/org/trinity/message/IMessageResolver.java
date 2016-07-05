package org.trinity.message;

import java.util.Locale;

/**
 * @author Isaiah Liu
 *
 *         Resolver for the messages.
 */
public interface IMessageResolver {
    /**
     * This method should get the message description from a message key and locale.
     *
     * @param message
     *            Message key
     * @param locale
     *            The locale for the message
     * @param args
     *            Arguments if exists
     * @return Message description
     */
    String getMessage(String message, Locale locale, String... args);

    void refresh();
}
