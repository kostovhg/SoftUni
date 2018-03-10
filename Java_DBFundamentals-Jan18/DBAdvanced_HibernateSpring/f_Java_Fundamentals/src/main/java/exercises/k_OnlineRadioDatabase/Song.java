package exercises.k_OnlineRadioDatabase;

import static exercises.k_OnlineRadioDatabase.Constants.*;

class Song {
    /* not necessary for judge
    private String artistName;
    private String songName;
    */
    private long songLength; // in seconds

    public Song(String artist, String name, String time) throws Exception {
        this.setArtistName(artist);
        this.setSongName(name);
        this.setSongLength(time);
    }

    private void setArtistName(String artistName) throws InvalidArtistNameException {
        if (artistName.length() < MIN_ARTIST_NAME_LENGTH || artistName.length() > MAX_ARTIST_NAME_LENGTH) {
            throw new InvalidArtistNameException();
        }
        // this.artistName = artistName;
    }

    private void setSongName(String songName) throws InvalidSongNameException {
        if (songName.length() < MIN_SONG_NAME_LENGTH || songName.length() > MAX_SONG_NAME_LENGTH) {
            throw new InvalidSongNameException();
        }
        // this.songName = songName;
    }

    private void setSongLength(String time) throws Exception {
        int minuteInt;
        int secondsInt;
        String[] timeStr = time.split(":");
        if (timeStr.length == 2) {
            try {
                minuteInt = Integer.parseInt(timeStr[0]);
                secondsInt = Integer.parseInt(timeStr[1]);
            } catch (NumberFormatException nfe) {
                throw new InvalidSongLengthException();
            }
        } else {
            throw new InvalidSongLengthException();
        }
        if (minuteInt < MIN_MINUTES || minuteInt > MAX_MINUTES) {
            throw new InvalidSongMinutesException();
        }
        if (secondsInt < MIN_SECONDS || secondsInt > MAX_SECONDS) {
            throw new InvalidSongSecondsException();
        }

        this.songLength = minuteInt * MIN_SEC_MULTIPLIER + secondsInt;
    }

    public long getSongLength() {
        return this.songLength;
    }
}
/*
Design a custom exception hierarchy for invalid songs:
InvalidSongException
oInvalidArtistNameException
oInvalidSongNameException
oInvalidSongLengthException
InvalidSongMinutesException
InvalidSongSecondsException
Validation
Artist name should be between 3 and 20 symbols.
    Song name should be between 3 and 30 symbols.
    Song length should be between 0 second and 14 minutes and 59 seconds.
        Song minutes should be between 0 and 14.
        Song seconds should be between 0 and 59.
Exception	                    Message
InvalidSongException	        "Invalid song."
InvalidArtistNameException	    "Artist name should be between 3 and 20 symbols."
InvalidSongNameException        "Song name should be between 3 and 30 symbols."
InvalidSongLengthException	    "Invalid song length."
InvalidSongMinutesException	    "Song minutes should be between 0 and 14."
InvalidSongSecondsException	    "Song seconds should be between 0 and 59."

Note: Check validity in the order artist name -> song name -> song length
 */
