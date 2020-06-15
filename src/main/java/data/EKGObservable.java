package data;

public interface EKGObservable {
    void registerGUI(EKGListener listenerGUI);
    void registerDB(EKGListener listenerDB);
}
