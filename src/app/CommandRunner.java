package app;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;

import app.user.*;
import com.fasterxml.jackson.databind.node.ArrayNode;

import com.fasterxml.jackson.databind.node.ObjectNode;
import app.audio.Collections.*;
import app.pages.Notification;
import app.player.PlayerStats;
import app.searchBar.Filters;
import com.fasterxml.jackson.databind.ObjectMapper;

import fileio.input.CommandInput;


import java.util.*;

/**
 * The type Command runner.
 */
public final class CommandRunner {
    /**
     * The Object mapper.
     */
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Admin admin;

    /**
     * Update admin.
     */
    public static void updateAdmin() {
        admin = Admin.getInstance();
    }

    private CommandRunner() {
    }

    /**
     * Search object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode search(final CommandInput commandInput) {
        User user = admin.getUser(commandInput.getUsername());
        Filters filters = new Filters(commandInput.getFilters());
        String type = commandInput.getType();
        ArrayList<String> results = new ArrayList<>();
        String message = "%s is offline.".formatted(user.getUsername());

        if (user.isStatus()) {
            results = user.search(filters, type);
            message = "Search returned " + results.size() + " results";
        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);
        objectNode.put("results", objectMapper.valueToTree(results));

        return objectNode;
    }

    /**
     * Select object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode select(final CommandInput commandInput) {
        User user = admin.getUser(commandInput.getUsername());

        String message = user.select(commandInput.getItemNumber());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Load object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode load(final CommandInput commandInput) {
        User user = admin.getUser(commandInput.getUsername());
        String message = user.load();
        if (user.getPlayer().getSource() != null) {
            AudioFile file = user.getPlayer().getSource().getAudioFile();
            for (Song song : Admin.getInstance().getSongs()) {
                if (file.getName().equals(song.getName())) {
                    Artist artist = Admin.getInstance().getArtist(song.getArtist());
                    if (artist != null) {
                        artist.setPlayed(true);
                    }
                }
            }
        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Play pause object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode playPause(final CommandInput commandInput) {
        User user = admin.getUser(commandInput.getUsername());
        String message = user.playPause();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Repeat object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode repeat(final CommandInput commandInput) {
        User user = admin.getUser(commandInput.getUsername());
        String message = user.repeat();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Shuffle object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode shuffle(final CommandInput commandInput) {
        User user = admin.getUser(commandInput.getUsername());
        Integer seed = commandInput.getSeed();
        String message = user.shuffle(seed);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Forward object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode forward(final CommandInput commandInput) {
        User user = admin.getUser(commandInput.getUsername());
        String message = user.forward();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Backward object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode backward(final CommandInput commandInput) {
        User user = admin.getUser(commandInput.getUsername());
        String message = user.backward();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Like object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode like(final CommandInput commandInput) {
        User user = admin.getUser(commandInput.getUsername());
        String message = user.like();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Next object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode next(final CommandInput commandInput) {
        User user = admin.getUser(commandInput.getUsername());
        String message = user.next();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Prev object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode prev(final CommandInput commandInput) {
        User user = admin.getUser(commandInput.getUsername());
        String message = user.prev();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Create playlist object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode createPlaylist(final CommandInput commandInput) {
        User user = admin.getUser(commandInput.getUsername());
        String message = user.createPlaylist(commandInput.getPlaylistName(),
                commandInput.getTimestamp());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Add remove in playlist object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addRemoveInPlaylist(final CommandInput commandInput) {
        User user = admin.getUser(commandInput.getUsername());
        String message = user.addRemoveInPlaylist(commandInput.getPlaylistId());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Switch visibility object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode switchVisibility(final CommandInput commandInput) {
        User user = admin.getUser(commandInput.getUsername());
        String message = user.switchPlaylistVisibility(commandInput.getPlaylistId());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Show playlists object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showPlaylists(final CommandInput commandInput) {
        User user = admin.getUser(commandInput.getUsername());
        ArrayList<PlaylistOutput> playlists = user.showPlaylists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     * Follow object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode follow(final CommandInput commandInput) {
        User user = admin.getUser(commandInput.getUsername());
        String message = user.follow();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Status object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode status(final CommandInput commandInput) {
        User user = admin.getUser(commandInput.getUsername());
        PlayerStats stats = user.getPlayerStats();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("stats", objectMapper.valueToTree(stats));

        return objectNode;
    }

    /**
     * Show liked songs object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showLikedSongs(final CommandInput commandInput) {
        User user = admin.getUser(commandInput.getUsername());
        ArrayList<String> songs = user.showPreferredSongs();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     * Gets preferred genre.
     *
     * @param commandInput the command input
     * @return the preferred genre
     */
    public static ObjectNode getPreferredGenre(final CommandInput commandInput) {
        User user = admin.getUser(commandInput.getUsername());
        String preferredGenre = user.getPreferredGenre();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(preferredGenre));

