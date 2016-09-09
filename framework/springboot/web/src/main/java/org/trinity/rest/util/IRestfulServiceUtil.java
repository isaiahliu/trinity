package org.trinity.rest.util;

import org.trinity.common.url.IHttpUrl;

public interface IRestfulServiceUtil {
	default <T> T callRestService(final IHttpUrl url, final String subPath, final Object requestBody, final Object requestParamBean,
			final Class<T> responseType) {
		return callRestService(getDefaultToken(), url, subPath, requestBody, requestParamBean, responseType);
	}

	<T> T callRestService(String token, final IHttpUrl url, String subPath, final Object requestBody, final Object requestParamBean,
			final Class<T> responseType);

	String getDefaultToken();

	IRestServer getRestServer();
}
