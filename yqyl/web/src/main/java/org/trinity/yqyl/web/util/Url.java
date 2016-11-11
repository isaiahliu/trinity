package org.trinity.yqyl.web.util;

import org.springframework.http.HttpMethod;
import org.trinity.common.url.IHttpUrl;
import org.trinity.common.url.IUrl;

public enum Url implements IHttpUrl {
    AUTHENTICATE(HttpMethod.PUT, Path.SECURITY, "authenticate"),
    LOGOUT(HttpMethod.PUT, Path.SECURITY, "logout"),
    REGISTER(HttpMethod.POST, Path.SECURITY, "register"),
    AUTHORITIES(HttpMethod.GET, Path.AUTHORITIES, ""),

    ACCESSRIGHT(HttpMethod.GET, Path.ACCESSRIGHT),

    TOKEN_VERIFY(HttpMethod.GET, Path.TOKEN, "verify"),
    TOKEN_NEW(HttpMethod.POST, Path.TOKEN),

    USER(HttpMethod.GET, Path.USER),
    USER_ME(HttpMethod.GET, Path.USER, "me"),
    USER_INFO(HttpMethod.PUT, Path.USER),
    USER_CHANGE_PASSWORD(HttpMethod.PUT, Path.USER, "password"),

    YIQUAN_ME(HttpMethod.GET, Path.YIQUAN, "me"),
    YIQUAN_BIND(HttpMethod.PUT, Path.YIQUAN, "bind"),
    YIQUAN_TOPUP(HttpMethod.POST, Path.YIQUAN, "topup"),

    ORDER(HttpMethod.GET, Path.ORDER),
    ORDER_UPDATE(HttpMethod.PUT, Path.ORDER),
    ORDER_PROPOSAL(HttpMethod.POST, Path.ORDER, "proposal"),
    ORDER_RECEIPT(HttpMethod.PUT, Path.ORDER, "receipt"),
    ORDER_RELEASE(HttpMethod.POST, Path.ORDER, "release"),
    ORDER_TRANSACTION(HttpMethod.POST, Path.ORDER, "transaction"),
    ORDER_CANCEL(HttpMethod.POST, Path.ORDER, "cancel"),
    ORDER_REJECT_CANCEL(HttpMethod.POST, Path.ORDER, "rejectCancel"),
    ORDER_PRICE(HttpMethod.PUT, Path.ORDER, "price"),

    APPRAISE_NEW(HttpMethod.POST, Path.APPRAISE),
    APPRAISE_UPDATE(HttpMethod.PUT, Path.APPRAISE),
    APPRAISE_REPLY(HttpMethod.POST, Path.APPRAISE, "reply"),

    REALNAME_ME(HttpMethod.GET, Path.REALNAME, "me"),
    REALNAME_UPDATE(HttpMethod.PUT, Path.REALNAME),

    SUPPLIER(HttpMethod.GET, Path.SUPPLIER),
    SUPPLIER_REGISTER(HttpMethod.POST, Path.SUPPLIER, "register"),
    SUPPLIER_UPDATE(HttpMethod.PUT, Path.SUPPLIER),
    SUPPLIER_AUDIT(HttpMethod.POST, Path.SUPPLIER, "audit"),
    SUPPLIER_REJECT(HttpMethod.POST, Path.SUPPLIER, "reject"),

    STAFF(HttpMethod.GET, Path.STAFF),
    STAFF_AVAILABLE(HttpMethod.GET, Path.STAFF, "available"),
    STAFF_UPDATE(HttpMethod.PUT, Path.STAFF),
    STAFF_NEW(HttpMethod.POST, Path.STAFF),

    AUDITING(HttpMethod.GET, Path.AUDITING),

