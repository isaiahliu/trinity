package org.trinity.message;

/**
 * Default message type
 *
 * @author Isaiah Liu
 */
public enum GeneralMessageType implements IMessageType {
    CAPTION(RootMessageType.getInstance()),
    BUTTON(CAPTION),
    LABEL(CAPTION),
    TEXT(CAPTION),

    LOOKUP(RootMessageType.getInstance()),
    ACCESSRIGHT(LOOKUP),
    LANGUAGE(LOOKUP),

    ERRORMESSAGE(RootMessageType.getInstance()),

    LOG(RootMessageType.getInstance()),
    LOGLEVEL(LOG);

    private static class RootMessageType implements IMessageType {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private static RootMessageType instance;

        private static RootMessageType getInstance() {
            if (instance == null) {
                synchronized (RootMessageType.class) {
                    if (instance == null) {
                        instance = new RootMessageType();
                    }
                }
            }
            return instance;
        }

        private RootMessageType() {
        }

        @Override
        public String getMessageTypeName() {
            return "";
        }

        @Override
        public String getMessageTypePrefix() {
            return "";
        }

        @Override
        public IMessageType getParentType() {
            return null;
        }
    }

    private IMessageType parentType;

    private GeneralMessageType(final IMessageType parentType) {
        this.parentType = parentType;
    }

    @Override
    public String getMessageTypeName() {
        return name();
    }

    @Override
    public IMessageType getParentType() {
        return parentType;
    }
}
