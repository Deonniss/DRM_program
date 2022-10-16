package data;

public class DataID {

    private String hardwareID;
    private String login;
    private String password;

    public String getHardwareID() {
        return hardwareID;
    }

    public void setHardwareID(String hardwareID) {
        this.hardwareID = hardwareID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DataID{hardwareID='"
                + hardwareID + ", login='"
                + login + ", password='"
                + password + ", secretKey='"
                + '}';
    }
}
