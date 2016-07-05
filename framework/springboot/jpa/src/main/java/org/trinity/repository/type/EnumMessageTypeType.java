package org.trinity.repository.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.trinity.message.IMessageType;

public class EnumMessageTypeType extends AbstractMessageType {
    @Override
    public Object nullSafeGet(final ResultSet rs, final String[] names, final SessionImplementor session,
            final Object owner) throws HibernateException, SQLException {
        final String value = rs.getString(names[0]);

        for (final Object message : getTargetClass().getEnumConstants()) {
            if (message instanceof IMessageType && ((IMessageType) message).getMessageTypeName().equals(value)) {
                return message;
            }
        }

        return null;
    }

    @Override
    public void nullSafeSet(final PreparedStatement st, final Object value, final int index,
            final SessionImplementor session) throws HibernateException, SQLException {
        if (value != null) {

            st.setString(index, ((IMessageType) value).getMessageTypeName());
        } else {
            st.setNull(index, Types.VARCHAR);
        }
    }
}
