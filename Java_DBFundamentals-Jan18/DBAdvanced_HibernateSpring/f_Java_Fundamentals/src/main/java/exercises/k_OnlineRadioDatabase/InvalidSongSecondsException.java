package exercises.k_OnlineRadioDatabase;

import static exercises.k_OnlineRadioDatabase.Constants.*;

class InvalidSongSecondsException extends Exception {

    public InvalidSongSecondsException(){
        super(String.format(
                INTERVAL_FORMAT, SONG, RGN_SECONDS,
                MIN_SECONDS, MAX_SECONDS));
    }
}
