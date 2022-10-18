package rest;

import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;


public class RestClient {

    private static final RestClient restClient = new RestClient();
    private final String server = "http://localhost:8080";
    private final RestTemplate rest;
    private final HttpHeaders headers;
    private final HttpEntity<?> entity;

    private RestClient() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        this.entity = new HttpEntity<>(headers);
        headers.add(HttpHeaders.ACCEPT, MediaType.ALL_VALUE);
    }

    public static RestClient getInstance() {
        return restClient;
    }

    public String get(String uri, MultiValueMap<String, String> params) {

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(server + uri)
                .queryParams(params)
                .encode()
                .toUriString();

        HttpEntity<String> response = rest.exchange(
                urlTemplate,
                HttpMethod.GET,
                entity,
                String.class,
                params);
        return response.getBody();
    }

}