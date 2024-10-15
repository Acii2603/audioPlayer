package app;

import app.audio.Collections.Album;
import app.audio.Collections.AlbumOutput;
import app.audio.Collections.Announcement;
import app.audio.Collections.Event;
import app.audio.Collections.Merch;
import app.audio.Collections.Playlist;
import app.audio.Collections.PlaylistOutput;
import app.audio.Collections.Podcast;
import app.audio.Collections.PodcastOutput;
import app.audio.Files.ArtistList;
import app.audio.Files.Episode;
import app.audio.Files.HostFile;
import app.audio.Files.Song;
import app.audio.LibraryEntry;
import app.pages.HomePage;
import app.pages.LikedContentPage;
import app.player.PlayerStats;
import app.searchBar.Filters;
import app.user.Artist;
import app.user.Host;
import app.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.CommandInput;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Command runner.
 */
public final class CommandRunner {
    /**
     * The Object mapper.
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private CommandRunner() {
    }

    /**
     * Search object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode search(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = commandInput.getUsername() + " is offline.";
        ArrayList<String> results = new ArrayList<>();
        if (user.isStatus()) {
            Filters filters = new Filters(commandInput.getFilters());
            String type = commandInput.getType();

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
        User user = Admin.getUser(commandInput.getUsername());

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
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.load();

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
        User user = Admin.getUser(commandInput.getUsername());
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
        User user = Admin.getUser(commandInput.getUsername());
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
        User user = Admin.getUser(commandInput.getUsername());
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
        User user = Admin.getUser(commandInput.getUsername());
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
        User user = Admin.getUser(commandInput.getUsername());
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
        User user = Admin.getUser(commandInput.getUsername());
        String message;
        if (user.isStatus()) {
            message = user.like();
        } else {
            message = user.getUsername() + " is offline.";
        }
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
        User user = Admin.getUser(commandInput.getUsername());
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
        User user = Admin.getUser(commandInput.getUsername());
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
        User user = Admin.getUser(commandInput.getUsername());
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
        User user = Admin.getUser(commandInput.getUsername());
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
        User user = Admin.getUser(commandInput.getUsername());
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
        User user = Admin.getUser(commandInput.getUsername());
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
        User user = Admin.getUser(commandInput.getUsername());
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
        User user = Admin.getUser(commandInput.getUsername());
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
        User user = Admin.getUser(commandInput.getUsername());
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
        User user = Admin.getUser(commandInput.getUsername());
        String preferredGenre = user.getPreferredGenre();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(preferredGenre));

        return objectNode;
    }

    /**
     * Gets top 5 songs.
     *
     * @param commandInput the command input
     * @return the top 5 songs
     */
    public static ObjectNode getTop5Songs(final CommandInput commandInput) {
        List<String> songs = Admin.getTop5Songs();

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
        List<String> playlists = Admin.getTop5Playlists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    public static ObjectNode getTop5Artists(final CommandInput commandInput) {
        List<String> playlists = Admin.getTop5Artists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    public static ObjectNode getTop5Albums(final CommandInput commandInput) {
        List<String> playlists = Admin.getTop5Albums();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    public static ObjectNode addUser(final CommandInput commandInput) {
        String result = Admin.addUser(commandInput);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (result != null) {
            objectNode.put("message", "The username " + result + " has been added successfully.");
        } else {
            objectNode.put(
                    "message", "The username " + commandInput.getUsername() + " is already taken.");
        }

        return objectNode;
    }

    public static ObjectNode switchConnectionStatus(final CommandInput commandInput) {
        String user = commandInput.getUsername();
        int status = Admin.switchConnectionStatus(user);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        switch (status) {
            case 0 -> objectNode.put("message", user + " has changed status successfully.");
            case 1 -> objectNode.put("message", user + " is not a normal user.");
            case 2 -> objectNode.put("message", "The username " + user + " doesn't exist.");
        }
        return objectNode;
    }

    public static ObjectNode getOnlineUsers(final CommandInput commandInput) {
        ArrayList<String> onlineUsers = Admin.getOnlineUsers();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(onlineUsers));
        return objectNode;
    }

    public static ObjectNode addAlbum(final CommandInput commandInput) {
        int status = Admin.addAlbum(commandInput);
        String message;
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (status == 0) {
            message = commandInput.getUsername() + " has added new album successfully.";
        } else if (status == 1) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (status == 2) {
            message = commandInput.getUsername() + " is not an artist.";
        } else if (status == 3) {
            message = commandInput.getUsername() + " has another album with the same name.";
        } else {
            message = commandInput.getUsername() + " has the same song at least twice in this album.";
        }
        objectNode.put("message", message);
        return objectNode;
    }

    public static ObjectNode showAlbums(final CommandInput commandInput) {
        ArrayList<AlbumOutput> result = new ArrayList<>();
        Artist artist = Admin.getArtist(commandInput.getUsername());

        for (Album album : artist.getAlbums()) {
            result.add(new AlbumOutput(album));
        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(result));
        return objectNode;
    }

    public static ObjectNode showPodcasts(final CommandInput commandInput) {
        ArrayList<PodcastOutput> result = new ArrayList<>();
        Host host = Admin.getHost(commandInput.getUsername());

        for (Podcast podcast : host.getPodcasts()) {
            result.add(new PodcastOutput(podcast));
        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(result));
        return objectNode;
    }

    public static ObjectNode addEvent(final CommandInput commandInput) {
        int status = Admin.addEvent(commandInput);
        String message;
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (status == 0) {
            message = commandInput.getUsername() + " has added new event successfully.";
        } else if (status == 1) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (status == 2) {
            message = commandInput.getUsername() + " is not an artist.";
        } else if (status == 3) {
            message = commandInput.getUsername() + " has another event with the same name.";
        } else {
            message = "Event for " + commandInput.getUsername() + " does not have a valid date.";
        }
        objectNode.put("message", message);
        return objectNode;
    }

    public static ObjectNode addAnnouncement(final CommandInput commandInput) {
        int status = Admin.addAnnouncement(commandInput);
        String message;
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (status == 0) {
            message = commandInput.getUsername() + " has successfully added new announcement.";
        } else if (status == 1) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (status == 2) {
            message = commandInput.getUsername() + " is not a host.";
        } else if (status == 3) {
            message = commandInput.getUsername() + " has already added an announcement with this name.";
        } else {
            message = "Event for " + commandInput.getUsername() + " does not have a valid date.";
        }
        objectNode.put("message", message);
        return objectNode;
    }

    public static ObjectNode addPodcast(final CommandInput commandInput) {
        int status = Admin.addPodcast(commandInput);
        String message;
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (status == 0) {
            message = commandInput.getUsername() + " has added new podcast successfully.";
        } else if (status == 1) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (status == 2) {
            message = commandInput.getUsername() + " is not a host.";
        } else if (status == 3) {
            message = commandInput.getUsername() + " has another podcast with the same name.";
        } else {
            message = commandInput.getUsername() + " has the same episode in this podcast.";
        }
        objectNode.put("message", message);
        return objectNode;
    }

    public static ObjectNode addMerch(final CommandInput commandInput) {
        int status = Admin.addMerch(commandInput);
        String message;
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (status == 0) {
            message = commandInput.getUsername() + " has added new merchandise successfully.";
        } else if (status == 1) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (status == 2) {
            message = commandInput.getUsername() + " is not an artist.";
        } else if (status == 3) {
            message = commandInput.getUsername() + " has merchandise with the same name.";
        } else {
            message = "Price for merchandise can not be negative.";
        }
        objectNode.put("message", message);
        return objectNode;
    }

    public static ObjectNode deleteUser(final CommandInput commandInput) {
        int status = Admin.deleteUser(commandInput);
        String message = null;
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (status == 0) {
            message = commandInput.getUsername() + " was successfully deleted.";
        } else if (status == 1) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (status == 2) {
            message = commandInput.getUsername() + " can't be deleted.";
        }
        objectNode.put("message", message);
        return objectNode;

    }

    public static ObjectNode getAllUsers(final CommandInput commandInput) {

        ArrayList<String> getUserNames = new ArrayList<>();
        ArrayList<String> getArtistNames = new ArrayList<>();
        ArrayList<String> getHostNames = new ArrayList<>();
        for (User user : Admin.getUsers()) {
            switch (Admin.getType(user)) {
                case 1 -> {
                    getArtistNames.add(user.getUsername());
                }
                case 2 -> {
                    getUserNames.add(user.getUsername());
                }
                case 3 -> {
                    getHostNames.add(user.getUsername());
                }
            }
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        ArrayList<String> result = new ArrayList<>();
        result.addAll(getUserNames);
        result.addAll(getArtistNames);
        result.addAll(getHostNames);
        objectNode.put("result", objectMapper.valueToTree(result));
        return objectNode;

    }

    public static ObjectNode removeAlbum(final CommandInput commandInput) {
        int status = Admin.removeAlbum(commandInput);
        String message;
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (status == 0) {
            message = commandInput.getUsername() + " deleted the album successfully.";
        } else if (status == 1) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (status == 2) {
            message = commandInput.getUsername() + " is not an artist.";
        } else if (status == 3) {
            message = commandInput.getUsername() + " doesn't have an album with the given name.";
        } else {
            message = commandInput.getUsername() + " can't delete this album.";
        }
        objectNode.put("message", message);
        return objectNode;
    }

    public static ObjectNode removePodcast(final CommandInput commandInput) {
        int status = Admin.removePodcast(commandInput);
        String message;
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (status == 0) {
            message = commandInput.getUsername() + " deleted the podcast successfully.";
        } else if (status == 1) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (status == 2) {
            message = commandInput.getUsername() + " is not a host.";
        } else if (status == 3) {
            message = commandInput.getUsername() + " doesn't have a podcast with the given name.";
        } else {
            message = commandInput.getUsername() + " can't delete this podcast.";
        }
        objectNode.put("message", message);
        return objectNode;
    }


    public static ObjectNode removeEvent(final CommandInput commandInput) {
        int status = Admin.removeEvent(commandInput);
        String message;
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (status == 0) {
            message = commandInput.getUsername() + " deleted the event successfully.";
        } else if (status == 1) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (status == 2) {
            message = commandInput.getUsername() + " is not an artist.";
        } else {
            message = commandInput.getUsername() + " doesn't have an event with the given name.";
        }
        objectNode.put("message", message);
        return objectNode;
    }

    public static ObjectNode removeAnnouncement(final CommandInput commandInput) {
        int status = Admin.removeAnnouncement(commandInput);
        String message;
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (status == 0) {
            message = commandInput.getUsername() + " has successfully deleted the announcement.";
        } else if (status == 1) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (status == 2) {
            message = commandInput.getUsername() + " is not a host.";
        } else {
            message = commandInput.getUsername() + " has no announcement with the given name.";
        }
        objectNode.put("message", message);
        return objectNode;
    }

    public static ObjectNode changePage(final CommandInput commandInput) {
        int status = Admin.changePage(commandInput);
        String message;
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (status == 0) {
            message = commandInput.getUsername() + " accessed " + commandInput.getNextPage() + " successfully.";
        } else {
            message = commandInput.getUsername() + " is trying to access a non-existent page.";
        }
        objectNode.put("message", message);
        return objectNode;
    }

    public static ObjectNode printCurrentPage(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        LibraryEntry currentPage = user.getCurrentPage();
        String message;
        if (!user.isStatus()) {
            message = user.getUsername() + " is offline.";
        } else {
            if (currentPage instanceof HomePage) {
                List<String> top5LikedSongs = ((HomePage) currentPage).getTop5Songs();
                List<String> top5FollowedPlaylists = ((HomePage) currentPage).getTop5Playlists();
                message = "Liked songs:\n\t" + top5LikedSongs + "\n\nFollowed playlists:\n\t" + top5FollowedPlaylists;
            } else if (currentPage instanceof LikedContentPage) {
                ArrayList<String> likedSongs = new ArrayList<>();
                for (Song song : ((LikedContentPage) currentPage).getLikedSongs()) {
                    String temp = song.getName() + " - " + song.getArtist();
                    likedSongs.add(temp);
                }
                ArrayList<String> followedPlaylists = new ArrayList<>();
                for (Playlist playlist : ((LikedContentPage) currentPage).getLikedPlaylists()) {
                    String temp = playlist.getName() + " - " + playlist.getOwner();
                    followedPlaylists.add(temp);
                }
                message = "Liked songs:\n\t" + likedSongs + "\n\nFollowed playlists:\n\t" + followedPlaylists;
            } else if (currentPage instanceof ArtistList) {
                ArrayList<String> albumNames = new ArrayList<>();
                for (Album album : ((ArtistList) currentPage).getAlbums()) {
                    albumNames.add(album.getName());
                }

                ArrayList<String> merches = new ArrayList<>();
                for (Merch merch : ((ArtistList) currentPage).getMerches()) {
                    String temp = merch.getName() + " - " + merch.getPrice() + ":\n\t" + merch.getDescription();
                    merches.add(temp);
                }

                ArrayList<String> events = new ArrayList<>();
                for (Event event : ((ArtistList) currentPage).getEvents()) {
                    String temp = event.getName() + " - " + event.getDate() + ":\n\t" + event.getDescription();
                    events.add(temp);
                }
                message = "Albums:\n\t" + albumNames + "\n\nMerch:\n\t" + merches + "\n\nEvents:\n\t" + events;
            } else {
                ArrayList<String> podcasts = new ArrayList<>();
                for (Podcast podcast : ((HostFile) currentPage).getPodcasts()) {
                    String temp = podcast.getName() + ":\n\t[";
                    for (Episode episode : podcast.getEpisodes()) {
                        temp += episode.getName() + " - " + episode.getDescription() + ", ";
                    }
                    temp = temp.substring(0, temp.length() - 2);
                    temp += "]\n";
                    podcasts.add(temp);
                }
                ArrayList<String> announcements = new ArrayList<>();
                for (Announcement announcement : ((HostFile) currentPage).getAnnouncements()) {
                    String temp = announcement.getName() + ":\n\t" + announcement.getDescription() + "\n";
                    announcements.add(temp);
                }

                message = "Podcasts:\n\t" + podcasts + "\n\nAnnouncements:\n\t" + announcements;
            }
        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }


}
