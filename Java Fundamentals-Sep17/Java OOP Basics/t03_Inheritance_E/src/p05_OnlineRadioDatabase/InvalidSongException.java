package p05_OnlineRadioDatabase;

public class InvalidSongException extends IllegalArgumentException {


    public InvalidSongException(String msg){
        super(msg);
    }
}

class InvalidArtistNameException extends InvalidSongException {
    public InvalidArtistNameException() {
        super("Artist name should be between 3 and 20 symbols.");
    }
}

class InvalidSongNameException extends InvalidSongException {
    public InvalidSongNameException() {
        super("Song name should be between 3 and 30 symbols.");
    }
}

class InvalidSongLengthException extends InvalidSongException {

    public InvalidSongLengthException(String msg) {
        super(msg);
    }

    public InvalidSongLengthException(){
        super("Invalid song length.");
    }
}

class InvalidSongMinutesException extends InvalidSongLengthException {
    public InvalidSongMinutesException() {
        super("Song minutes should be between 0 and 14.");
    }
}

class InvalidSongSecondsException extends InvalidSongLengthException {
    public InvalidSongSecondsException() {
        super("Song seconds should be between 0 and 59.");
    }
}
/*
Artist name should be between 3 and 20 symbols.
        Song name should be between 3 and 30 symbols.
        Song lengthSong seconds should be between 0 and 59.
         should be between 0 second and 14 minutes and 59 seconds.
        Song minutes should be between 0 and 14.

        "Artist name should be between 3 and 20 symbols."
"Song name should be between 3 and 30 symbols."
"Invalid song length."
"Song minutes should be between 0 and 14."
"Song seconds should be between 0 and 59."
*/