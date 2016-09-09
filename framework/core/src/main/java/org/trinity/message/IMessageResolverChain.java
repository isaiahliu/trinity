package org.trinity.message;

import java.util.Locale;

/**
 * A chain for message resolvers. A message key can be resolved by the resolvers one by one until one resolver can get
 * the description.
 * 
 * @author Isaiah Liu
 *
 * @see IMessageResolver
 */
public interface IMessageResolverChain {
    /**
     * Get the message description by the default locale.
     *
     * @param message
     *            Message type of {@link IMessage}.
     * @return Message description.
     */
    String getMessage(IMessage<?> message);

    /**
     * Get the message description.
     *
     * @param message
     *            Message type of {@link IMessage}.
     * @param locale
     *            Specified locale.
     * @return Message description.
     */
    String getMessage(IMessage<?> message, Locale locale);

    /**
     * Get the message description
     *
     * @param message
     *            Message type of {@link IMessage}.
     * @param locale
     *            Specified locale.
     * @param args
     *            Message arguments
     * @return Message description.
     */
    String getMessage(IMessage<?> message, Locale locale, String... args);

    /**
     * Get the message description by the default locale.
     *
     * @param message
     *            Message type of {@link IMessage}.
     * @param args
     *            Message arguments
     * @return Message description.
     */
    String getMessage(IMessage<?> message, String... args);

    /**
     * Get the message description by the default locale.
     *
     * @param message
     *            Message key.
     * @return Message description.
     */
    String getMessage(String message);

    /**
     * Get the message description.
     *
     * @param message
     *            Message key.
     * @param locale
     *            Specified locale.
     * @return Message description.
     */
    String getMessage(String message, Locale locale);

    /**
     * Get the message description
     *
     * @param message
     *            Message key.
     * @param locale
     *            Specified locale.
     * @param args
     *            Message arguments
     * @return Message description.
     */
    String getMessage(String message, Locale locale, String... args);

    /**
     * Get the message description by the default locale.
     *
     * @param message
     *            Message key.
     * @param args
     *            Message arguments
     * @return Message description.
     */
    String getMessage(String message, String... args);

    /**
     * Get the resolver list.
     *
     * @return resolvers
     */
    IMessageResolver[] getMessageResolvers();

    void refresh();
}
