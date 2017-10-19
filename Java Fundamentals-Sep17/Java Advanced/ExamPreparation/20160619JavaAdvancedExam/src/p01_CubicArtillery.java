import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/*
 This solution is taken and rewritten few times, also debugged couple of dozen times
 to figure out what is the logic. Unfortunately it is still mystery, especially if you
 are trying to compare the logic here with the problem description!
 Original solution was taken from
    Aleksandar.Tanev: https://softuni.bg/users/profile/show/aleksandar.tanev
 given in the forum of SoftUni:
    https://softuni.bg/forum/10331/csharpadvanced-exam-19-06-2016-problem1-cubic-artillery
 */
public class p01_CubicArtillery {
    // Keep bunkers in queue (FIFO)
    private static Deque<String> bunkers = new ArrayDeque<>();
    // Will keep only the weapons in the bunker in the head of queue of bunkers
    private static Deque<Integer> weapons = new ArrayDeque<>();
    // will keep the initial common capacity
    private static Integer maxCapacity;
    // will keep the capacity only for bunker at the heat of the queue
    private static Integer freeCapacity;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String line;
        maxCapacity = Integer.valueOf(reader.readLine());
        freeCapacity = maxCapacity;
        while(!"Bunker Revision".equals(line = reader.readLine())){
            String[] tokens = line.split("\\s+");
            for (String token : tokens) {
                // if the token is a weapon
                try {
                    // take it's power
                    Integer weapon = Integer.valueOf(token);
                    boolean inserted = false;

                    // IF the bunkers are more than one
                    while(bunkers.size() > 1){
                        // try to insert it in the current bunker in the head of queue
                        // if there is space
                        if(freeCapacity >=  weapon){
                            // insert the weapon in queue of weapons
                            // and reduce capacity of the bunker
                            insertWeapon(weapon);
                            // !--> In fact we need a check, even when judge test are passing without it
                            inserted = true;
                            // break and continue to the CONDITION FOR SINGLE BUNKER?!
                            break;
                        }

                        // if we can't insert it in the first one
                        if(weapons.size() == 0){
                            // and current bunker
                            // doesn't have weapons
                            System.out.println(String.format("%s -> Empty", bunkers.peek()));
                        } else {
                            // if we have weapons in current bunker
                            String weaponsContained = weapons.toString();
                            System.out.printf("%s -> %s%n", bunkers.peek(),
                                    weaponsContained.substring(1, weaponsContained.length() -1));
                        }

                        // remove the bunker and clear its weapons
                        bunkers.poll();
                        weapons.clear();
                        // return the initial capacity for the next bunker
                        freeCapacity = maxCapacity;
                        // so now we are going to next bunker,
                        // until there is only one bunker in the queue
                    }
                    // it passes judge without that condition, but without it
                    // it will put second time last weapon!!!
                    // Does peoples that write the problems and theirs descriptions
                    // knows what are they doing????
                    if(inserted) continue;
                    // if we have only one bunker left
                    /* CONDITION FOR SINGLE BUNKER */

                    if(maxCapacity >= weapon) { // if weapon power is under maxCapacity
                        while (freeCapacity < weapon) {
                            // take out old weapons and increase capacity
                            freeCapacity += weapons.poll();
                        }
                        // insert the weapon.
                        insertWeapon(weapon);
                    }
                    // all else is to miss to do whatever with the current weapon!
                } catch (NumberFormatException e){
                    // if the token is not number, add new bunker
                    bunkers.add(token);
                }
            }
        }
    }

    private static void insertWeapon(Integer weaponPower){
        weapons.add(weaponPower);
        freeCapacity -= weaponPower;
    }
}
