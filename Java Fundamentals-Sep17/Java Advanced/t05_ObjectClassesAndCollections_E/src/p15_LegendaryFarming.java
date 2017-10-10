import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;

public class p15_LegendaryFarming {
    private static TreeMap<String, Integer> keyMaterials = new TreeMap<>();
    private static TreeMap<String, Integer> junks = new TreeMap<>();

    public static void main(String[] args) {
        keyMaterials.put("shards", 0);
        keyMaterials.put("fragments", 0);
        keyMaterials.put("motes", 0);
        Scanner scann = new Scanner(System.in);
        String line = scann.nextLine();
        String legendary = "";
        boolean found = false;
        while(!found){
            String[] input = line.toLowerCase().split("\\s+");
            String material;
            int count;
            for (int i = 0; i < input.length; i += 2) {
                material = input[i + 1];
                count = Integer.parseInt(input[i]);
                if(keyMaterials.containsKey(material)){
                    keyMaterials.put(material, keyMaterials.get(material) + count);
                    if(keyMaterials.get(material) >= 250 && legendary.equals("")){
                        keyMaterials.put(material, keyMaterials.get(material) - 250);
                        switch (material){
                            case "shards": legendary = "Shadowmourne"; break;
                            case "fragments": legendary = "Valanyr"; break;
                            case "motes": legendary = "Dragonwrath"; break;
                        }
                        found = true;
                        break;
                    }
                } else {
                    if(!junks.containsKey(material)){
                        junks.put(material, 0);
                    }
                    junks.put(material, junks.get(material) + count);
                }
            }
            if(found) break;
            line = scann.nextLine();
        }
        System.out.println(legendary + " obtained!");
        keyMaterials.entrySet().stream().sorted((m1, m2) ->
                        keyMaterials.get(m2.getKey()).compareTo(keyMaterials.get(m1.getKey())))
                .forEach(material -> System.out.format("%s: %d%n", material.getKey(), material.getValue()));
        for (String junk :
                junks.keySet()) {
            System.out.format("%s: %d%n", junk, junks.get(junk));
        }


    }
}