package app.pages;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;
import app.user.Artist;
import app.user.Host;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class Page {
    private Artist artist;
    private Host host;
    private ArrayList<Song> songs;
    private ArrayList<Playlist> playlists;

    public Page(Artist artist) {
        this.artist = artist;
        host = null;
        songs = null;
        playlists = null;
    }

    public Page(Host host) {
        this.host = host;
        artist = null;
        songs = null;
        playlists = null;
    }

    public Page(ArrayList<Song> songs, ArrayList<Playlist> playlists) {
        this.songs = songs;
        this.playlists = playlists;
        artist = null;
        host = null;
    }

    public List<String> getTop5Songs() {
        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        List<String> topSongs = new ArrayList<>();
        int count = 0;
        for (Song song : sortedSongs) {
            if (count >= 5) {
                break;
            }
            topSongs.add(song.getName());
            count++;
        }
        return topSongs;
    }

    public List<String> getTop5Playlists() {
        List<Playlist> sortedPlaylists = new ArrayList<>(playlists);
        sortedPlaylists.sort(Comparator.comparingInt(Playlist::getFollowers)
                .reversed()
                .thenComparing(Playlist::getTimestamp, Comparator.naturalOrder()));
        List<String> topPlaylists = new ArrayList<>();
        int count = 0;
        for (Playlist playlist : sortedPlaylists) {
            if (count >= 5) {
                break;
            }
            topPlaylists.add(playlist.getName());
            count++;
        }
        return topPlaylists;
    }
}