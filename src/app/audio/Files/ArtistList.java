package app.audio.Files;

import app.audio.Collections.Album;
import app.audio.Collections.Event;
import app.audio.Collections.Merch;
import app.audio.LibraryEntry;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ArtistList extends LibraryEntry {
    private ArrayList<Album> albums;
    private ArrayList<Event> events;
    private ArrayList<Merch> merches;

    public ArtistList(final String name, final ArrayList<Album> albums,
                      final ArrayList<Event> events,
                      final ArrayList<Merch> merches) {
        super(name);
        this.albums = albums;
        this.events = events;
        this.merches = merches;
    }
}
