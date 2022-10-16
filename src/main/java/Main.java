import data.DataID;
import data.UserData;
import handler.ComputerIdentifierHandler;
import handler.CryptoHandler;
import handler.UserDataHandler;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {

        //получение данных пользователя (папка temp). Если нету ввод пароля.
        UserData userData = UserDataHandler.generateUserData();

        //сбор аппаратных данных
        String hardwareID = ComputerIdentifierHandler.generateLicenseKey();

        //генерация и отправка пакета (user-data + secret-key + аппаратные данные)
        DataID dataID = new DataID();
        dataID.setLogin(userData.getLogin());
        dataID.setPassword(userData.getPassword());
        dataID.setHardwareID(hardwareID);
        String secret = JSONObject.valueToString(dataID);

        //шифрование
        String encodedMessage = CryptoHandler.encryptData(secret);

        //получение ответа
        if (CryptoHandler.getPermission(encodedMessage)) {
            System.out.println("PROGRAM IS ACTIVATED");
        } else {
            System.out.println("ACTIVATION ERROR");
        }
    }
}
