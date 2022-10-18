package data;

import java.io.Serializable;

public class UserData implements Serializable {

    private String login;
    private String password;
    private String command;

    public UserData() {
    }

    public UserData(String login, String password, String command) {
        this.login = login;
        this.password = password;
        this.command = command;
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

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
