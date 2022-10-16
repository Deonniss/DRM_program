package handler;

import java.io.*;

public class FileHandler {

    private FileHandler() {}

    public static void saveData(String tempFileName, Object data) {
        try {
            FileOutputStream outputStream = new FileOutputStream(tempFileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getData(String tempFileName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(tempFileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
