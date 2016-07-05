package org.trinity.rest.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.trinity.common.url.IHttpUrl;

public class RestfulServiceUtil implements IRestfulServiceUtil {

	@Override
	public <T> T callRestService(final IRestServer server, final IHttpUrl url,
			final Object requestBody, final Object requestParamBean,
			final Class<T> responseType) {
		return callRestService(server, null, url, requestBody, requestParamBean, responseType);
	}

	@Override
	public <T> T callRestService(final IRestServer server, final String token,
			final IHttpUrl url, final Object requestBody, final Object requestParamBean,
			final Class<T> responseType) {
		return callRestService(server, token, url.getFullPath(), url.getHttpMethod(),
				requestBody, requestParamBean, responseType);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected <T> T callRestService(final IRestServer server, final String token,
			final String url, final HttpMethod httpMethod, final Object requestBody,
			final Object requestParamBean, final Class<T> responseType) {

		RestTemplate restTemplate = null;
		if (token != null) {
			final List<Header> headers = new ArrayList<>();
			headers.add(new BasicHeader("Token", token));

			final CloseableHttpClient client = HttpClientBuilder.create()
					.setDefaultHeaders(headers).useSystemProperties().build();
			final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
					client);
			restTemplate = new RestTemplate(requestFactory);
		} else {
			restTemplate = new RestTemplate();
		}

		final Map<String, Object> varibles = new HashMap<String, Object>();

		if (requestParamBean != null) {
			for (final Field field : requestParamBean.getClass().getDeclaredFields()) {
				try {
					field.setAccessible(true);
					final Object value = field.get(requestParamBean);

					varibles.put(field.getName(), value != null ? value : "");
				} catch (final Exception e) {

				}
			}
		}

		final String restUrl = server.getUrl().concat(url).concat("?")
				.concat(String.join("&",
						varibles.keySet().stream()
								.map(item -> String.format("%1$s={%1$s}", item))
								.toArray(String[]::new)));

		HttpEntity<?> requestBodyEntity = null;
		if (requestBody != null) {
			requestBodyEntity = new HttpEntity(requestBody);
		}
		return restTemplate
				.exchange(restUrl, httpMethod, requestBodyEntity, responseType, varibles)
				.getBody();
	}
}
