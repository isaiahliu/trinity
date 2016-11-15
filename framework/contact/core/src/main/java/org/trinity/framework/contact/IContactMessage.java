package org.trinity.framework.contact;

public interface IContactMessage<TMessageMeta extends IContactMessageMeta> {
    TMessageMeta getMeta();

    int getRequestSerialNumber();

    boolean isRequireResponse();

    void setMeta(TMessageMeta meta);
}
