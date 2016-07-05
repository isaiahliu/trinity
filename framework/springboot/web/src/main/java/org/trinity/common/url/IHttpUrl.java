package org.trinity.common.url;

import org.springframework.http.HttpMethod;

public interface IHttpUrl extends IUrl
{
	HttpMethod getHttpMethod();
}
