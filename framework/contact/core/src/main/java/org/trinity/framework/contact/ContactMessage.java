package org.trinity.framework.contact;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ContactMessage {
    public static enum StoreMethod {
        BIG_END,
        LITTLE_END;
    }

    StoreMethod storeMethod();
}
