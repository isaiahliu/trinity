package org.trinity.rest.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.trinity.common.accessright.AuthToken;
import org.trinity.common.accessright.ITokenAwareAuthentication;
import org.trinity.common.url.IHttpUrl;
import org.trinity.common.util.Tuple2;

public class RestfulServiceUtil implements IRestfulServiceUtil {

	private final IRestServer restServer;

	public RestfulServiceUtil(final IRestServer restServer) {
		super();
		this.restServer = restServer;
	}

	@Override
	public <T> T callRestService(final String token, final IHttpUrl url, final String subPath, final Object requestBody,
			final Object requestParamBean, final Class<T> responseType) {
		return callRestService(token, url.getFullPath(), subPath, url.getHttpMethod(), requestBody, requestParamBean, responseType);
	}

	@Override
	public String getDefaultToken() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication instanceof ITokenAwareAuthentication) {
			final AuthToken token = ((ITokenAwareAuthentication) authentication).getToken();
			if (token != null) {
				return token.getToken();
			}
		}
		return null;
	}

	@Override
	public IRestServer getRestServer() {
		return restServer;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected <T> T callRestService(final String token, final String url, final String subPath, final HttpMethod httpMethod,
			final Object requestBody, final Object requestParamBean, final Class<T> responseType) {

		RestTemplate restTemplate = null;
		if (token != null) {
			final List<Header> headers = new ArrayList<>();
			headers.add(new BasicHeader("Token", token));

			final CloseableHttpClient client = HttpClientBuilder.create().setDefaultHeaders(headers).useSystemProperties().build();
			final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(client);
			restTemplate = new RestTemplate(requestFactory);
		} else {
			restTemplate = new RestTemplate();
		}

		final Map<String, Object> values = new HashMap<String, Object>();
		final List<Tuple2<String, String>> keys = new ArrayList<>();

		if (requestParamBean != null) {
			if (requestParamBean instanceof Map) {
				values.putAll((Map) requestParamBean);
				values.forEach((key, value) -> keys.add(new Tuple2<>(key, key)));
			} else {
				for (final Method method : requestParamBean.getClass().getMethods()) {
					String prefix = null;
					if (method.getName().startsWith("get")) {
						prefix = "get";
					} else if (method.getName().startsWith("is")) {
						prefix = "is";
					}

					if (prefix == null) {
						continue;
					}

					try {
						final Object value = method.invoke(requestParamBean);
						String fieldName = method.getName().substring(prefix.length());

						fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);

						if (value instanceof List) {
							for (int index = 0; index < ((List) value).size(); index++) {
								final Object listValue = ((List) value).get(index);
								keys.add(new Tuple2<>(fieldName, fieldName + "_" + index));
								values.put(fieldName + "_" + index, listValue != null ? listValue : "");
							}
						} else {
							keys.add(new Tuple2<>(fieldName, fieldName));
							values.put(fieldName, value != null ? value : "");
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					}
				}
			}
		}

		String restUrl = getRestServer().getUrl().concat(url);

		if (!StringUtils.isEmpty(subPath)) {
			restUrl = restUrl.concat("/").concat(subPath);
		}

		if (!values.isEmpty()) {
			restUrl = restUrl.concat("?").concat(String.join("&",
					keys.stream().map(item -> String.format("%s={%s}", item.getItem1(), item.getItem2())).toArray(String[]::new)));
		}

		HttpEntity<?> requestBodyEntity = null;
		if (requestBody != null) {
			requestBodyEntity = new HttpEntity(requestBody);
		}
		return restTemplate.exchange(restUrl, httpMethod, requestBodyEntity, responseType, values).getBody();
	}

}
