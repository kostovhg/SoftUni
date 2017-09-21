package t01_IntroToJava_E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Methods {

    public static void main(String[] args) {
	// write your code here
    }

    public static int[] readIntArr(String[] line) throws IOException{
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[line.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }
        return arr;
    }
}
