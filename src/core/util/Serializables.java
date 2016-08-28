package core.util;

import java.io.*;
import java.nio.file.Path;

public class Serializables {

    private Serializables() {
        // TODO Auto-generated constructor stub
    }

    public static void serializeToFile(Path filePath, Object object) {
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(object);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static <T> T deserializeFromFile(Path filePath) {
        T deserializedObject = null;
        try (FileInputStream fis = new FileInputStream(filePath.toFile());
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            deserializedObject = (T) ois.readObject();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return deserializedObject;
    }
}
