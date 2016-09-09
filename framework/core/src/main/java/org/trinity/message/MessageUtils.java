package org.trinity.message;

public final class MessageUtils {
    public static boolean in(final IMessage<?> sourceMessage, final IMessage<?>... messages) {
        if (messages == null) {
            return false;
        }

        for (final IMessage<?> message : messages) {
            if (message == null) {
                continue;
            }

            if (message == sourceMessage) {
                return true;
            }
        }
        return false;
    }

    public static boolean in(final String code, final IMessage<?>... messages) {
        if (messages == null) {
            return false;
        }

        for (final IMessage<?> message : messages) {
            if (message == null) {
                continue;
            }

            if (message.getMessageCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    private MessageUtils() {
    }
}