    RECEIVER_AUDIT(HttpMethod.PUT, Path.RECEIVER_INFO, "audit"),
    RECEIVER_ME(HttpMethod.GET, Path.RECEIVER_INFO, "me"),
    RECEIVER(HttpMethod.GET, Path.RECEIVER_INFO),
    RECEIVER_PROPOSAL(HttpMethod.PUT, Path.RECEIVER_INFO, "proposal"),
    RECEIVER_UPDATE(HttpMethod.PUT, Path.RECEIVER_INFO),
    RECEIVER_DELETE(HttpMethod.DELETE, Path.RECEIVER_INFO),
    RECEIVER_CANCEL_PROPOSAL(HttpMethod.DELETE, Path.RECEIVER_INFO, "cancel"),
    RECEIVER_ADD(HttpMethod.POST, Path.RECEIVER_INFO),

    HEALTH_INDICATOR(HttpMethod.GET, Path.HEALTH_INDICATOR),

    OPERATOR(HttpMethod.GET, Path.OPERATOR),
    OPERATOR_UPDATE(HttpMethod.PUT, Path.OPERATOR),

    CONTENT_UPLOAD(HttpMethod.PUT, Path.CONTENT, "upload"),
    CONTENT_DOWNLOAD(HttpMethod.GET, Path.CONTENT, "download"),

    LOOKUP_TYPE(HttpMethod.GET, Path.LOOKUP),
    RESOURCE_REFRESH(HttpMethod.PUT, Path.RESOURCE),

    SERVICE_CATEGORY(HttpMethod.GET, Path.SERVICE_CATEGORY),
    SERVICE_CATEGORY_UPDATE(HttpMethod.PUT, Path.SERVICE_CATEGORY),

    REQUIREMENT_NEW(HttpMethod.POST, Path.REQUIREMENT),

    SERVICE_INFO(HttpMethod.GET, Path.SERVICE_INFO),
    SERVICE_INFO_NEW(HttpMethod.POST, Path.SERVICE_INFO),
    SERVICE_INFO_UPDATE(HttpMethod.PUT, Path.SERVICE_INFO),
    SERVICE_INFO_DELETE(HttpMethod.DELETE, Path.SERVICE_INFO),
    SERVICE_INFO_ME(HttpMethod.GET, Path.SERVICE_INFO, "me"),

    PING(HttpMethod.GET, Path.COMMON, "ping");
    private static enum Path implements IUrl {
        SECURITY("security"),
        TOKEN(SECURITY, "token"),
        AUTHORITIES(SECURITY, "authorities"),

        USER("user"),
        ORDER(USER, "order"),
        REALNAME(USER, "realname"),

        APPRAISE(ORDER, "appraise"),

        YIQUAN("yiquan"),

        CLIENT("client"),
        SUPPLIER(CLIENT, "supplier"),
        RECEIVER(CLIENT, "receiver"),
        OPERATOR(CLIENT, "operator"),

        STAFF(SUPPLIER, "staff"),
        AUDITING(SUPPLIER, "auditing"),

        RECEIVER_INFO(RECEIVER, "info"),

        HEALTH_INDICATOR(RECEIVER, "healthindicator"),

        SERVICE("service"),
        SERVICE_INFO(SERVICE, "info"),
        REQUIREMENT(SERVICE, "requirement"),

        CONTENT("content"),

        COMMON("common"),
        LOOKUP(COMMON, "lookup"),
        RESOURCE(COMMON, "resource"),
        SERVICE_CATEGORY(COMMON, "servicecategory"),
        ACCESSRIGHT(COMMON, "accessright");

        private IUrl parent;

        private String path;

        private Path(final IUrl parent, final String path) {
            this.parent = parent;
            this.path = path;
        }

        private Path(final String path) {
            this(null, path);
        }

        @Override
        public IUrl getParent() {
            return parent;
        }

        @Override
        public String getPath() {
            return path;
        }
    }

    private IUrl parent;

    private String path;
    private HttpMethod httpMethod;

    private Url(final HttpMethod httpMethod, final Path parent) {
        this(httpMethod, parent, "");
    }

    private Url(final HttpMethod httpMethod, final Path parent, final String path) {
        this.httpMethod = httpMethod;
        this.parent = parent;
        this.path = path;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    @Override
    public IUrl getParent() {
        return parent;
    }

    @Override
    public String getPath() {
        return path;
    }

}