        return objectNode;
    }

    /**
     * Switch connection status object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode switchConnectionStatus(final CommandInput commandInput) {
        String message = admin.switchStatus(commandInput.getUsername());
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Add user object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addUser(final CommandInput commandInput) {
        String message = admin.addNewUser(commandInput);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Delete user object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode deleteUser(final CommandInput commandInput) {
        String message = admin.deleteUser(commandInput.getUsername());
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Add album object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addAlbum(final CommandInput commandInput) {
        String message = admin.addAlbum(commandInput);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Remove album object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode removeAlbum(final CommandInput commandInput) {
        String message = admin.removeAlbum(commandInput);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Show albums object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showAlbums(final CommandInput commandInput) {
        Artist artist = admin.getArtist(commandInput.getUsername());
        ArrayList<AlbumOutput> albums = artist.showAlbums();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(albums));

        return objectNode;
    }

    /**
     * Add event object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addEvent(final CommandInput commandInput) {
        String message = admin.addEvent(commandInput);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Remove event object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode removeEvent(final CommandInput commandInput) {
        String message = admin.removeEvent(commandInput);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Add podcast object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addPodcast(final CommandInput commandInput) {
        String message = admin.addPodcast(commandInput);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Remove podcast object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode removePodcast(final CommandInput commandInput) {
        String message = admin.removePodcast(commandInput);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Show podcasts object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showPodcasts(final CommandInput commandInput) {
        Host host = admin.getHost(commandInput.getUsername());
        List<PodcastOutput> podcasts = host.getPodcasts().stream().map(PodcastOutput::new).toList();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(podcasts));

        return objectNode;
    }

    /**
     * Add merch object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addMerch(final CommandInput commandInput) {
        String message = admin.addMerch(commandInput);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Add announcement object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addAnnouncement(final CommandInput commandInput) {
        String message = admin.addAnnouncement(commandInput);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Remove announcement object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode removeAnnouncement(final CommandInput commandInput) {
        String message = admin.removeAnnouncement(commandInput);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Gets online users.
     *
     * @param commandInput the command input
     * @return the online users
     */
    public static ObjectNode getOnlineUsers(final CommandInput commandInput) {
        List<String> onlineUsers = admin.getOnlineUsers();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(onlineUsers));

        return objectNode;
    }

    /**
     * Gets all users.
     *
     * @param commandInput the command input
     * @return the all users
     */
    public static ObjectNode getAllUsers(final CommandInput commandInput) {
        List<String> users = admin.getAllUsers();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(users));

        return objectNode;
    }

    /**
     * Change page object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode changePage(final CommandInput commandInput) {
        String message = admin.changePage(commandInput);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Print current page object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode printCurrentPage(final CommandInput commandInput) {
        String message = admin.printCurrentPage(commandInput);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Gets top 5 album list.
     *
     * @param commandInput the command input
     * @return the top 5 album list
     */
    public static ObjectNode getTop5AlbumList(final CommandInput commandInput) {
        List<String> albums = admin.getTop5AlbumList();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(albums));

        return objectNode;
    }

    /**
     * Gets top 5 artist list.
     *
     * @param commandInput the command input
     * @return the top 5 artist list
     */
    public static ObjectNode getTop5ArtistList(final CommandInput commandInput) {
        List<String> artists = admin.getTop5ArtistList();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(artists));

        return objectNode;
    }

    /**
     * Gets top 5 songs.
     *
     * @param commandInput the command input
     * @return the top 5 songs
     */
    public static ObjectNode getTop5Songs(final CommandInput commandInput) {
        List<String> songs = admin.getTop5Songs();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     * Gets top 5 playlists.
     *
     * @param commandInput the command input
     * @return the top 5 playlists
     */
    public static ObjectNode getTop5Playlists(final CommandInput commandInput) {
        List<String> playlists = admin.getTop5Playlists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     * Processes a wrapped command based on the provided command input.
     *
     * @param commandInput The input containing information for the wrapped command.
     * @return An ObjectNode containing the result of the wrapped command processing.
     */

    public static ObjectNode wrapped(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        Artist artist = Admin.getInstance().getArtist(commandInput.getUsername());
        if (user == null && artist != null) {
            artist.createAlbumStats();
            List<Map.Entry<String, Integer>> entryListAlbum = new ArrayList<>(
                    artist.getPopularAlbums().entrySet());
            entryListAlbum = Admin.getInstance().sortStats(entryListAlbum);

            List<Map.Entry<String, Integer>> entryListSong = new ArrayList<>(
                    artist.getSongListens().entrySet());
            entryListSong = Admin.getInstance().sortStats(entryListSong);

            List<Map.Entry<String, Integer>> entryListFans = new ArrayList<>(
                    artist.getFans().entrySet());
            entryListFans = Admin.getInstance().sortStats(entryListFans);
            ArrayList<String> topFansList = new ArrayList<>();
            int ok = 0;
            for (Map.Entry<String, Integer> entry : entryListFans) {
                if (ok == 5) {
                    break;
                }
                topFansList.add(entry.getKey());
            }
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", commandInput.getCommand());
            objectNode.put("user", commandInput.getUsername());
            objectNode.put("timestamp", commandInput.getTimestamp());
            objectNode.put("result", commandInput.getTimestamp());
            ObjectNode resultNode = objectNode.putObject("result");

            addTopEntries(resultNode, "topAlbums", entryListAlbum);
            addTopEntries(resultNode, "topSongs", entryListSong);
            ArrayNode topFansNode = resultNode.putArray("topFans");
            for (String fan : topFansList) {
                topFansNode.add(fan);
            }
            resultNode.put("listeners", entryListFans.size());
            return objectNode;
        }
        if (user != null) {
            List<Map.Entry<String, Integer>> entryList = new ArrayList<>(
                    user.getBestSongs().entrySet());
            entryList = Admin.getInstance().sortStats(entryList);

            List<Map.Entry<String, Integer>> entryListGenre = new ArrayList<>(
                    user.getBestGenre().entrySet());
            entryListGenre = Admin.getInstance().sortStats(entryListGenre);

            List<Map.Entry<String, Integer>> entryListArtist = new ArrayList<>(
                    user.getBestArtists().entrySet());
            entryListArtist = Admin.getInstance().sortStats(entryListArtist);

            List<Map.Entry<String, Integer>> entryListAlbum = new ArrayList<>(
                    user.getBestAlbums().entrySet());
            entryListAlbum = Admin.getInstance().sortStats(entryListAlbum);

            List<Map.Entry<String, Integer>> entryListEpisode = new ArrayList<>(
                    user.getBestEpisodes().entrySet());
            entryListEpisode = Admin.getInstance().sortStats(entryListEpisode);

            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", commandInput.getCommand());
            objectNode.put("user", commandInput.getUsername());
            objectNode.put("timestamp", commandInput.getTimestamp());
            objectNode.put("result", commandInput.getTimestamp());
            ObjectNode resultNode = objectNode.putObject("result");
            addTopEntries(resultNode, "topArtists", entryListArtist);
            addTopEntries(resultNode, "topGenres", entryListGenre);
            addTopEntries(resultNode, "topSongs", entryList);
            addTopEntries(resultNode, "topAlbums", entryListAlbum);
            addTopEntries(resultNode, "topEpisodes", entryListEpisode);
            return objectNode;
        }
        return null;
    }

    /**
     * Adds top entries to the specified ObjectNode based on the provided entry list.
     *
     * @param resultNode The ObjectNode to which top entries will be added.
     * @param nodeName   The name of the node in the ObjectNode where entries will be added.
     * @param entryList  The list of Map entries containing key-value pairs.
     */
    private static void addTopEntries(final ObjectNode resultNode,
                                      final String nodeName,
                                      final List<Map.Entry<String,
                                              Integer>> entryList) {
        ObjectNode node = resultNode.putObject(nodeName);
        int ok = 0;
        for (Map.Entry<String, Integer> entry : entryList) {
            if (ok == 5) {
                break;
            }
            node.put(entry.getKey(), entry.getValue());
            ok++;
        }
    }

    /**
     * Processes a subscription based on the provided command input.
     *
     * @param commandInput The input containing information for the subscription operation.
     * @return An ObjectNode containing the result of the subscription operation.
     */
    public static ObjectNode subscribe(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        if (user.getCurrentPage().display().equals("likedcontent")
                || user.getCurrentPage().display().equals("home")) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", commandInput.getCommand());
            objectNode.put("user", commandInput.getUsername());
            objectNode.put("timestamp", commandInput.getTimestamp());
            objectNode.put("message",
                    "To subscribe you need to be on the page of an artist or host.");
            return objectNode;
        }
        String name = user.getCurrentPage().getName();
        if (user.getSubscribeList().contains(name)) {
            user.getSubscribeList().remove(name);
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", commandInput.getCommand());
            objectNode.put("user", commandInput.getUsername());
            objectNode.put("timestamp", commandInput.getTimestamp());
            objectNode.put("message", user.getUsername() + " unsubscribed from "
                    + name + " successfully.");
            return objectNode;
        }
        user.getSubscribeList().add(name);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", user.getUsername() + " subscribed to "
                + name + " successfully.");
        return objectNode;
    }

    /**
     * Retrieves user notifications based on the provided command input.
     *
     * @param commandInput The input containing information for retrieving notifications.
     * @return An ObjectNode containing the user's notifications.
     */
    public static ObjectNode getNotifications(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        ArrayList<Notification> notifications = user.getNotificationList();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        ArrayNode notificationsNode = objectNode.putArray("notifications");
        for (Notification notification : notifications) {
            ObjectNode notificationNode = objectMapper.createObjectNode();
            notificationNode.put("name", notification.getName());
            notificationNode.put("description", notification.getDescription());
            notificationsNode.addObject().setAll(notificationNode);
        }
        user.clearNotifications();
        return objectNode;
    }

    /**
     * Processes a merchandise purchase based on the provided command input.
     *
     * @param commandInput The input containing information for the merchandise purchase.
     * @return An ObjectNode containing the result of the merchandise purchase operation.
     */
    public static ObjectNode buyMerch(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        if (user.getCurrentPage().display().equals("artist")) {
            String name = user.getCurrentPage().getName();
            Artist artist = Admin.getInstance().getArtist(name);
            Merchandise merch = artist.getAMerch(commandInput.getName());
            if (merch == null) {
                ObjectNode objectNode = objectMapper.createObjectNode();
                objectNode.put("command", commandInput.getCommand());
                objectNode.put("user", commandInput.getUsername());
                objectNode.put("timestamp", commandInput.getTimestamp());
                objectNode.put("message", "The merch " + commandInput.getName()
                        + " doesn't exist.");
                return objectNode;
            }
            int a = merch.getBuyers();
            a++;
            merch.setBuyers(a);
            user.addMerch(merch.getName());
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", commandInput.getCommand());
            objectNode.put("user", commandInput.getUsername());
            objectNode.put("timestamp", commandInput.getTimestamp());
            objectNode.put("message", user.getUsername() + " has added new merch successfully.");
            return objectNode;
        }
        return null;

    }

    /**
     * Retrieves information about merchandise based on the provided command input.
     *
     * @param commandInput The input containing information for retrieving merchandise details.
     * @return An ObjectNode containing information about the requested merchandise.
     */
    public static ObjectNode seeMerch(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(user.getMerch()));
        return objectNode;
    }
    /**
     * Updates recommendations based on the provided command input.
     *
     * @param commandInput The input containing information for updating recommendations.
     * @return An ObjectNode containing the updated recommendations.
     */
    public static ObjectNode updateRecommendations(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        if (commandInput.getRecommendationType().equals("random_song")) {
            PlayerStats stats = user.getPlayerStats();
            Song song = Admin.getInstance().getSong(stats.getName());
            int seed = song.getDuration() - stats.getRemainedTime();
            if (seed < 30) {
                ObjectNode objectNode = objectMapper.createObjectNode();
                objectNode.put("user", commandInput.getUsername());
                objectNode.put("command", commandInput.getCommand());
                objectNode.put("timestamp", commandInput.getTimestamp());
                objectNode.put("message", "No recommendations available.");
                return objectNode;
            }
            String genre = song.getGenre();
            ArrayList<Song> songArrayList = Admin.getInstance().searchAllGenre(genre);
            Random random = new Random(seed);
            int randomIndex = random.nextInt(songArrayList.size());
            Song song1 = songArrayList.get(randomIndex);
            user.addRecommendedSong(song1);
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", commandInput.getCommand());
            objectNode.put("user", commandInput.getUsername());
            objectNode.put("timestamp", commandInput.getTimestamp());
            objectNode.put("message", "The recommendations for user "
                    + user.getUsername() + " have been updated successfully.");
            return objectNode;
        }
        if (commandInput.getRecommendationType().equals("random_playlist")) {
            HashMap<String, Integer> genreMap = new HashMap<>();
            for (Song song : user.getLikedSongs()) {
                genreMap = Admin.getInstance().updateHashMap(song, genreMap);
            }
            for (Song song : user.getLikedSongs()) {
                genreMap = Admin.getInstance().updateHashMap(song, genreMap);
            }
            for (Playlist playlist : user.getFollowedPlaylists()) {
                for (Song song : playlist.getSongs()) {
                    genreMap = Admin.getInstance().updateHashMap(song, genreMap);
                }
            }
            List<Map.Entry<String, Integer>> topGen = new ArrayList<>(genreMap.entrySet());
            topGen = Admin.getInstance().sortStats(topGen);
            ArrayList<Song> top = new ArrayList<>();
            if (!topGen.isEmpty()) {
                top = Admin.getInstance().searchTopSongsGenre(topGen.get(0).getKey());
            }
            Playlist recomennded = new Playlist(user.getUsername()
                    + "'s recommendations", user.getUsername());
            for (Song sg : top) {
                recomennded.addSong(sg);
            }
            user.addRecommnedePlaylist(recomennded);
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", commandInput.getCommand());
            objectNode.put("user", commandInput.getUsername());
            objectNode.put("timestamp", commandInput.getTimestamp());
            objectNode.put("message", "The recommendations for user "
                    + user.getUsername() + " have been updated successfully.");
            return objectNode;
        }
        if (commandInput.getRecommendationType().equals("fans_playlist")) {
            String name = user.getPlayer().getSource().getAudioFile()
                    .getName();
            Song song = Admin.getInstance().getSong(name);
            String nameArtist = song.getArtist();
            Artist artist = Admin.getInstance().getArtist(nameArtist);
            Playlist playlist = new Playlist(nameArtist +
                    " Fan Club recommendations",
                    user.getUsername());
            List<Map.Entry<String, Integer>> entryListFans = new ArrayList<>(
                    artist.getFans().entrySet());
            entryListFans = Admin.getInstance().sortStats(entryListFans);
            ArrayList<String> topFansList = new ArrayList<>();
            for(String str : topFansList){
                User user1 = Admin.getInstance().getUser(str);
                List<String> likedSongs = Admin.getInstance().getTop5SongsUser(user1);
                Admin.getInstance().updatePlaylist(playlist,likedSongs);
            }
            int ok = 0;
            for (Map.Entry<String, Integer> entry : entryListFans) {
                if (ok == 5) {
                    break;
                }
                topFansList.add(entry.getKey());
            }
            user.addRecommnedePlaylist(playlist);
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", commandInput.getCommand());
            objectNode.put("user", commandInput.getUsername());
            objectNode.put("timestamp", commandInput.getTimestamp());
            objectNode.put("message", "The recommendations for user "
                    + user.getUsername() + " have been updated successfully.");
            return objectNode;
        }
        return null;
    }

    public static ObjectNode endProgram() {
        Admin.getInstance().calculateSales();
        ArrayList<Artist> topArtists = Admin.getInstance().getTopSales();

        if (!topArtists.isEmpty()) {
            Admin.getInstance().calculateRevenue(topArtists);
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", "endProgram");
            ObjectNode resultNode = objectNode.putObject("result");

            int index = 1;

            for (Artist artist : topArtists) {
                ObjectNode extranode = objectMapper.createObjectNode();
                extranode.put("merchRevenue", artist.getMerchRevenue());
                extranode.put("songRevenue", 0.0);
                extranode.put("ranking", index);
                extranode.put("mostProfitableSong", "N/A");

                resultNode.set(artist.getUsername(), extranode);
                index++;
            }
            return objectNode;
        }
        if (admin.existsEnd()) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", "endProgram");
            ObjectNode resultNode = objectNode.putObject("result");

            int index = 1;

            ArrayList<Artist> copyArtist = new ArrayList<>();
            copyArtist.addAll(Admin.getInstance().getArtists());
            Collections.sort(copyArtist, Comparator.comparing(Artist::getUsername));
            for (Artist artist : copyArtist) {
                if (artist.isPlayed()) {
                    ObjectNode extranode = objectMapper.createObjectNode();
                    extranode.put("merchRevenue", artist.getMerchRevenue());
                    extranode.put("songRevenue", 0.0);
                    extranode.put("ranking", index);
                    extranode.put("mostProfitableSong", "N/A");

                    resultNode.set(artist.getUsername(), extranode);
                    index++;
                }
            }
            return objectNode;
        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", "endProgram");
        objectNode.put("result", objectMapper.createObjectNode());
        return objectNode;
    }
    /**
     * Navigates to the previous page based on the provided command input.
     *
     * @param commandInput The input containing information for navigating to the previous page.
     * @return An ObjectNode containing the result of navigating to the previous page.
     */
    public static ObjectNode previousPage(final CommandInput commandInput) {
        User user = Admin.getInstance().getUser(commandInput.getUsername());
        System.out.println(user.getPageHistory().toString());
        int nr = user.getPageHistory().size() - 1;
        String newPage = user.getPageHistory().get(nr);
        System.out.println(newPage);
        if (newPage.equals("likedcontent")) {
            user.setCurrentPage(user.getLikedContentPage());
        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", "previousPage");
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", "The user " + commandInput.getUsername()
                + " has navigated successfully to the previous page.");
        return objectNode;
    }

}

