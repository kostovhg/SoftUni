package p05_OnlineRadioDatabase;

public class Song {

    private String artistName;
    private String songName;
    private int songMinutes;
    private int songSeconds;

    public Song(String aName, String sName, String time) throws InvalidSongException {
        this.setArtistName(aName);
        this.setSongName(sName);
        setTime(time);
    }


    public String getArtistName() {
        return artistName;
    }

    public String getSongName() {
        return songName;
    }

    public int getSongMinutes() {
        return songMinutes;
    }

    public int getSongSeconds() {
        return songSeconds;
    }

    private void setArtistName(String aName) throws InvalidArtistNameException {
        if (aName.length() < 3 || aName.length() > 20) {
            throw new InvalidArtistNameException();
        }
        this.artistName = aName;
    }

    private void setSongName(String sName) throws InvalidSongNameException {
        if (sName.length() < 3 || sName.length() > 30) {
            throw new InvalidSongNameException();
        }
        this.songName = sName;
    }

    private void setTime(String time) {
        String[] tokens = time.split(":");
        try {
            int m = Integer.parseInt(tokens[0]);
            int s = Integer.parseInt(tokens[1]);
            this.setSongMinutes(m);
            this.setSongSeconds(s);
        } catch (NumberFormatException nfe) {
            throw new InvalidSongLengthException();
        } catch (NullPointerException npe) {
            throw new InvalidSongLengthException();
        }
    }

    private void setSongMinutes(int minutes) throws InvalidSongMinutesException {
        if (minutes < 0 || minutes > 14) {
            throw new InvalidSongMinutesException();
        }
        this.songMinutes = minutes;
    }

    private void setSongSeconds(int seconds) throws InvalidSongSecondsException{
        if (seconds < 0 || seconds > 59) {
            throw new InvalidSongSecondsException();
        }
        this.songSeconds = seconds;
    }
}
