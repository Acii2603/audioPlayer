package app.user;

import app.audio.Collections.Album;
import app.audio.Collections.Event;
import app.audio.Collections.Merch;
import app.audio.Files.Song;
import app.pages.ArtistPage;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Artist extends User {
    private ArrayList<Album> albums;
    private ArrayList<Event> events;
    private ArrayList<Merch> merches;
    private ArtistPage artistPage;
    private int likes = 0;

    public Artist(String username,
                  int age,
                  String city) {
        super(username, age, city);
        albums = new ArrayList<>();
        events = new ArrayList<>();
        merches = new ArrayList<>();
    }

    public Artist(String username, int age, String city, ArrayList<Album> albums, ArrayList<Event> events, ArrayList<Merch> merches) {
        super(username, age, city);
        this.albums = albums;
        this.events = events;
        this.merches = merches;
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void addMerch(Merch merch) {
        merches.add(merch);
    }

    public void addLikes() {
        likes = 0;
        for (Album album : albums) {
            for (Song song : album.getSongs()) {
                likes += song.getLikes();
            }
        }
    }
}
