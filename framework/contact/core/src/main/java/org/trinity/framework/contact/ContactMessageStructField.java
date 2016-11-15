package org.trinity.framework.contact;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ContactMessageStructField {
    public static enum StructFieldType {
        FLAG,
        NBIT,
        NIL,
    }

    StructFieldType fieldType();

    String getLengthFrom() default "";

    String getRequiredFrom() default "";

    /**
     * Only required for NBIT
     *
     * @return
     */
    int length() default 1;

    int order();

    boolean required() default true;
}
