package app.user;

public interface UserObservable {
    /**
     * Adds an observer to the list of observers.
     *
     * @param observer The observer to be added.
     */
    void addObserver(UserObserver observer);

    /**
     * Removes an observer from the list of observers.
     *
     * @param observer The observer to be removed.
     */
    void removeObserver(UserObserver observer);

    /**
     * Notifies all observers with a specific message.
     *
     * @param message The message to be sent to all observers.
     */
    void notifyObservers(String message);

}
