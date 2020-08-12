package pl.sda.ldz24.finalapp.store;

public class SingletonExample { //nazywany jest antypatternem

    private static SingletonExample INSTANCE;

    private SingletonExample() {
    }

    public static SingletonExample getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonExample.class) { //operacja kosztowna
                if (INSTANCE == null) {
                    INSTANCE = new SingletonExample();
                }
            }
        }
        return INSTANCE;
    }
}
