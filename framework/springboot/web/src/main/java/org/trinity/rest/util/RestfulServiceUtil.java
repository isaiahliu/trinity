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

		final Map<String, Object> varibles = new HashMap<String, Object>();

		if (requestParamBean != null) {
			if (requestParamBean instanceof Map) {
				varibles.putAll((Map) requestParamBean);
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

						varibles.put(fieldName, value != null ? value : "");
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					}
				}
			}
		}

		String restUrl = getRestServer().getUrl().concat(url);

		if (!StringUtils.isEmpty(subPath)) {
			restUrl = restUrl.concat("/").concat(subPath);
		}

		if (!varibles.isEmpty()) {
			restUrl = restUrl.concat("?").concat(
					String.join("&", varibles.keySet().stream().map(item -> String.format("%1$s={%1$s}", item)).toArray(String[]::new)));
		}

		HttpEntity<?> requestBodyEntity = null;
		if (requestBody != null) {
			requestBodyEntity = new HttpEntity(requestBody);
		}
		return restTemplate.exchange(restUrl, httpMethod, requestBodyEntity, responseType, varibles).getBody();
	}

}
