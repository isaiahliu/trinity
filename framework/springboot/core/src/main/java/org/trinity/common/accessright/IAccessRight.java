package org.trinity.common.accessright;

import org.springframework.security.core.GrantedAuthority;
import org.trinity.message.ILookupMessage;
import org.trinity.message.ILookupType;
import org.trinity.message.IMessage;

/**
 * @author Isaiah Liu
 *
 *         The interface of all accessright message.
 *
 * @see IMessage
 */
public interface IAccessRight<TType extends ILookupType> extends GrantedAuthority, ILookupMessage<TType> {

    @Override
    default String getAuthority() {
        return getMessageCode();
    }

    IAccessRight<TType> getParentAccessRight();
}
