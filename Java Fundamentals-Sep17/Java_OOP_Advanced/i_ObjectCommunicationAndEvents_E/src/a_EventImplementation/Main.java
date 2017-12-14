package a_EventImplementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();
        Dispatcher dispatcher = new Dispatcher();
        NameChangeListener listener = new Handler();
        dispatcher.addNameChangeLIstener(listener);

        while(true){
            if("End".equals(line)) break;

            dispatcher.setName(line);
            line = reader.readLine();
        }
    }
}
