package app.pages;

import app.user.Artist;
import app.user.Host;
import app.user.User;
import app.user.UserAbstract;

public class PageFactory {
    public static Page createPage(final String pageType, final UserAbstract user) {
        return switch (pageType.toLowerCase()) {
            case "liked" -> new LikedContentPage((User) user);
            case "home" -> new HomePage((User) user);
            case "artist" -> new ArtistPage((Artist) user);
            case "host" -> new HostPage((Host) user);
            default -> throw new IllegalArgumentException("Invalid page type: " + pageType);
        };
    }
}
