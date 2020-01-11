package io;

import java.io.*;

public class ObjectStreamDemo {
    public static void main(String[] args) {
        // TODO自动生成的方法存根
        ObjectOutputStream objectWriter = null;
        ObjectInputStream objectReader = null;

        try {
            objectWriter = new ObjectOutputStream(new FileOutputStream("objectStreamTestOut.txt"));
            objectWriter.writeObject(new Student("gg", 22));
            objectWriter.writeObject(new Student("tt", 18));
            objectWriter.writeObject(new Student("rr", 17));
            objectReader = new ObjectInputStream(new FileInputStream("objectStreamTestOut.txt"));
            for (int i = 0; i < 3; i++) {
                System.out.println(objectReader.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            // TODO自动生成的 catch 块
            e.printStackTrace();
        } finally {
            try {
                objectReader.close();
                objectWriter.close();
            } catch (IOException e) {
                // TODO自动生成的 catch 块
                e.printStackTrace();
            }

        }

    }
}
