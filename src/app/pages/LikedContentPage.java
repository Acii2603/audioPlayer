package app.pages;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;
import app.audio.LibraryEntry;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter

public class LikedContentPage extends LibraryEntry {
    private ArrayList<Song> likedSongs;
    private ArrayList<Playlist> likedPlaylists;

    public LikedContentPage(String name, ArrayList<Song> likedSongs, ArrayList<Playlist> likedPlaylists) {
        super(name);
        this.likedSongs = likedSongs;
        this.likedPlaylists = likedPlaylists;
    }
}
