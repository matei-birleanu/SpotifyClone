package app.user;

public interface UserObservable {
    void addObserver(UserObserver observer);
    void removeObserver(UserObserver observer);
    void notifyObservers(String message);
}
