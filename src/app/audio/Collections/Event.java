package app.audio.Collections;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {
    private final String name;
    private final String date;
    private final int timestamp;
    private final String description;

    public Event(final String name, final String date,
                 final int timestamp, final String description) {
        this.name = name;
        this.date = date;
        this.timestamp = timestamp;
        this.description = description;

    }

}
