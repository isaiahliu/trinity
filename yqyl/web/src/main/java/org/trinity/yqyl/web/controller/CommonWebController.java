package org.trinity.yqyl.web.controller;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;

@RestController
@RequestMapping("/common/*")
public class CommonWebController {
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ModelAndView ping() throws IException {
        final CredentialsProvider provider = new BasicCredentialsProvider();
        final UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("admin", "admin");
        provider.setCredentials(AuthScope.ANY, credentials);

        final CloseableHttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider)
                .useSystemProperties().build();
        final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
                client);

        final RestTemplate restTemplate = new RestTemplate(requestFactory);
        final DefaultResponse response = restTemplate.getForObject("http://localhost:50001/common/ping",
                DefaultResponse.class);
        return new ModelAndView("ping").addObject("response", response);
    }
}
