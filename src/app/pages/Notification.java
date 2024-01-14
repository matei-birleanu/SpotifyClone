package app.pages;

public class Notification {
    String name;
    String description;

    public Notification() {
    }

    public Notification(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
