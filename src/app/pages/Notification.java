package app.pages;

public class Notification {
    private String name;
    private String description;

    public Notification() {
    }

    public Notification(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Retrieves the name associated with the object.
     *
     * @return The name of the object.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the description associated with the object.
     *
     * @return The description of the object.
     */
    public String getDescription() {
        return description;
    }


}
