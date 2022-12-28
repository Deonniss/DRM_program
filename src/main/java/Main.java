import data.ConsoleData;
import rest.RestClient;
import rest.StatusCode;

import java.awt.*;
import java.util.Scanner;

import static handler.ComputerIdentifierHandler.generateHardware;
import static handler.ConsoleColorPrinter.print;
import static handler.ConsoleHandler.*;
import static handler.WebParamHandler.*;
import static rest.RestRequest.*;
import static rest.StatusCode.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        RestClient restClient;
        switch (args.length) {
            case 1:
                restClient = RestClient.getInstance(args[0]);
                break;
            case 2:
                restClient = RestClient.getInstance(args[0], args[1]);
                break;
            default:
                restClient = RestClient.getInstance();
        }

        String in;
        while (true) {
            try {
                print("commands: reg, log. params: -u 'username' -p 'password'\nexamples: reg -u denis -p 0000, log -u denis -p 0000\nenter 'q' for exit\n\n", Color.WHITE);
                in = scanner.nextLine();
                if (in.equalsIgnoreCase("q")) return;
                ConsoleData data = generateRequest(in);
                StatusCode code;
                if (data.getCommand().equals("log")) {
                    code = findByCode(restClient.get(LOGIN.name, login(data.getUsername(), data.getPassword(), generateHardware())));
                } else {
                    print("enter the license key: XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX", Color.WHITE);
                    String license = scanner.nextLine();
                    code = findByCode(restClient.get(REGISTRATION.name, registration(data.getUsername(), data.getPassword(), generateHardware(), generateUUIDKey(license))));
                }
                check(code);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

    }

    private static void check(StatusCode code) {
        print(code.message, Color.BLUE);
        if (code == REGISTRATION_SUCCESS_110 || code == LOGIN_SUCCESS_101) {
            print("THE PROGRAM IS REAL", Color.GREEN);
        } else {
            print("THE PROGRAM IS STOLEN", Color.RED);
        }
    }
}
