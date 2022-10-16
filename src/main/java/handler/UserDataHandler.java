package handler;

import data.UserData;

import java.util.Scanner;

public class UserDataHandler {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String USER_DATA_FILE = "user_data";

    private UserDataHandler() {}

    public static UserData generateUserData() {
        UserData userData = (UserData) FileHandler.getData(USER_DATA_FILE);
        if (userData == null) {
            userData = new UserData();
            System.out.println("Enter the user name!");
            String login = scanner.nextLine();
            System.out.println("Enter your password!");
            String password = scanner.nextLine();
            userData.setLogin(login);
            userData.setPassword(password);
        }
        return userData;
    }
}
