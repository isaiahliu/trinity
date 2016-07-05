package org.trinity.message;

/**
 * @author Isaiah Liu
 *
 *         Default message type
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

    private IMessageType parentType;

    @Override
    public IMessageType getParentType() {
        return parentType;
    }

    private GeneralMessageType(final IMessageType parentType) {
        this.parentType = parentType;
    }

    @Override
    public String getMessageTypeName() {
        return name();
    }

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
        public IMessageType getParentType() {
            return null;
        }

        @Override
        public String getMessageTypeName() {
            return "";
        }

        @Override
        public String getMessageTypePrefix() {
            return "";
        }
    }
}
