package handler;

import rest.RestClient;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.Base64;

public class CryptoHandler {

//    private static final String PUBLIC_KEY_FILE = "publicKey";
//    private static final String CIPHER = "RSA";
//
//    private CryptoHandler() {}
//
//    public static String encryptData(String message) {
//        try {
//            Cipher encryptCipher = Cipher.getInstance(CIPHER);
//            PublicKey publicKey = generatePublicKey();
//            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
//            byte[] secretMessageBytes = message.getBytes(StandardCharsets.UTF_8);
//            byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
//            return Base64.getEncoder().encodeToString(encryptedMessageBytes);
//        } catch (Exception e) {
//            return "s";
//        }
//    }

//    public static boolean getPermission(String encodedMessage) {
//        return (Boolean) RestClient.getInstance().get("/login", encodedMessage);
//    }

//    public static PublicKey generatePublicKey() {
//        PublicKey publicKey = (PublicKey) FileHandler.getData(PUBLIC_KEY_FILE);
//        if (publicKey == null) {
//            publicKey = getPublicKey();
//            FileHandler.saveData(PUBLIC_KEY_FILE, publicKey);
//        }
//        return publicKey;
//    }

//    private static PublicKey getPublicKey() {
//        return (PublicKey) RestClient.getInstance().get("/publicKey", "");
//    }
}
