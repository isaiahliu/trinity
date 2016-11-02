package org.trinity.rest.util;

import org.trinity.common.dto.IResponse;
import org.trinity.common.exception.IException;
import org.trinity.common.url.IHttpUrl;

public interface IRestfulServiceUtil {
    default <T extends IResponse> T callRestService(final IHttpUrl url, final String subPath, final Object requestBody,
            final Object requestParamBean, final Class<T> responseType) throws IException {
        return callRestService(getDefaultToken(), url, subPath, requestBody, requestParamBean, responseType);
    }

    <T extends IResponse> T callRestService(String token, final IHttpUrl url, String subPath, final Object requestBody,
            final Object requestParamBean, final Class<T> responseType) throws IException;

    String getDefaultToken();

    IRestServer getRestServer();
}
