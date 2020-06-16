package data;

public interface TempObservable extends Runnable {
    void register(TempListener listener);
}