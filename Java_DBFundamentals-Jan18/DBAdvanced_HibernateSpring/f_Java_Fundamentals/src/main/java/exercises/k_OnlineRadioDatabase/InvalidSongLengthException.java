package exercises.k_OnlineRadioDatabase;

import static exercises.k_OnlineRadioDatabase.Constants.*;

class InvalidSongLengthException extends Exception {

    public InvalidSongLengthException(){
        super(INVALID_SONG_LENGTH);
    }
}
