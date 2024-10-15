package app.audio.Files;

import app.audio.Collections.Announcement;
import app.audio.Collections.Podcast;
import app.audio.LibraryEntry;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class HostFile extends LibraryEntry {
    private ArrayList<Podcast> podcasts;
    private ArrayList<Announcement> announcements;

    public HostFile(final String name, final ArrayList<Podcast> podcasts,
                    final ArrayList<Announcement> announcements) {
        super(name);
        this.podcasts = podcasts;
        this.announcements = announcements;
    }
}
