package handler;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public final class WebParamHandler {

    private WebParamHandler() {}

    public static MultiValueMap<String, String> login(String username, String password, String hardware) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("username", username);
        requestParams.add("password", password);
        requestParams.add("hardware", hardware);
        return requestParams;
    }

    public static MultiValueMap<String, String> registration(String username, String password, String hardware, String license) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("username", username);
        requestParams.add("password", password);
        requestParams.add("hardware", hardware);
        requestParams.add("license", license);
        return requestParams;
    }
}
