package rest;

public enum RestRequest {

    LOGIN("/login"),
    REGISTRATION("/registration");

    public final String name;

    RestRequest(String name) {
        this.name = name;
    }
}
