package exercises.k_OnlineRadioDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static exercises.k_OnlineRadioDatabase.Constants.*;

public class Main {

    private static final List<Song> SONGS = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int inputCounts = Integer.parseInt(reader.readLine());

        for (int i = 0; i < inputCounts; i++) {
            try {
                SONGS.add(createSong(reader.readLine()));
                System.out.println(SUCCESSFULLY_ADDED_SONG);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Songs added: " + SONGS.size());
        int[] times = timeArray();
        System.out.println(String.format("Playlist length: %dh %dm %ds", times[0], times[1], times[2]));
        reader.close();
    }

    private static Song createSong(String input) throws Exception {
        Matcher m = SONG_INPUT_PATTERN.matcher(input);
        if(!m.find()){
            throw new InvalidSongException();
        }
        String name = m.group(RGN_SONG_NAME);
        String artist = m.group(RGN_ARTIST_NAME);
        String time = m.group(RGN_TIME);

        return new Song(artist, name, time);
    }

    private static int[] timeArray() {
        int[] timeArray = new int[3];
        long totalSeconds = SONGS.stream().mapToLong(Song::getSongLength).sum();
        timeArray[2] = (int)totalSeconds % 60;
        timeArray[1] = (int)(totalSeconds / 60)%60;
        timeArray[0] = (int)(totalSeconds / 60)/60;
        return timeArray;
    }
}