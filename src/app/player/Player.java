package app.player;

import app.Admin;
import app.audio.Collections.Album;
import app.audio.Collections.AudioCollection;
import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import app.audio.LibraryEntry;
import app.user.Artist;
import app.user.User;
import app.utils.Enums;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Player.
 */
public final class Player {
    private Enums.RepeatMode repeatMode;
    private boolean shuffle;
    private boolean paused;
    @Getter
    private PlayerSource source;
    @Getter
    private String type;
    private final int skipTime = 90;
    @Getter
    private String ownerPlayer;

    private ArrayList<PodcastBookmark> bookmarks = new ArrayList<>();

    public void setOwnerPlayer(final String ownerPlayer) {
        this.ownerPlayer = ownerPlayer;
    }

    /**
     * Instantiates a new Player.
     */
    public Player() {
        this.repeatMode = Enums.RepeatMode.NO_REPEAT;
        this.paused = true;
    }

    /**
     * Stop.
     */
    public void stop() {
        if ("podcast".equals(this.type)) {
            bookmarkPodcast();
        }

        repeatMode = Enums.RepeatMode.NO_REPEAT;
        paused = true;
        source = null;
        shuffle = false;
        //System.out.println("stop");
    }

    private void bookmarkPodcast() {
        if (source != null && source.getAudioFile() != null) {
            PodcastBookmark currentBookmark =
                    new PodcastBookmark(source.getAudioCollection().getName(),
                            source.getIndex(),
                            source.getDuration());
            bookmarks.removeIf(bookmark -> bookmark.getName().equals(currentBookmark.getName()));
            bookmarks.add(currentBookmark);
        }
    }

    /**
     * Create source player source.
     *
     * @param type      the type
     * @param entry     the entry
     * @param bookmarks the bookmarks
     * @return the player source
     */
    public static PlayerSource createSource(final String type,
                                            final LibraryEntry entry,
                                            final List<PodcastBookmark> bookmarks) {
        if ("song".equals(type)) {
            return new PlayerSource(Enums.PlayerSourceType.LIBRARY, (AudioFile) entry);
        } else if ("playlist".equals(type)) {
            return new PlayerSource(Enums.PlayerSourceType.PLAYLIST, (AudioCollection) entry);
        } else if ("podcast".equals(type)) {
            return createPodcastSource((AudioCollection) entry, bookmarks);
        } else if ("album".equals(type)) {
            return new PlayerSource(Enums.PlayerSourceType.ALBUM, (AudioCollection) entry);
        }

        return null;
    }

    private static PlayerSource createPodcastSource(final AudioCollection collection,
                                                    final List<PodcastBookmark> bookmarks) {
        for (PodcastBookmark bookmark : bookmarks) {
            if (bookmark.getName().equals(collection.getName())) {
                return new PlayerSource(Enums.PlayerSourceType.PODCAST, collection, bookmark);
            }
        }
        return new PlayerSource(Enums.PlayerSourceType.PODCAST, collection);
    }

    /**
     * Sets source.
     *
     * @param entry      the entry
     * @param sourceType the sourceType
     */
    public void setSource(final LibraryEntry entry, final String sourceType) {
        if ("podcast".equals(this.type)) {
            bookmarkPodcast();
        }

        this.type = sourceType;
        this.source = createSource(sourceType, entry, bookmarks);
        this.repeatMode = Enums.RepeatMode.NO_REPEAT;
        this.shuffle = false;
        this.paused = true;
    }

    /**
     * Pause.
     */
    public void pause() {
        paused = !paused;
    }

    /**
     * Shuffle.
     *
     * @param seed the seed
     */
    public void shuffle(final Integer seed) {
        if (seed != null) {
            source.generateShuffleOrder(seed);
        }

        if (source.getType() == Enums.PlayerSourceType.PLAYLIST
                || source.getType() == Enums.PlayerSourceType.ALBUM) {
            shuffle = !shuffle;
            if (shuffle) {
                source.updateShuffleIndex();
            }
        }
    }

    /**
     * Repeat enums . repeat mode.
     *
     * @return the enums . repeat mode
     */
    public Enums.RepeatMode repeat() {
        if (repeatMode == Enums.RepeatMode.NO_REPEAT) {
            if (source.getType() == Enums.PlayerSourceType.LIBRARY) {
                repeatMode = Enums.RepeatMode.REPEAT_ONCE;
            } else {
                repeatMode = Enums.RepeatMode.REPEAT_ALL;
            }
        } else {
            if (repeatMode == Enums.RepeatMode.REPEAT_ONCE) {
                repeatMode = Enums.RepeatMode.REPEAT_INFINITE;
            } else {
                if (repeatMode == Enums.RepeatMode.REPEAT_ALL) {
                    repeatMode = Enums.RepeatMode.REPEAT_CURRENT_SONG;
                } else {
                    repeatMode = Enums.RepeatMode.NO_REPEAT;
                }
            }
        }

        return repeatMode;
    }

