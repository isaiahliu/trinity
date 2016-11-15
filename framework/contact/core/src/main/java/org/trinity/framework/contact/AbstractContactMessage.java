package org.trinity.framework.contact;

public abstract class AbstractContactMessage<TMessageMeta extends IContactMessageMeta>
        implements IContactMessage<TMessageMeta> {
    private TMessageMeta meta;

    @Override
    public TMessageMeta getMeta() {
        if (meta == null) {
            meta = createMessageMeta();
        }
        return meta;
    }

    @Override
    public void setMeta(final TMessageMeta meta) {
        this.meta = meta;
    }

    protected abstract TMessageMeta createMessageMeta();

    protected abstract int getDefaultMessageId();
}
