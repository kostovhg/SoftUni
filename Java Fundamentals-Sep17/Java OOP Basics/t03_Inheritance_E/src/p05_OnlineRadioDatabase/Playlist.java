package p05_OnlineRadioDatabase;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private List<Song> songs;
    private long lengthInSeconds;

    public Playlist(){
        this.songs = new ArrayList<>();
    }

    public void addSong(Song s) {
        this.songs.add(s);
    }

    private void setLengthInSeconds() {
        this.lengthInSeconds = songs.stream()
                .mapToLong(t -> (long) (t.getSongMinutes() * 60 + t.getSongSeconds())).sum();
    }

    public String getLength(){
        StringBuilder sb = new StringBuilder();
        this.setLengthInSeconds();
        int hours = (int) (this.lengthInSeconds / 3600);
        int minutes = (int)(lengthInSeconds - hours * 3600) / 60;
        int seconds = (int)(this.lengthInSeconds - hours * 3600 - minutes * 60 );

        return sb.append(hours).append("h ")
                .append(minutes).append("m ")
                .append(seconds).append("s ").toString();
    }

    public int getSongsAdded(){
        return this.songs.size();
    }
}
