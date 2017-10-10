import java.util.*;

public class p16_DragonArmy {
    public static class Dragon {
        private String type;
        private String name;
        private int damage;
        private int health;
        private int armor;


        @Override
        public int hashCode(){
            return name.hashCode();
        }

        @Override
        public boolean equals(Object anotherDragon){
            if(!(anotherDragon instanceof Dragon)) return false;
            Dragon other = (Dragon) anotherDragon;
            return (this.name).equals(other.name) && (this.type).equals(other.type);
        }

        public Dragon(String str){
            String[] tokens = str.split("\\s+");
            type = tokens[0];
            name = tokens[1];
            damage = (tokens[2].equals("null")) ? 45 : Integer.parseInt(tokens[2]);
            health = (tokens[3].equals("null")) ? 250 : Integer.parseInt(tokens[3]);
            armor = (tokens[4].equals("null")) ? 10 : Integer.parseInt(tokens[4]);
        }
    }
    private static LinkedHashMap<String, TreeMap<String, int[]>> dragons = new LinkedHashMap<>();

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        int count = Integer.parseInt(scann.nextLine());
        for (int i = 0; i < count; i++) {
            Dragon current = new Dragon(scann.nextLine());
            if(!dragons.containsKey(current.type)) {
                dragons.put(current.type, new TreeMap<>());
            }
                //dragons.get(current.type)
                       // .put(current.name, new int[]{current.damage, current.health, current.armor});
                // dragons.get(current.type).put(current.name, current);
            if(dragons.get(current.type).containsKey(current.name)){
                dragons.get(current.type).get(current.name)[0] = current.damage;
                dragons.get(current.type).get(current.name)[1] = current.health;
                dragons.get(current.type).get(current.name)[2] = current.armor;
            } else {
                dragons.get(current.type)
                        .put(current.name, new int[]{current.damage, current.health, current.armor});
            }
        }

        for (String type :
                dragons.keySet()) {
            TreeMap<String, int[]> currType = dragons.get(type);
            calculateAndPrintAverage(currType, type);
            for (String dragon:
                    currType.keySet()) {
                int dmg = currType.get(dragon)[0];
                int hp = currType.get(dragon)[1];
                int arm = currType.get(dragon)[2];
                System.out.format("-%s -> damage: %d, health: %d, armor: %d%n", dragon, dmg, hp, arm);
            }
        }
    }

    private static void calculateAndPrintAverage(TreeMap<String, int[]> currType, String type) {
        double count = currType.size();
        double[] param = new double[3];
        for (String dragon :
                currType.keySet()) {
            for (int i = 0; i < 3; i++) {
                param[i] += currType.get(dragon)[i];
            }
        }
        System.out.format("%s::(%.2f/%.2f/%.2f)%n", type, param[0] / count, param[1]/ count, param[2] / count );
    }
}

