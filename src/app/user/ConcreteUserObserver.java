package app.user;

public class ConcreteUserObserver implements UserObserver {
    private String name;

    public ConcreteUserObserver(final String name) {
        this.name = name;
    }

    @Override
/**
 * Updates the current object based on changes from an observed user.
 *
 * @param user    The user whose changes triggered the update.
 * @param message The message associated with the update.
 */
    public final void update(final UserAbstract user, final String message) {
        System.out.println(name + " received update from " + user.getUsername() + ": " + message);
    }
}
