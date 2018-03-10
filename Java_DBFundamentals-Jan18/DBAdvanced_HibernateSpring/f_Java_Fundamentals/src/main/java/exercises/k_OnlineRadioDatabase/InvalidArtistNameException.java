package exercises.k_OnlineRadioDatabase;

import static exercises.k_OnlineRadioDatabase.Constants.*;

class InvalidArtistNameException extends Exception {

    public InvalidArtistNameException(){
        super(String.format(
                INTERVAL_FORMAT_, ARTIST, RGN_SONG_NAME,
                MIN_ARTIST_NAME_LENGTH, MAX_ARTIST_NAME_LENGTH));
    }
}
