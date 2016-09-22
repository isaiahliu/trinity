package org.trinity.yqyl.web.util;

import org.springframework.http.HttpMethod;
import org.trinity.common.url.IHttpUrl;
import org.trinity.common.url.IUrl;

public enum Url implements IHttpUrl {
	AUTHENTICATE(HttpMethod.PUT, Path.SECURITY, "authenticate"),
	LOGOUT(HttpMethod.PUT, Path.SECURITY, "logout"),
	REGISTER(HttpMethod.POST, Path.SECURITY, "register"),
	AUTHORITIES(HttpMethod.GET, Path.AUTHORITIES, ""),

	TOKEN_VERIFY(HttpMethod.GET, Path.TOKEN, "verify"),
	TOKEN_NEW(HttpMethod.POST, Path.TOKEN),

	USER_ME(HttpMethod.GET, Path.USER, "me"),
	USER_INFO(HttpMethod.PUT, Path.USER),
	USER_CHANGE_PASSWORD(HttpMethod.PUT, Path.USER, "password"),

	SUPPLIER(HttpMethod.GET, Path.SUPPLIER),
	SUPPLIER_AUDIT(HttpMethod.PUT, Path.SUPPLIER, "audit"),
	SUPPLIER_ME(HttpMethod.GET, Path.SUPPLIER, "me"),
	SUPPLIER_PROPOSAL(HttpMethod.PUT, Path.SUPPLIER, "proposal"),

	RECEIVER_AUDIT(HttpMethod.PUT, Path.RECEIVER_INFO, "audit"),
	RECEIVER_ME(HttpMethod.GET, Path.RECEIVER_INFO, "me"),
	RECEIVER(HttpMethod.GET, Path.RECEIVER_INFO),
	RECEIVER_PROPOSAL(HttpMethod.PUT, Path.RECEIVER_INFO, "proposal"),
	RECEIVER_UPDATE(HttpMethod.PUT, Path.RECEIVER_INFO),
	RECEIVER_DELETE(HttpMethod.DELETE, Path.RECEIVER_INFO),
	RECEIVER_CANCEL_PROPOSAL(HttpMethod.DELETE, Path.RECEIVER_INFO, "cancel"),
	RECEIVER_ADD(HttpMethod.POST, Path.RECEIVER_INFO),

	OPERATOR(HttpMethod.GET, Path.OPERATOR),
	OPERATOR_UPDATE(HttpMethod.PUT, Path.OPERATOR),

	CONTENT_UPLOAD(HttpMethod.PUT, Path.CONTENT, "upload"),
	CONTENT_DOWNLOAD(HttpMethod.GET, Path.CONTENT, "download"),

	LOOKUP_TYPE(HttpMethod.GET, Path.LOOKUP),
	RESOURCE_REFRESH(HttpMethod.PUT, Path.RESOURCE),

	PING(HttpMethod.GET, Path.COMMON, "ping");
	private static enum Path implements IUrl {
		SECURITY("security"),
		TOKEN(SECURITY, "token"),
		USER(SECURITY, "user"),
		AUTHORITIES(SECURITY, "authorities"),

		CLIENT("client"),
		SUPPLIER(CLIENT, "supplier"),
		RECEIVER(CLIENT, "receiver"),
		OPERATOR(CLIENT, "operator"),

		RECEIVER_INFO(RECEIVER, "info"),

		CONTENT("content"),

		COMMON("common"),
		LOOKUP(COMMON, "lookup"),
		RESOURCE(COMMON, "resource");

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