    public String getArtist(final String song) {
        for (Song sg : Admin.getInstance().getSongs()) {
            if (sg.getName().equals(song)) {
                return sg.getArtist();
            }
        }
        return null;
    }

    /**
     * Simulate player.
     *
     * @param time the time
     */
    public void simulatePlayer(final int time) {
        int elapsedTime = time;
        if (!paused) {
            while (elapsedTime >= source.getDuration()) {
                if (Admin.getInstance().getSong(
                        source.getAudioFile().getName()) != null
                        && Admin.getInstance().getSong(
                                source.getAudioFile().getName()).
                        getDuration() != null
                        && Admin.getInstance().getSong(source.getAudioFile().getName()).
                        getDuration() == source.getDuration()) {
                    User user = Admin.getInstance().getUser(ownerPlayer);
                    if (type.equals("album")) {
                        String name = getArtist(source.getAudioFile().getName());
                        Artist artist = Admin.getInstance().getArtist(name);
                        user.updateStatsSong(source.getAudioFile());
                        Album album = Admin.getInstance().getAlbum(
                                source.getAudioCollection().getName());
                        user.updateStatsAlbum(album);
                        artist.updateListens(source.getAudioFile().getName(),
                                user.getUsername());
                    }
                    if (type.equals("song")) {
                        //System.out.println(source.getRemainedDuration());
                        Song song = Admin.getInstance().getSong(
                                source.getAudioFile().getName());
                        Album album = Admin.getInstance().getAlbum(
                                song.getAlbum());
                        user.updateStatsSong(song);
                        //System.out.println("am inreg pt din song1" + song.getName());
                        user.updateStatsAlbum(album);
                        Artist artist = Admin.getInstance().getArtist(
                                song.getArtist());
                        artist.updateListens(source.getAudioFile().getName(),
                                user.getUsername());
                    }
                }
                elapsedTime -= source.getDuration();
                next();
                if (paused) {
                    break;
                }
                if (elapsedTime < source.getDuration()) {
                    User user = Admin.getInstance().getUser(ownerPlayer);
                    Song song = Admin.getInstance().getSong(
                            source.getAudioFile().getName());
                    if (song != null) {
                        Album album = Admin.getInstance().getAlbum(song.getAlbum());
                        user.updateStatsSong(song);
                        user.updateStatsAlbum(album);
                        Artist artist = Admin.getInstance().getArtist(
                                song.getArtist());
                        artist.updateListens(source.getAudioFile().getName(),
                                user.getUsername());
                    }
                }
            }
            if (source != null && type.equals("song")) {
                User user = Admin.getInstance().getUser(ownerPlayer);
                Song song = Admin.getInstance().getSong(
                        source.getAudioFile().getName());
                Album album = Admin.getInstance().getAlbum(song.getAlbum());
                user.updateStatsSong(song);
                user.updateStatsAlbum(album);
                Artist artist = Admin.getInstance().getArtist(song.getArtist());
                artist.updateListens(source.getAudioFile().getName(),
                        user.getUsername());
            }
            if (!paused) {
                source.skip(-elapsedTime);
            }
        }
    }

    /**
     * Next.
     */
    public void next() {
        paused = source.setNextAudioFile(repeatMode, shuffle);
        if (repeatMode == Enums.RepeatMode.REPEAT_ONCE) {
            repeatMode = Enums.RepeatMode.NO_REPEAT;
        }

        if (source.getDuration() == 0 && paused) {
            stop();
        }
    }

    /**
     * Prev.
     */
    public void prev() {
        source.setPrevAudioFile(shuffle);
        paused = false;
    }

    private void skip(final int duration) {
        source.skip(duration);
        paused = false;
    }

    /**
     * Skip next.
     */
    public void skipNext() {
        if (source.getType() == Enums.PlayerSourceType.PODCAST) {
            skip(-skipTime);
        }
    }

    /**
     * Skip prev.
     */
    public void skipPrev() {
        if (source.getType() == Enums.PlayerSourceType.PODCAST) {
            skip(skipTime);
        }
    }

    /**
     * Gets current audio file.
     *
     * @return the current audio file
     */
    public AudioFile getCurrentAudioFile() {
        if (source == null) {
            return null;
        }
        return source.getAudioFile();
    }

    /**
     * Gets current audio collection.
     *
     * @return the current audio collection
     */
    public AudioCollection getCurrentAudioCollection() {
        if (source == null) {
            return null;
        }
        return source.getAudioCollection();
    }

    /**
     * Gets paused.
     *
     * @return the paused
     */
    public boolean getPaused() {
        return paused;
    }

    /**
     * Gets shuffle.
     *
     * @return the shuffle
     */
    public boolean getShuffle() {
        return shuffle;
    }

    /**
     * Gets stats.
     *
     * @return the stats
     */
    public PlayerStats getStats() {
        String filename = "";
        int duration = 0;
        if (source != null && source.getAudioFile() != null) {
            filename = source.getAudioFile().getName();
            duration = source.getDuration();
        } else {
            stop();
        }

        return new PlayerStats(filename, duration, repeatMode, shuffle, paused);
    }
}
