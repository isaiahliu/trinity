package org.trinity.rest.util;

import org.trinity.common.url.IHttpUrl;

public interface IRestfulServiceUtil {
	<T> T callRestService(IRestServer server, final IHttpUrl url, final Object requestBody,
			final Object requestParamBean, final Class<T> responseType);

	<T> T callRestService(IRestServer server, String token, final IHttpUrl url,
			final Object requestBody, final Object requestParamBean,
			final Class<T> responseType);
}
