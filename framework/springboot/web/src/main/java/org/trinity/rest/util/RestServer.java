package org.trinity.rest.util;

public class RestServer implements IRestServer {
	private final String url;

	public RestServer(final String url) {
		this.url = url;
	}

	@Override
	public String getUrl() {
		return url;
	}
}
