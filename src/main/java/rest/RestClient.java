package rest;

import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

public class RestClient {

    private static RestClient restClient;

    private static final String PROTOCOL = "http://";
    private static final String SPLITTER = ":";
    private static final String HOST_DEFAULT = "localhost";
    private static final String PORT_DEFAULT = "8080";
    private final RestTemplate rest;
    private final HttpEntity<?> entity;

    private final String host;
    private final String port;

    private RestClient(String host, String port) {
        this.rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        this.entity = new HttpEntity<>(headers);
        headers.add(HttpHeaders.ACCEPT, MediaType.ALL_VALUE);

        this.host = host;
        this.port = port;
    }

    public static RestClient getInstance(String host, String port) {
        if (restClient == null) {
            restClient = new RestClient(host, port);
        }
        return restClient;
    }

    public static RestClient getInstance(String host) {
        if (restClient == null) {
            restClient = new RestClient(host, PORT_DEFAULT);
        }
        return restClient;
    }

    public static RestClient getInstance() {
        if (restClient == null) {
            restClient = new RestClient(HOST_DEFAULT, PORT_DEFAULT);
        }
        return restClient;
    }

    public Integer get(String uri, MultiValueMap<String, String> params) {

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(PROTOCOL + host + SPLITTER + port + uri)
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