package org.trinity.yqyl.common.message.exception;

import org.trinity.message.exception.IErrorMessage;

public enum ErrorMessage implements IErrorMessage {
    TOKEN_IS_EXPIRED,
    LOGGED_BY_OTHERS,
    USER_IS_DISABLED,
    PASSWORD_CHANGED,
    UNABLE_TO_FIND_USER,
    WRONG_PASSWORD,
    UNABLE_TO_ACCESS_USER,
    UNABLE_TO_FIND_ALLOWANCE_SUPPLIER_CLIENT,
    UNABLE_TO_FIND_SERVICE_SUPPLIER_CLIENT,
    UNABLE_TO_FIND_SERVICE_RECEIVER_CLIENT,
    UNABLE_TO_FIND_OPERATOR_CLIENT,
    UNABLE_TO_FIND_ACCESSRIGHT,
    UNABLE_TO_FIND_ACCOUNT_POSTING,
    UNABLE_TO_FIND_ACCOUNT_BALANCE,
    UNABLE_TO_FIND_ACCOUNT,
    UNABLE_TO_FIND_ACCOUNT_TRANSACTION,
    UNABLE_TO_FIND_MESSAGE,
    UNABLE_TO_FIND_ROLE,
    UNABLE_TO_FIND_SYSTEM_ATTRIBUTE,
    UNABLE_TO_FIND_ANNOUNCEMENT,
    UNABLE_TO_FIND_ORDER,
    UNABLE_TO_FIND_SERVICE,
    UNABLE_TO_FIND_USER_GROUP,
    UNABLE_TO_FIND_LOOKUP,
    MULTI_ROOT_ACCESSRIGHT,
    UNABLE_TO_FIND_FAVORITE,
    UNABLE_TO_FIND_TOKEN,
    TOKEN_IS_NOT_AUTHENTICATED,
    USERNAME_IS_REGISTERED,
    UNABLE_TO_FIND_CONTENT,
    SUPPLIER_CLIENT_EXISTS,
    ONLY_PROPOSAL_CLIENT_CAN_BE_CANCELLED,
    SERVICE_SUPPLIER_CLIENT_MUST_BE_PROPOSAL,
    SERVICE_RECEIVER_CLIENT_MUST_BE_PROPOSAL;

    @Override
    public String getMessageCode() {
        return name();
    }
}
