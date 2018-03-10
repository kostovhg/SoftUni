package exercises.k_OnlineRadioDatabase;

import static exercises.k_OnlineRadioDatabase.Constants.*;

class InvalidSongMinutesException extends Exception {

    public InvalidSongMinutesException(){
        super(String.format(
                INTERVAL_FORMAT, SONG, RGN_MINUTES,
                MIN_MINUTES, MAX_MINUTES));
    }
}
