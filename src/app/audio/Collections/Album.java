package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Album extends AudioCollection {

    private final ArrayList<Song> songs;
    private int timestamp;
    private int releaseYear;
    private String description;
    private int likes = 0;

    public Album(final String name,
                 final String owner,
                 final int timestamp,
                 final String description,
                 final ArrayList<Song> songs) {
        super(name, owner);
        this.songs = songs;
        this.timestamp = timestamp;
        this.description = description;
    }

    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }

    @Override
    public AudioFile getTrackByIndex(final int index) {
        return songs.get(index);
    }

    public boolean containsSong(final Song song) {
        return songs.contains(song);
    }

    public void addSong(final Song song) {
        songs.add(song);
    }

    public void addLikes() {
        likes = 0;
        for (Song song : songs) {
            likes += song.getLikes();
        }
    }

}
