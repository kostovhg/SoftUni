package p05_OnlineRadioDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Playlist playlist = new Playlist();
        int count = Integer.parseInt(reader.readLine());
        for (int i = 0; i < count; i++) {
            String[] tokens = reader.readLine().split(";");
            try {
                playlist.addSong(new Song(tokens[0], tokens[1], tokens[2]));
                System.out.println("Song added.");
            } catch (InvalidSongException ise) {
                System.out.println(ise.getMessage());
            }
        }

        System.out.println("Songs added: " + playlist.getSongsAdded());
        System.out.println("Playlist length: " + playlist.getLength());

    }
}