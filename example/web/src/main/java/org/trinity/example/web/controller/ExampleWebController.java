package org.trinity.example.web.controller;

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
import org.trinity.common.exception.IException;
import org.trinity.example.common.dto.request.GetDataRequest;
import org.trinity.example.common.dto.response.GetDataResponse;

@RestController
@RequestMapping("/example/*")
public class ExampleWebController {
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView page(final GetDataRequest request) throws IException {
        final CredentialsProvider provider = new BasicCredentialsProvider();
        final UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("admin", "admin");
        provider.setCredentials(AuthScope.ANY, credentials);

        final CloseableHttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider)
                .useSystemProperties().build();
        final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
                client);

        final RestTemplate restTemplate = new RestTemplate(requestFactory);
        GetDataResponse response = null;
        if (request.getData() == null) {
            response = restTemplate.getForObject("http://localhost:50001/example/data", GetDataResponse.class);
        } else {
            response = restTemplate.getForObject("http://localhost:50001/example/data?data=" + request.getData(),
                    GetDataResponse.class);
        }
        return new ModelAndView("example").addObject("request", request.getData()).addObject("response", response);
    }
}
