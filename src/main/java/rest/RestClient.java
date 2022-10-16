package rest;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


public class RestClient {

    private static final RestClient restClient = new RestClient();
    private final String server = "http://localhost:3000";
    private final RestTemplate rest;
    private final HttpHeaders headers;

    private RestClient() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT, MediaType.ALL_VALUE);
    }

    public static RestClient getInstance() {
        return restClient;
    }

    public Object get(String uri, String data) {
        HttpEntity<String> requestEntity = new HttpEntity<>(data, headers);
        ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, String.class);
        return responseEntity.getBody();
    }
}