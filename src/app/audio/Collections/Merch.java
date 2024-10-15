package app.audio.Collections;

import lombok.Getter;

@Getter

public class Merch {
    private final String name;
    private final int price;
    private final int timestamp;
    private final String description;

    public Merch(final String name, final int price,
                 final int timestamp, final String description) {
        this.name = name;
        this.price = price;
        this.timestamp = timestamp;
        this.description = description;

    }

    public String getName() {
        return name;
    }
}
