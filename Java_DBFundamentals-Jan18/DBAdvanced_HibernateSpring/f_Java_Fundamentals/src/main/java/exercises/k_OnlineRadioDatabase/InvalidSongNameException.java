package exercises.k_OnlineRadioDatabase;


import static exercises.k_OnlineRadioDatabase.Constants.*;

class InvalidSongNameException extends Exception {

    public InvalidSongNameException(){
        super(String.format(
                INTERVAL_FORMAT_, SONG, RGN_SONG_NAME,
                MIN_SONG_NAME_LENGTH, MAX_SONG_NAME_LENGTH));
    }
}
