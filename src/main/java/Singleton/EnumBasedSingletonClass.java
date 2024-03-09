package Singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.*;

enum EnumBasedSingleton {
    INSTANCE;

    private int value;


    EnumBasedSingleton() {
        value = 42;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}


class Demo {
    static void saveToFile(EnumBasedSingleton singleton, String fileName) throws Exception {
        FileOutputStream fl = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fl);
        objectOutputStream.writeObject(singleton);

    }

    static EnumBasedSingleton readFromFile(String fileName) throws Exception {
        FileInputStream fl = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fl);
        return (EnumBasedSingleton) objectInputStream.readObject();
    }

    public static void main(String[] args) throws Exception {
        //"myfile.bin"
        //TODO ENUM ONLY FILENAME IS SAVED LIKE INSTANCE* BUT OTHER THAN THAT THE VALUES COMING FROM SETTER  IS NOT SAVED
        // WHEN YOU COMMENT OUT 49-50 AND READ THE WRITTEN FILE YOU WILL GET THE VALUE OF 42 ANYWAYS
        String filename = "myfile.bin";
        EnumBasedSingleton singleton = EnumBasedSingleton.INSTANCE;
        singleton.setValue(111);
        saveToFile(singleton, filename);

        EnumBasedSingleton singleton1 = readFromFile(filename);
        System.out.println(singleton1 == singleton);
        System.out.println(singleton1.getValue() == singleton.getValue());
    }
}


public class EnumBasedSingletonClass {
}
