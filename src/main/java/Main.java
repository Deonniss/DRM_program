import data.UserData;
import handler.ComputerIdentifierHandler;
import org.json.JSONObject;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import rest.RestClient;
import rest.StatusCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final RestClient restClient = RestClient.getInstance();
    private static final String REGEX_KEY = "^\\w{4}-\\w{4}-\\w{4}-\\w{4}$";


    public static void main(String[] args) {

        MultiValueMap<String, String> ma = new LinkedMultiValueMap<>();

//        MultiM<String, String> m = new MultiValueMapAdapter<>();
        ma.add("username", "den1");
        ma.add("password", "0000");
        System.out.println(restClient.get("/login", ma));
    }
    public static void main2(String[] args) {


        String in = "";
        while (!in.equalsIgnoreCase("q")) {

            System.out.println("commands: reg, log. params: -u 'username' -p 'password'\nexamples: reg -u denis -p 0000");
            System.out.println("enter 'q' for exit");

            in = scanner.nextLine();

            if (!in.isBlank()) {
                String[] params = in.split("\\s+");

                if (params.length == 5 && params[1].equalsIgnoreCase("-u") && params[3].equalsIgnoreCase("-p")
                        && (params[0].equalsIgnoreCase("reg") || params[0].equalsIgnoreCase("log")) && params[2].length() > 2
                        && params[2].length() < 10 && params[4].length() > 2 && params[4].length() < 10) {
                    UserData userData = new UserData(params[2].toLowerCase(), params[4].toLowerCase(), params[0].toLowerCase());
                    StatusCode code = null;
//                    StatusCode code = restClient.get("/login", JSONObject.valueToString(userData));

                    System.out.println(code.message);

                    switch (code) {
                        case LOGIN_SUCCESS_101:
                            //генерация и отправка hardware и проверка уникальности
                            String hardwareID = ComputerIdentifierHandler.generateLicenseKey();
//                            code = restClient.get("/check/hardware", hardwareID);
                            code = null;
                            System.out.println(code.message);
                            if (code == StatusCode.HARDWARE_SUCCESS_130) {
                                System.err.println("THE PROGRAM IS REAL");
                            } else {
                                System.err.println("THE PROGRAM IS STOLEN");
                            }
                            in = "q";
                            break;
                        case REGISTRATION_SUCCESS_110:
                            System.out.println("enter the license key: XXXX-XXXX-XXXX-XXXX");
                            in = scanner.nextLine();
                            if (in.matches(REGEX_KEY)) {
                                code = null;
//                                code = restClient.get("/check/license", in);
                                System.out.println(code.message);
                            }
                            //ввод ключа. Проверка его на сервере.
                            break;
                        case LOGIN_FAILED_201:
                        case LOGIN_FAILED_202:
                        case LOGIN_FAILED_203:
                        case REGISTRATION_FAILED_210:
                            System.out.println("Try again!");
                    }
                }


            }


        }


        //получение данных пользователя (папка temp). Если нету ввод пароля.
//        UserData userData = UserDataHandler.generateUserData();

        //сбор аппаратных данных


        //генерация и отправка пакета (user-data + secret-key + аппаратные данные)
//        DataID dataID = new DataID();
//        dataID.setLogin(userData.getLogin());
//        dataID.setPassword(userData.getPassword());
//        dataID.setHardwareID(hardwareID);
//        String secret = JSONObject.valueToString(dataID);

        //шифрование
//        String encodedMessage = CryptoHandler.encryptData(secret);

        //получение ответа
//        if (CryptoHandler.getPermission(encodedMessage)) {
//            System.out.println("PROGRAM IS ACTIVATED");
//        } else {
//            System.out.println("ACTIVATION ERROR");
//        }
    }
}
