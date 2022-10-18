package rest;

public enum StatusCode {

    LOGIN_SUCCESS_101("Successful authorization!", 101),
    REGISTRATION_SUCCESS_110("Successful registration", 110),
    LICENSE_SUCCESS_120("The license key has been successfully activated", 210),
    HARDWARE_SUCCESS_130("The hardware configuration is correct", 130),

    LOGIN_FAILED_201("User does not exist", 201),
    LOGIN_FAILED_202("Password doesn't match", 202),
    LOGIN_FAILED_203("User is blocked", 203),
    REGISTRATION_FAILED_210("user already exists", 210),

    LICENSE_FAILED_220("The license key is already activated!", 220),
    LICENSE_FAILED_221("The license key does not exist!", 221),

    HARDWARE_FAILED_230("The hardware configuration is incorrect", 230);

    public final String message;
    public final int code;

    StatusCode(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public static StatusCode findByCode(String name){
        return StatusCode.valueOf(name);
    }
}
