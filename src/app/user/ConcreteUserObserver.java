package app.user;

public class ConcreteUserObserver implements UserObserver {
    private String name;

    public ConcreteUserObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(UserAbstract user, String message) {
        System.out.println(name + " received update from " + user.getUsername() + ": " + message);
    }
}
