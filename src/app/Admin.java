package app;

import app.audio.Collections.Album;
import app.audio.Collections.Announcement;
import app.audio.Collections.Event;
import app.audio.Collections.Merch;
import app.audio.Collections.Playlist;
import app.audio.Collections.Podcast;
import app.audio.Files.ArtistList;
import app.audio.Files.Episode;
import app.audio.Files.HostFile;
import app.audio.Files.Song;
import app.pages.HomePage;
import app.pages.LikedContentPage;
import app.user.Artist;
import app.user.Host;
import app.user.User;
import fileio.input.CommandInput;
import fileio.input.EpisodeInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import fileio.input.UserInput;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * The type Admin.
 */
@Getter
@Setter
public final class Admin {
    private static final Admin instance = new Admin();
    private static final int LIMIT = 5;
    private static final List<Album> albums = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();
    private static List<Song> songs = new ArrayList<>();
    private static List<Podcast> podcasts = new ArrayList<>();
    private static int timestamp = 0;

    private Admin() {
    }

    public static boolean isUser(final User user) {
        return !(user instanceof Artist || user instanceof Host);
    }

    public static List<ArtistList> getArtists() {
        ArrayList<ArtistList> artists = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Artist) {
                artists.add(new ArtistList(user.getUsername(),
                        ((Artist) user).getAlbums(),
                        ((Artist) user).getEvents(),
                        ((Artist) user).getMerches()));
            }
        }
        return artists;
    }

    public static List<HostFile> getHosts() {
        List<HostFile> hosts = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Host) {
                hosts.add(new HostFile(user.getUsername(),
                        ((Host) user).getPodcasts(),
                        ((Host) user).getAnnouncements()));
            }
        }
        return hosts;
    }

    public static List<Album> getAlbums() {
        List<Album> adminAlbums = new ArrayList<Album>();
        for (User user : getUsers()) {
            if (user instanceof Artist) {
                adminAlbums.addAll(((Artist) user).getAlbums());
            }
        }
        return adminAlbums;
    }

    /**
     * Gets songs.
     *
     * @return the songs
     */
    public static List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    /**
     * Sets songs.
     *
     * @param songInputList the song input list
     */
    public static void setSongs(final List<SongInput> songInputList) {
        songs = new ArrayList<>();
        for (SongInput songInput : songInputList) {
            songs.add(new Song(songInput.getName(), songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist()));
        }
    }

    /**
     * Gets podcasts.
     *
     * @return the podcasts
     */
    public static List<Podcast> getPodcasts() {
        return new ArrayList<>(podcasts);
    }

    /**
     * Sets podcasts.
     *
     * @param podcastInputList the podcast input list
     */
    public static void setPodcasts(final List<PodcastInput> podcastInputList) {
        podcasts = new ArrayList<>();
        for (PodcastInput podcastInput : podcastInputList) {
            List<Episode> episodes = new ArrayList<>();
            for (EpisodeInput episodeInput : podcastInput.getEpisodes()) {
                episodes.add(new Episode(episodeInput.getName(),
                        episodeInput.getDuration(),
                        episodeInput.getDescription()));
            }
            podcasts.add(new Podcast(podcastInput.getName(), podcastInput.getOwner(), episodes));
        }
    }

    /**
     * Gets playlists.
     *
     * @return the playlists
     */
    public static List<Playlist> getPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        for (User user : users) {
            playlists.addAll(user.getPlaylists());
        }
        return playlists;
    }

    /**
     * Gets user.
     *
     * @param username the username
     * @return the user
     */
    public static User getUser(final String username) {
        for (User user : users) {
            if (user.getUsername().equals(username) && isUser(user)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Update timestamp.
     *
     * @param newTimestamp the new timestamp
     */
    public static void updateTimestamp(final int newTimestamp) {
        int elapsed = newTimestamp - timestamp;
        timestamp = newTimestamp;
        if (elapsed == 0) {
            return;
        }

        for (User user : users) {
            user.simulateTime(elapsed);
        }
    }

    /**
     * Gets top 5 songs.
     *
     * @return the top 5 songs
     */
    public static List<String> getTop5Songs() {
        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        List<String> topSongs = new ArrayList<>();
        int count = 0;
        for (Song song : sortedSongs) {
            if (count >= LIMIT) {
                break;
            }
            topSongs.add(song.getName());
            count++;
        }
        return topSongs;
    }

    public static List<String> getTop5Artists() {
        ArrayList<Artist> sortedArtists = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Artist) {
                ((Artist) user).addLikes();
                sortedArtists.add((Artist) user);
            }
        }

        sortedArtists.sort(Comparator
                .comparing(Artist::getLikes, Comparator.reverseOrder())
                .thenComparing(Artist::getUsername));
        List<String> topArtists = new ArrayList<>();
        int count = 0;
        for (Artist artist : sortedArtists) {
            if (count >= LIMIT) {
                break;
            }
            topArtists.add(artist.getUsername());
            count++;
        }
        return topArtists;
    }

    public static List<String> getTop5Albums() {
        List<Album> sortedAlbums = new ArrayList<>();

        for (Album album : getAlbums()) {
            album.addLikes();
            sortedAlbums.add(album);
        }

        sortedAlbums.sort(Comparator
                .comparing(Album::getLikes, Comparator.reverseOrder())
                .thenComparing(Album::getName));

        List<String> topAlbums = new ArrayList<>();
        int count = 0;
        for (Album album : sortedAlbums) {
            if (count >= LIMIT) {
                break;
            }
            topAlbums.add(album.getName());
            count++;
        }
        return topAlbums;
    }

    /**
     * Gets top 5 playlists.
     *
     * @return the top 5 playlists
     */
    public static List<String> getTop5Playlists() {
        List<Playlist> sortedPlaylists = new ArrayList<>(getPlaylists());
        sortedPlaylists.sort(Comparator.comparingInt(Playlist::getFollowers)
                .reversed()
                .thenComparing(Playlist::getTimestamp, Comparator.naturalOrder()));
        List<String> topPlaylists = new ArrayList<>();
        int count = 0;
        for (Playlist playlist : sortedPlaylists) {
            if (count >= LIMIT) {
                break;
            }
            topPlaylists.add(playlist.getName());
            count++;
        }
        return topPlaylists;
    }

    public static int changePage(final CommandInput commandInput) {
        User user = getUser(commandInput.getUsername());

        if (user != null) {
            if (commandInput.getNextPage().equals("Artist")
                    || commandInput.getNextPage().equals("Host")) {
                return 1;
            }
            if (commandInput.getNextPage().equals("Home")) {
                user.setCurrentPage(new HomePage(user.getUsername(),
                        user.getLikedSongs(),
                        user.getFollowedPlaylists()));
            }
            if (commandInput.getNextPage().equals("LikedContent")) {
                user.setCurrentPage(new LikedContentPage(user.getUsername(),
                        user.getLikedSongs(), user.getFollowedPlaylists()));
            }
            return 0;

        } else {
            Artist artist = getArtist(commandInput.getUsername());
            if (artist != null) {
                if (commandInput.getNextPage().equals("Host")) {
                    return 1;
                }
                artist.setCurrentPage(new ArtistList(artist.getUsername(),
                        artist.getAlbums(),
                        artist.getEvents(),
                        artist.getMerches()));
                return 0;
            } else {
                Host host = getHost(commandInput.getUsername());
                if (commandInput.getNextPage().equals("Artist")) {
                    return 1;
                }
                host.setCurrentPage(new HostFile(host.getUsername(),
                        host.getPodcasts(), host.getAnnouncements()));
                return 0;
            }
        }

    }

    public static String addUser(final CommandInput commandInput) {
        for (User user : users) {
            if (commandInput.getUsername().equals(user.getUsername())) {
                return null;
            }
        }
        switch (commandInput.getType()) {
            case "user" -> users.add(new User(commandInput.getUsername(),
                    commandInput.getAge(), commandInput.getCity()));
            case "artist" -> users.add(new Artist(commandInput.getUsername(),
                    commandInput.getAge(), commandInput.getCity()));

            case "host" -> users.add(new Host(commandInput.getUsername(),
                    commandInput.getAge(), commandInput.getCity()));
            default -> {
                return null;
            }
        }

        return commandInput.getUsername();
    }

    public static int getType(final User user) {
        int errNo = 3;
        if (user instanceof Artist) {
            errNo = 1;
        }
        if (isUser(user)) {
            errNo = 2;
        }
        return errNo;
    }

    public static int deleteArtist(final User targetUser) {
        Artist artist = (Artist) targetUser;
        for (Album album : artist.getAlbums()) {
            ArrayList<String> songNames = new ArrayList<>();
            for (Song song : album.getSongs()) {
                songNames.add(song.getName());
            }
            for (User user : users) {
                if (songNames.contains(user.getPlayerStats().getName())) {
                    return 2;
                }
            }
        }

        for (User user : users) {
            if (user.getCurrentPage().getName().equals(targetUser.getUsername())) {
                if (!user.getUsername().equals(targetUser.getUsername())) {
                    return 2;
                }
            }
        }

        for (Album album : artist.getAlbums()) {
            for (Song song : album.getSongs()) {
                songs.remove(song);
            }
        }
        for (User user : users) {
            for (Album album : artist.getAlbums()) {
                for (Song song : album.getSongs()) {
                    user.getLikedSongs().remove(song);
                }
            }
        }
        for (Song song : songs) {
            if (targetUser.getLikedSongs().contains(song)) {
                song.dislike();
            }
        }

        for (Playlist playlist : getPlaylists()) {
            if (targetUser.getFollowedPlaylists().contains(playlist)) {
                playlist.decreaseFollowers();
            }
        }
        users.remove(targetUser);
        return 0;
    }

    public static int deleteHost(final User targetUser) {
        Host host = (Host) targetUser;
        for (Podcast podcast : host.getPodcasts()) {
            ArrayList<String> episodesNames = new ArrayList<>();
            for (Episode episode : podcast.getEpisodes()) {
                episodesNames.add(episode.getName());
            }
            for (User user : users) {
                if (episodesNames.contains(user.getPlayerStats().getName())) {
                    return 2;
                }
            }
        }

        for (User user : users) {
            if (user.getCurrentPage().getName().equals(targetUser.getUsername())) {
                if (!user.getUsername().equals(targetUser.getUsername())) {
                    return 2;
                }
            }
        }
        for (Song song : songs) {
            if (targetUser.getLikedSongs().contains(song)) {
                song.dislike();
            }
        }
        for (Playlist playlist : getPlaylists()) {
            if (targetUser.getFollowedPlaylists().contains(playlist)) {
                playlist.decreaseFollowers();
            }
        }


        users.remove(targetUser);
        return 0;
    }

    public static int deleteUser(final CommandInput commandInput) {
        int status;
        for (User user : users) {
            if (commandInput.getUsername().equals(user.getUsername())) {
                switch (getType(user)) {
                    case 1 -> {
                        status = deleteArtist(user);
                        return status;
                    }
                    case 2 -> {
                        for (Playlist playlist : getPlaylists()) {
                            if (playlist.getOwner().equals(user.getUsername())) {
                                for (User userTmp : users) {
                                    if (!userTmp.getUsername().equals(user.getUsername())) {
                                        for (Song song : playlist.getSongs()) {
                                            if (userTmp.getPlayerStats().getName()
                                                    .equals(song.getName())) {
                                                return 2;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (user.getPlayerStats().getName().isEmpty()) {
                            for (Song song : songs) {
                                if (user.getLikedSongs().contains(song)) {
                                    song.dislike();
                                }
                            }

                            for (Playlist playlist : getPlaylists()) {
                                if (user.getFollowedPlaylists().contains(playlist)) {
                                    playlist.decreaseFollowers();
                                }
                            }
                            for (User userTmp : users) {
                                ArrayList<Playlist> playlistsToRemove = new ArrayList<>();
                                for (Playlist playlistTmp : userTmp.getFollowedPlaylists()) {
                                    for (Playlist playlist : user.getPlaylists()) {
                                        if (playlist.equals(playlistTmp)) {
                                            playlistsToRemove.add(playlist);
                                        }
                                    }
                                }
                                userTmp.getFollowedPlaylists().removeAll(playlistsToRemove);
                            }
                            users.remove(user);
                            return 0;
                        } else {
                            return 2;
                        }
                    }
                    case 3 -> {
                        status = deleteHost(user);
                        return status;
                    }
                    default -> {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

    public static int switchConnectionStatus(final String username) {
        for (User user : users) {
            if (username.equals(user.getUsername())) {
                if (!isUser(user)) {
                    return 1;
                } else {
                    user.changeStatus();
                    return 0;
                }

            }
        }
        return 2;
    }

    public static void addAlbumSongs(final ArrayList<Song> songs) {
        for (Song currSong : songs) {
            if (!(Admin.songs.contains(currSong))) {
                Admin.songs.add(currSong);
            }
        }
    }

    public static ArrayList<String> getOnlineUsers() {
        ArrayList<String> onlineUsers = new ArrayList<>();
        for (User user : users) {
            if (user.isStatus() && isUser(user)) {
                onlineUsers.add(user.getUsername());
            }
        }
        return onlineUsers;
    }

    public static Artist getArtist(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username) && (user instanceof Artist)) {
                return (Artist) user;
            }
        }
        return null;
    }

    public static Host getHost(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username) && (user instanceof Host)) {
                return (Host) user;
            }
        }
        return null;
    }

    public static int addAlbum(CommandInput commandInput) {

        Artist artist = getArtist(commandInput.getUsername());
        if (artist != null) {

            for (Album albums : artist.getAlbums()) {
                if (albums.getName().equals(commandInput.getName())) {
                    return 3;
                }
            }
            ArrayList<SongInput> list = commandInput.getSongs();
            int size = list.size();
            for (int i = 0; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    if (list.get(i).getName().equals(list.get(j).getName())) {
                        return 4;
                    }
                }
            }

            ArrayList<Song> songs = new ArrayList<>();
            for (SongInput song : commandInput.getSongs()) {
                Song newSong = new Song(song.getName(),
                        song.getDuration(),
                        song.getAlbum(),
                        song.getTags(),
                        song.getLyrics(),
                        song.getGenre(),
                        song.getReleaseYear(),
                        song.getArtist());
                songs.add(newSong);
            }

            Album newAlbum = new Album(commandInput.getName(), commandInput.getUsername(), commandInput.getTimestamp(),
                    commandInput.getDescription(), songs);
            artist.addAlbum(newAlbum);
            addAlbumSongs(songs);
            return 0;
        } else {
            User user = getUser(commandInput.getUsername());
            Host host = getHost(commandInput.getUsername());
            if (user == null && host == null) {
                return 1;
            } else {
                return 2;
            }

        }
    }

    public static int addPodcast(CommandInput commandInput) {

        Host host = getHost(commandInput.getUsername());
        if (host != null) {

            for (Podcast podcast : host.getPodcasts()) {
                if (podcast.getName().equals(commandInput.getName())) {
                    return 3;
                }
            }
            ArrayList<EpisodeInput> list = commandInput.getEpisodes();
            int size = list.size();
            for (int i = 0; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    if (list.get(i).getName().equals(list.get(j).getName())) {
                        return 4;
                    }
                }
            }

            ArrayList<Episode> episodes = new ArrayList<>();
            for (EpisodeInput episode : commandInput.getEpisodes()) {
                Episode newEpisode = new Episode(episode.getName(), episode.getDuration(), episode.getDescription());
                episodes.add(newEpisode);
            }

            Podcast newPodcast = new Podcast(commandInput.getName(), commandInput.getUsername(), episodes);
            host.addPodcast(newPodcast);
            podcasts.add(newPodcast);
            return 0;
        } else {
            User user = getUser(commandInput.getUsername());
            Artist artist = getArtist(commandInput.getUsername());
            if (user == null && artist == null) {
                return 1;
            } else {
                return 2;
            }

        }
    }

    public static int removeAlbum(CommandInput commandInput) {

        Artist artist = getArtist(commandInput.getUsername());
        if (artist != null) {
            Album currentAlbum = null;
            for (Album album : artist.getAlbums()) {
                if (album.getName().equals(commandInput.getName())) {
                    currentAlbum = album;
                }
            }
            if (currentAlbum == null) {
                return 3;
            }

            ArrayList<String> songNames = new ArrayList<>();

            for (Song song : currentAlbum.getSongs()) {
                songNames.add(song.getName());
            }
            for (User user : users) {
                if (songNames.contains(user.getPlayerStats().getName())) {
                    return 4;
                }
                for (Playlist playlist : user.getPlaylists()) {
                    for (Song song : playlist.getSongs()) {
                        if (songNames.contains(song.getName())) {
                            return 4;
                        }
                    }
                }

            }


            for (Song song : currentAlbum.getSongs()) {
                songs.remove(song);
            }
            ArrayList<Album> newAlbums = artist.getAlbums();
            newAlbums.remove(currentAlbum);
            artist.setAlbums(newAlbums);
            return 0;
        } else {
            User user = getUser(commandInput.getUsername());
            Host host = getHost(commandInput.getUsername());
            if (user == null && host == null) {
                return 1;
            } else {
                return 2;
            }

        }

    }

    public static int removePodcast(CommandInput commandInput) {

        Host host = getHost(commandInput.getUsername());
        if (host != null) {
            Podcast currentPodcast = null;
            for (Podcast podcast : host.getPodcasts()) {
                if (podcast.getName().equals(commandInput.getName())) {
                    currentPodcast = podcast;
                }
            }
            if (currentPodcast == null) {
                return 3;
            }

            ArrayList<String> episodeNames = new ArrayList<>();

            for (Episode episode : currentPodcast.getEpisodes()) {
                episodeNames.add(episode.getName());
            }
            for (User user : users) {
                if (episodeNames.contains(user.getPlayerStats().getName())) {
                    return 4;
                }
            }

            ArrayList<Podcast> newPodcasts = host.getPodcasts();
            newPodcasts.remove(currentPodcast);
            host.setPodcasts(newPodcasts);
            return 0;
        } else {
            User user = getUser(commandInput.getUsername());
            Artist artist = getArtist(commandInput.getUsername());
            if (user == null && artist == null) {
                return 1;
            } else {
                return 2;
            }

        }

    }

    public static boolean isValidDate(final String inputDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(inputDate, formatter);

            int day = date.getDayOfMonth();
            int month = date.getMonthValue();
            if (month <= 12 && day <= 31) {

                return month != 2 || day <= 28;
            } else {
                return false;
            }


        } catch (Exception e) {
            return false;
        }
    }

    public static int addEvent(final CommandInput commandInput) {

        Artist artist = getArtist(commandInput.getUsername());
        if (artist != null) {

            for (Event event : artist.getEvents()) {
                if (event.getName().equals(commandInput.getName())) {
                    return 3;
                }
            }

            if (!isValidDate(commandInput.getDate())) {
                return 4;
            }

            Event newEvent = new Event(commandInput.getName(),
                    commandInput.getDate(),
                    commandInput.getTimestamp(),
                    commandInput.getDescription());

            artist.addEvent(newEvent);
            return 0;
        } else {
            User user = getUser(commandInput.getUsername());
            Host host = getHost(commandInput.getUsername());
            if (user == null && host == null) {
                return 1;
            } else {
                return 2;
            }

        }

    }

    public static int addAnnouncement(final CommandInput commandInput) {

        Host host = getHost(commandInput.getUsername());
        if (host != null) {

            for (Announcement announcement : host.getAnnouncements()) {
                if (announcement.getName().equals(commandInput.getName())) {
                    return 3;
                }
            }


            Announcement newAnnouncement = new Announcement(commandInput.getName(),
                    commandInput.getDate(),
                    commandInput.getTimestamp(),
                    commandInput.getDescription());

            host.addAnnouncement(newAnnouncement);
            return 0;
        } else {
            User user = getUser(commandInput.getUsername());
            Artist artist = getArtist(commandInput.getUsername());
            if (user == null && artist == null) {
                return 1;
            } else {
                return 2;
            }

        }

    }

    public static int removeEvent(final CommandInput commandInput) {

        Artist artist = getArtist(commandInput.getUsername());
        Event currentEvent = null;
        if (artist != null) {
            for (Event event : artist.getEvents()) {
                if (event.getName().equals(commandInput.getName())) {
                    currentEvent = event;
                }
            }

            if (currentEvent == null) {
                return 3;
            }

            ArrayList<Event> newEvents = artist.getEvents();
            newEvents.remove(currentEvent);
            artist.setEvents(newEvents);
            return 0;
        } else {
            User user = getUser(commandInput.getUsername());
            Host host = getHost(commandInput.getUsername());
            if (user == null && host == null) {
                return 1;
            } else {
                return 2;
            }

        }

    }

    public static int removeAnnouncement(final CommandInput commandInput) {

        Host host = getHost(commandInput.getUsername());
        Announcement currentAnnouncement = null;
        if (host != null) {
            for (Announcement announcement : host.getAnnouncements()) {
                if (announcement.getName().equals(commandInput.getName())) {
                    currentAnnouncement = announcement;
                }
            }

            if (currentAnnouncement == null) {
                return 3;
            }

            ArrayList<Announcement> newAnnouncements = host.getAnnouncements();
            newAnnouncements.remove(currentAnnouncement);
            host.setAnnouncements(newAnnouncements);
            return 0;
        } else {
            User user = getUser(commandInput.getUsername());
            Artist artist = getArtist(commandInput.getUsername());
            if (user == null && artist == null) {
                return 1;
            } else {
                return 2;
            }

        }

    }

    public static int addMerch(final CommandInput commandInput) {

        Artist artist = getArtist(commandInput.getUsername());
        if (artist != null) {

            for (Merch merch : artist.getMerches()) {
                if (merch.getName().equals(commandInput.getName())) {
                    return 3;
                }
            }

            if (commandInput.getPrice() < 0) {
                return 4;
            }

            Merch newMerch = new Merch(commandInput.getName(),
                    commandInput.getPrice(),
                    commandInput.getTimestamp(),
                    commandInput.getDescription());
            artist.addMerch(newMerch);
            return 0;
        } else {
            User user = getUser(commandInput.getUsername());
            Host host = getHost(commandInput.getUsername());
            if (user == null && host == null) {
                return 1;
            } else {
                return 2;
            }

        }

    }

    /**
     * Reset.
     */
    public static void reset() {
        users = new ArrayList<>();
        songs = new ArrayList<>();
        podcasts = new ArrayList<>();
        timestamp = 0;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Sets users.
     *
     * @param userInputList the user input list
     */
    public static void setUsers(final List<UserInput> userInputList) {
        users = new ArrayList<>();
        for (UserInput userInput : userInputList) {
            users.add(new User(userInput.getUsername(), userInput.getAge(), userInput.getCity()));
        }
    }
}
