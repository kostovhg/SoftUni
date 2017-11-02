package p04_MordorsCrueltyPlan;

import java.util.HashMap;
import java.util.Map;

public class Wizard {
    private static final Map<String, Integer> FOOD_POINTS = getBasePoints();
    private static Map<String, Integer> getBasePoints(){
        Map<String, Integer> map = new HashMap<>();
        map.put("cram", 2);
        map.put("lembas", 3);
        map.put("apple", 1);
        map.put("melon", 1);
        map.put("honeycake", 5);
        map.put("mushrooms", -10);
        return map;
    }
    private int points;

    Wizard() {
        this.points = 0;
    }

    void eat(String food){
        if (!FOOD_POINTS.containsKey(food)) {
            this.points--;
        } else {
            this.points += FOOD_POINTS.get(food);
        }
    }

    int getPoints(){
        return this.points;
    }

    String getMood(){
        int p = this.points;
        if (p > 15) { return "JavaScript"; }
        if (p > -1) { return "Happy"; }
        if (p > - 6) {return "Sad"; }
        else return "Angry";
    }
}
