package org.trinity.framework.contact;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ContactMessageField {

    public static enum EmptyKeys implements IAdditionalMessageKey {
        ;

        @Override
        public int getDefaultLength() {
            return 0;
        }

        @Override
        public FieldType getFieldType() {
            return null;
        }

        @Override
        public int getId() {
            return 0;
        }
    }

    public static enum FieldType {
        BYTE,
        NBYTE,
        WORD,
        DWORD,
        STRING,
        BCD,
        VAR_BCD,
        ADDITIONALS,
        STRUCT,
        COMPONENT,
        COMPONENT_LIST,
        BYTEARRAY,
        LLVAR_BCD
    }

    Class<?> additionalEnumClass() default EmptyKeys.class;

    int bitmapPos() default 0;

    boolean depends() default false;

    FieldType fieldType();

    String getLengthFrom() default "";

    String getRequiredFrom() default "";

    /**
     * Only Required for NBYTE, BCD and STRUCT
     *
     * @return
     */
    int length() default 1;

    int order();

    /**
     * Only Required for NBYTE, BCD and STRUCT
     *
     * @return
     */
    char padLetter() default ' ';

    boolean required() default true;;
}
