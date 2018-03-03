package com.teamness.smane;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.Base64;

public class Serialisation {

    public static Object toObject(String str) throws IOException, ClassNotFoundException {
        byte[] bytes = DatatypeConverter.parseBase64Binary(str);
        return deserialiseBytes(bytes);
    }

    private static Object deserialiseBytes(byte[] bytes) throws ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            return ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String fromObject(Serializable obj) throws IOException {
        byte[] serialised = serialiseObject(obj);
        return DatatypeConverter.printBase64Binary(serialised);
    }

    public static byte[] serialiseObject(Serializable obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try(ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
