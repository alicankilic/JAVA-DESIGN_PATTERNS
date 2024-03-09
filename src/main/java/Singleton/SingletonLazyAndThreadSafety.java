package Singleton;


//TODO CORE LAZY SINGLETON THREAD SAFETY LAZY INIT..
class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("initializing lazy singleton");
    }

    //TODO BELOW THIS ONE IS THE EASY WAY I WOULD PICK THIS
/*
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

 */




/*
    //TODO DOUBLE CHECK LOCK OUTDATED ASK ABOUT INNER LOCK

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

 */
}


class InnerStaticSingleton {

    //TODO THIS APPROACH WORKS TOO

    private InnerStaticSingleton() {

    }

    private static class Imp {
        private static final InnerStaticSingleton inner = new InnerStaticSingleton();


    }


    //
    public InnerStaticSingleton getInstance() {
             return Imp.inner;
    }

}


public class SingletonLazyAndThreadSafety {
}
