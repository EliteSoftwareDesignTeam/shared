package com.teamness.smane;

import java.io.*;
import java.util.Base64;

public class Serialisation {

    public static Object toObject(String str) throws IOException, ClassNotFoundException {
        byte[] bytes = Base64.getDecoder().decode(str);
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            return ois.readObject();
        }
    }

    public static String fromObject(Serializable obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try(ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        }
    }

}
