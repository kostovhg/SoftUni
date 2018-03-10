package exercises.k_OnlineRadioDatabase;

import java.util.regex.Pattern;

class Constants {

    // Success Message
    static final String SUCCESSFULLY_ADDED_SONG = "Song added.";

    // for InvalidSongException
    static final String INVALID_SONG = "Invalid song.";

    // Regex group name (RGN_), patterns and often used strings
    static final String RGN_SONG_NAME = "name";
    static final String RGN_ARTIST_NAME = "artist";
    static final String RGN_TIME = "time";
    static final String RGN_MINUTES = "minutes";
    static final String RGN_SECONDS = "seconds";
    static final String SONG = "Song";
    static final String ARTIST = "Artist";
    private static final String INPUT_PATTERN =
            "^(?<" + RGN_ARTIST_NAME + ">[^;]+);" +
                    "(?<" + RGN_SONG_NAME + ">[^;]+);" +
                    "(?<" + RGN_TIME + ">[^;]+)$";
    static final Pattern SONG_INPUT_PATTERN = Pattern.compile(INPUT_PATTERN);

    // Interval format pattern common for all values out of range
    static final String INTERVAL_FORMAT = "%s %s should be between %d and %d.";
    static final String INTERVAL_FORMAT_ = "%s %s should be between %d and %d symbols.";

    // Length constants
    static final int MIN_ARTIST_NAME_LENGTH = 3;
    static final int MAX_ARTIST_NAME_LENGTH = 20;
    static final int MIN_SONG_NAME_LENGTH = MIN_ARTIST_NAME_LENGTH;
    static final int MAX_SONG_NAME_LENGTH = 30;
    static final int MIN_SECONDS = 0;
    static final int MAX_SECONDS = 59;
    static final int MIN_MINUTES = MIN_SECONDS;
    static final int MAX_MINUTES = 14;
    static final int MIN_SEC_MULTIPLIER = 60;
    // static final int MAX_SONG_LENGTH = MAX_MINUTES * MIN_SEC_MULTIPLIER + MAX_SECONDS;

    // general error messages
    static final String INVALID_SONG_LENGTH = "Invalid song length.";

   /* not necessary for this variant of  
   static final String SONG_TIME_LENGTH_PATTERN =
            "^(1[0-4]:[0-5][0-9]|0?[0-9]:[0-5][0-9])$";
            */
}
