package rest;

import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

public class RestClient {

    private static final RestClient restClient = new RestClient();
    private static final String server = "http://localhost:8080";
    private final RestTemplate rest;
    private final HttpEntity<?> entity;

    private RestClient() {
        this.rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        this.entity = new HttpEntity<>(headers);
        headers.add(HttpHeaders.ACCEPT, MediaType.ALL_VALUE);
    }

    public static RestClient getInstance() {
        return restClient;
    }

    public Integer get(String uri, MultiValueMap<String, String> params) {

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
        return Integer.parseInt(Objects.requireNonNull(response.getBody()));
    }

}