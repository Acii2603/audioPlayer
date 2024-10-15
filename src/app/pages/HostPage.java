package app.pages;

import app.user.Host;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HostPage {
    private Host host;

    public HostPage(Host host) {
        this.host = host;
    }
}
