package app.pages;

import app.user.Artist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistPage {
    private final Artist artist;

    public ArtistPage(Artist artist) {
        this.artist = artist;
    }

}
