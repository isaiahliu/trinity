package org.trinity.yqyl.common.message.exception;

import org.trinity.message.exception.IErrorMessage;

public enum ErrorMessage implements IErrorMessage {
    TOKEN_IS_EXPIRED,
    LOGGED_BY_OTHERS,
    USER_IS_DISABLED,
    PASSWORD_CHANGED,
    WRONG_PASSWORD,
    UNABLE_TO_ACCESS_USER,
    MULTI_ROOT_ACCESSRIGHT,
    TOKEN_IS_NOT_AUTHENTICATED,
    USERNAME_IS_REGISTERED,
    SUPPLIER_CLIENT_EXISTS,
    ONLY_PROPOSAL_CLIENT_CAN_BE_CANCELLED,
    SERVICE_SUPPLIER_CLIENT_MUST_BE_PROPOSAL,
    SERVICE_RECEIVER_CLIENT_MUST_BE_PROPOSAL,
    NO_SUB_ORDERS,
    INVALID_ORDER_ID,
    INCORRECT_SERVICE_ORDER_STATUS,
    INSUFFICIENT_ACCESSRIGHT,
    UNABLE_TO_FIND_USER;

    @Override
    public String getMessageCode() {
        return name();
    }
}
