package Singleton;


//TODO E.GAMMA SAYS SINGLETONS ARE HATED FOR ITS DESIGN SMELL
//TODO EXTENSIBLE AND TESTABLE LARDA PROBLEM

import java.io.*;

class BasicSingleton implements Serializable {

    private static final BasicSingleton INSTANCE = new BasicSingleton();

    private int value = 0;

    //TODO PROBLEM 1 REFLECTION KULLANARA BU PRIVATE CONSTRUCTOR KULLANILABILIR PUBLIC YAPABILIRSIN
    private BasicSingleton() {

    }

    public static BasicSingleton getInstance() {
        return INSTANCE;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    //SERIALIZATION PROBLEM FIX
    protected Object readResolve() {
        return INSTANCE;
    }

}

public class SinglT {

    static void saveToFile(BasicSingleton singleton, String fileName) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
        try {
            out.writeObject(singleton);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static BasicSingleton readFromFile(String fileName) throws Exception {
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);

        try {
            return (BasicSingleton) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws Exception {
        /*BasicSingleton br = BasicSingleton.getInstance();
        br.setValue(12);
        System.out.println(br.getValue());

         */

        //TODO PROBLEM NUMBER 2 SERIALIZATION

        BasicSingleton basicSingleton = BasicSingleton.getInstance();
        basicSingleton.setValue(11);


        //! SINGLETON.BIN
        String fileName = "singleton.bin";
        saveToFile(basicSingleton, fileName);

        basicSingleton.setValue(222);

        BasicSingleton basicSingleton1 = readFromFile(fileName);

        System.out.println(basicSingleton == basicSingleton1);
        System.out.println(basicSingleton1.getValue() + "<->" + basicSingleton.getValue());


    }

}
