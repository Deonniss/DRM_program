package rest;

public enum RestRequest {

    LOGIN("/login"),
    REGISTRATION("/registration"),
    HARDWARE("/hardware"),
    HARDWARE_ADD("/hardware/add"),
    LICENSE("/license");

    public final String name;

    RestRequest(String name) {
        this.name = name;
    }
}
