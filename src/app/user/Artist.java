package app.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.Admin;
import app.audio.Collections.Album;
import app.audio.Collections.AlbumOutput;
import app.audio.Files.Song;
import app.pages.ArtistPage;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Artist.
 */
public final class Artist extends ContentCreator {
    private ArrayList<Album> albums;
    private ArrayList<Merchandise> merch;
    private ArrayList<Event> events;
    private HashMap <String, Integer> fans = new HashMap<>();
    private HashMap <String, Integer> songListens = new HashMap<>();
    @Getter
    private HashMap<String,Integer> popularAlbums = new HashMap<>();
    @Getter
    @Setter
    private int sales;
    @Getter
    @Setter
    private int merchRevenue = 0;

    /**
     * Instantiates a new Artist.
     *
     * @param username the username
     * @param age      the age
     * @param city     the city
     */
    public Artist(final String username, final int age, final String city) {
        super(username, age, city);
        albums = new ArrayList<>();
        merch = new ArrayList<>();
        events = new ArrayList<>();

        super.setPage(new ArtistPage(this));
    }
    public Merchandise getAMerch(String name){
        for(Merchandise merch : merch){
            if(merch.getName().equals(name)){
                return merch;
            }
        }
        return null;
    }
    public HashMap<String, Integer> getSongListens() {
        return songListens;
    }

    public void setSongListens(HashMap<String, Integer> songListens) {
        this.songListens = songListens;
    }

    public HashMap<String, Integer> getFans() {
        return fans;
    }

    public void setFans(HashMap<String, Integer> fans) {
        this.fans = fans;
    }

    /**
     * Gets albums.
     *
     * @return the albums
     */
    public ArrayList<Album> getAlbums() {
        return albums;
    }

    /**
     * Gets merch.
     *
     * @return the merch
     */
    public ArrayList<Merchandise> getMerch() {
        return merch;
    }

    /**
     * Gets events.
     *
     * @return the events
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * Gets event.
     *
     * @param eventName the event name
     * @return the event
     */
    public Event getEvent(final String eventName) {
        for (Event event : events) {
            if (event.getName().equals(eventName)) {
                return event;
            }
        }

        return null;
    }

    /**
     * Gets album.
     *
     * @param albumName the album name
     * @return the album
     */
    public Album getAlbum(final String albumName) {
        for (Album album : albums) {
            if (album.getName().equals(albumName)) {
                return album;
            }
        }

        return null;
    }

    /**
     * Gets all songs.
     *
     * @return the all songs
     */
    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        albums.forEach(album -> songs.addAll(album.getSongs()));

        return songs;
    }

    /**
     * Show albums array list.
     *
     * @return the array list
     */
    public ArrayList<AlbumOutput> showAlbums() {
        ArrayList<AlbumOutput> albumOutput = new ArrayList<>();
        for (Album album : albums) {
            albumOutput.add(new AlbumOutput(album));
        }

        return albumOutput;
    }
    public void updateListens(String file, String fan){
        if(fans.containsKey(fan)){
            Integer listens = fans.get(fan);
            listens++;
            fans.put(fan, listens);
        }
        else {
            fans.put(fan, 1);
        }
        if(songListens.containsKey(file)){
            Integer listens = songListens.get(file);
            listens++;
            songListens.put(file,listens);
        }
        else{
            songListens.put(file, 1);
        }

    }
    public void putAlbumStats(Album album, User user){
        if(popularAlbums.containsKey(album.getName())){
            int a = popularAlbums.get(album.getName());
            a = a + user.getBestAlbums().get(album.getName());
            popularAlbums.put(album.getName(), a);
        }
        else{
            //System.out.println("pibk " + user.getBestAlbums().get(album.getName()));
            popularAlbums.put(album.getName(),user.getBestAlbums().get(album.getName()));
        }
    }
    public void createAlbumStats(){
        for(User usr : Admin.getInstance().getUsers()){
           for(Map.Entry<String,Integer> entry : usr.getBestAlbums().entrySet()){
               //System.out.println( "Artist "+entry.getKey() + " " + entry.getValue());
               Album album = Admin.getInstance().getAlbum(entry.getKey());
               if(album.getOwner().equals(getUsername())){
                    putAlbumStats(album,usr);
               }
           }

        }

    }
    /**
     * Get user type
     *
     * @return user type string
     */
    public String userType() {
        return "artist";
    }
}
