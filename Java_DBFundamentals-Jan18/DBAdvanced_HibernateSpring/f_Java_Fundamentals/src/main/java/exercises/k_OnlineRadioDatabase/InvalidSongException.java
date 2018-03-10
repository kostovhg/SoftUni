package exercises.k_OnlineRadioDatabase;

import static exercises.k_OnlineRadioDatabase.Constants.INVALID_SONG;

class InvalidSongException extends Exception {

    public InvalidSongException(){
        super(INVALID_SONG);
    }
}
