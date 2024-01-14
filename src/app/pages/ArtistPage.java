package app.pages;

import app.audio.Collections.Album;
import app.user.Artist;
import app.user.ContentCreator;
import app.user.Event;
import app.user.Merchandise;
import lombok.Getter;

import java.util.List;

/**
 * The type Artist page.
 */
public final class ArtistPage implements Page {
    @Getter
    private List<Album> albums;
    private List<Merchandise> merch;
    private List<Event> events;
    private Artist artist;

    /**
     * Instantiates a new Artist page.
     *
     * @param artist the artist
     */
    public ArtistPage(final Artist artist) {
        albums = artist.getAlbums();
        merch = artist.getMerch();
        events = artist.getEvents();
        this.artist = artist;
    }

    @Override
    public String printCurrentPage() {
        return "Albums:\n\t%s\n\nMerch:\n\t%s\n\nEvents:\n\t%s"
                .formatted(albums.stream().map(Album::getName).toList(),
                           merch.stream().map(merchItem -> "%s - %d:\n\t%s"
                                .formatted(merchItem.getName(),
                                           merchItem.getPrice(),
                                           merchItem.getDescription()))
                                .toList(),
                           events.stream().map(event -> "%s - %s:\n\t%s"
                                 .formatted(event.getName(),
                                            event.getDate(),
                                            event.getDescription()))
                                 .toList());
    }

    @Override
    public String display() {
        return "artist";
    }

    @Override
    public String getName() {
        return artist.getUsername();
    }
}
