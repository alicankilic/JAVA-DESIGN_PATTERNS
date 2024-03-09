package Singleton;


import java.io.File;
import java.io.IOException;

class StaticBlockSingleton {
    private StaticBlockSingleton() throws IOException {
        System.out.println("singleton init");
        File.createTempFile(".", ".");
    }

    private static StaticBlockSingleton instance;

    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (IOException e) {
            System.out.println("failed to create singleton");

        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}


public class SingletonStaticVariation {
}
