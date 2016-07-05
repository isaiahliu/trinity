package org.trinity.repository.type;

import java.io.Serializable;
import java.sql.Types;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
import org.trinity.message.IMessage;

public abstract class AbstractMessageType implements UserType, ParameterizedType {
    private Properties parameters;

    @Override
    public Object assemble(final Serializable cached, final Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object deepCopy(final Object value) throws HibernateException {
        return value;
    }

    @Override
    public Serializable disassemble(final Object value) throws HibernateException {
        return (IMessage) value;
    }

    @Override
    public boolean equals(final Object x, final Object y) throws HibernateException {
        if (x == y) {
            return true;
        }

        if (x != null && y != null) {
            final IMessage xObject = (IMessage) x;
            final IMessage yObject = (IMessage) y;

            return xObject.getMessageCodeWithPrefix().equals(yObject.getMessageCodeWithPrefix());
        }
        return false;
    }

    @Override
    public int hashCode(final Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
        if (original == null) {
            return target;
        }
        return original;
    }

    @Override
    public Class<?> returnedClass() {
        return IMessage.class;
    }

    @Override
    public void setParameterValues(final Properties parameters) {
        this.parameters = parameters;
    }

    @Override
    public int[] sqlTypes() {
        return new int[] { Types.VARCHAR };
    }

    protected Class<?> getTargetClass() {
        try {
            return Class.forName(parameters.getProperty("class"));
        } catch (final ClassNotFoundException e) {
            return Object.class;
        }
    }
}
