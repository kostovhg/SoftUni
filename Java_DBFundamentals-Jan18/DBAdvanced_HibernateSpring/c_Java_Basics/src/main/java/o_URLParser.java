import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class o_URLParser {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();

        int protSep = input.indexOf("://");
        String protocol = protSep > -1 ? input.substring(0, protSep) : "";
        input = input.replace(protocol + "://", "");
        int resSep = input.indexOf("/");
        String resources = resSep > -1 ? input.substring(resSep) : "";
        String server = input.replace(resources, "");
        if (resources.length() > 0) {
            resources = resources.substring(1);
        }

        System.out.printf("[protocol] = \"%s\"%n[server] = \"%s\"%n[resource] = \"%s\"",
                protocol.trim(), server.trim(), resources.trim());

    }
}
