import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by uKBo on 7/26/2017.
 */
public class p03_UserDatabase {

        public static void main(String[] args) throws IOException {
            String path = "./users.txt";
            if(!Files.exists(Paths.get(path))) {
                File f = new File(path);
                f.createNewFile();
            }
            try(Scanner reader = new Scanner(new FileInputStream(path))) {
                HashMap<String, User> users = new HashMap<>();

                User loggedInUser = null;

                while (reader.hasNext()) {
                    String[] tokens = reader.nextLine().split(" ");

                    User user = new User();
                    user.Username = tokens[0];
                    user.Password = tokens[1];
                    user.IsLogged = Boolean.parseBoolean(tokens[2]);
                    if (user.IsLogged) {
                        loggedInUser = user;
                    }
                    users.put(user.Username, user);
                }

                Scanner scanner = new Scanner(System.in);

                while(true) {
                    String[] input = scanner.nextLine().split(" ");
                    if (input[0].equals("exit")){
                        break;
                    }

                    switch (input[0]) {
                        case "register":
                            String username = input[1];
                            String password = input[2];
                            String repeatPassword = input[3];
                            if (users.containsKey(username)) {
                                System.out.println("The given username already exists.");
                            }
                            else if (!password.equals(repeatPassword)) {
                                System.out.println("The two passwords must match.");
                            }
                            else {
                                User user = new User();
                                user.Username = username;
                                user.Password = password;
                                user.IsLogged = false;
                                users.put(user.Username, user);
                            }
                            break;
                        case "login":
                            String loginUser = input[1];
                            String loginPass = input[2];
                            if(!users.containsKey(loginUser)) {
                                System.out.println("There is no user with the given name");
                                continue;
                            }
                            User userToLogin = users.get(loginUser);
                            if( userToLogin.IsLogged) {
                                System.out.println("There is already a logged in user.");
                            }
                            else if(!loginPass.equals(userToLogin.Password)) {
                                System.out.println("The password you entered is incorrect.");
                            }
                            else {
                                users.get(loginUser).IsLogged = true;
                                loggedInUser = userToLogin;
                            }
                            break;
                        case "logout":
                            if (loggedInUser == null) {
                                System.out.println("There is no currently logged in user.");
                            }
                            else {
                                loggedInUser.IsLogged = false;
                                loggedInUser = null;
                            }
                            break;
                    }
                    try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
                        for (User user : users.values()) {
                            String userToWrite = String.format("%s %s %s%n", user.Username, user.Password, user.IsLogged);
                            writer.write(userToWrite);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
class User {
    public String Username;
    public String Password;
    public boolean IsLogged;
}
