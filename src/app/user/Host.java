package app.user;

import app.audio.Collections.Announcement;
import app.audio.Collections.Podcast;

import java.util.ArrayList;

public class Host extends User {
    private ArrayList<Podcast> podcasts;
    private ArrayList<Announcement> announcements;

    public Host(String username,
                int age,
                String city) {
        super(username, age, city);
        podcasts = new ArrayList<>();
        announcements = new ArrayList<>();
    }

    public Host(String username, int age, String city, ArrayList<Podcast> podcasts, ArrayList<Announcement> announcements) {
        super(username, age, city);
        this.podcasts = podcasts;
        this.announcements = announcements;
    }

    public void addAnnouncement(Announcement announcement) {
        announcements.add(announcement);
    }

    public void addPodcast(Podcast podcast) {
        podcasts.add(podcast);
    }

    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    public ArrayList<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(ArrayList<Announcement> announcements) {
        this.announcements = announcements;
    }
}