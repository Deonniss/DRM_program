import data.UserData;
import handler.ComputerIdentifierHandler;
import org.json.JSONObject;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import rest.RestClient;
import rest.RestRequest;
import rest.StatusCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final RestClient restClient = RestClient.getInstance();
    private static final String REGEX_KEY = "^\\w{4}-\\w{4}-\\w{4}-\\w{4}$";


    public static void main6(String[] args) {

        MultiValueMap<String, String> ma = new LinkedMultiValueMap<>();

//        MultiM<String, String> m = new MultiValueMapAdapter<>();
        ma.add("username", "den1");
        ma.add("password", "0000");
        System.out.println(StatusCode.findByCode(restClient.get("/login", ma)).code);
    }
    public static void main(String[] args) {


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

                    MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
                    requestParams.add("username", params[2].toLowerCase());
                    requestParams.add("password", params[4].toLowerCase());

                    StatusCode code;
                    String hardwareID;

                    if (params[0].equalsIgnoreCase("log")) {
                        code = StatusCode.findByCode(restClient.get(RestRequest.LOGIN.name, requestParams));
                    } else {
                        code = StatusCode.findByCode(restClient.get(RestRequest.REGISTRATION.name, requestParams));
                    }
                    requestParams.clear();
                    System.out.println(code.message);

                    switch (code) {
                        case LOGIN_SUCCESS_101:
                            //генерация и отправка hardware и проверка уникальности
                            hardwareID = ComputerIdentifierHandler.generateLicenseKey();
                            requestParams.add("username", params[2].toLowerCase());
                            requestParams.add("hardware", hardwareID);
                            code = StatusCode.findByCode(restClient.get(RestRequest.HARDWARE.name, requestParams));
                            requestParams.clear();

                            System.out.println(code.message);
                            if (code == StatusCode.HARDWARE_SUCCESS_130) {
                                System.err.println("THE PROGRAM IS REAL");
                            } else {
                                System.err.println("THE PROGRAM IS STOLEN");
                            }
                            break;
                        case REGISTRATION_SUCCESS_110:
                            System.out.println("enter the license key: XXXX-XXXX-XXXX-XXXX");
                            in = scanner.nextLine();
                            if (in.matches(REGEX_KEY)) {
                                requestParams.add("username", params[2].toLowerCase());
                                requestParams.add("key", in);
                                code = StatusCode.findByCode(restClient.get(RestRequest.LICENSE.name, requestParams));
                                requestParams.clear();

                                System.out.println(code.message);
                                if (code == StatusCode.LICENSE_SUCCESS_120) {
                                    hardwareID = ComputerIdentifierHandler.generateLicenseKey();
                                    requestParams.add("username", params[2].toLowerCase());
                                    requestParams.add("hardware", hardwareID);
                                    code = StatusCode.findByCode(restClient.get(RestRequest.HARDWARE_ADD.name, requestParams));
                                    requestParams.clear();
                                    System.out.println(code.message);
                                }
                            }
                            //ввод ключа. Проверка его на сервере.
                            break;
                        default:
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
