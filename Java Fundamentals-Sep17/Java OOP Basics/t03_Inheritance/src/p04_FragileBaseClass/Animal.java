package p04_FragileBaseClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Animal {
    protected List<Food> foodEaten;

    public Animal(List<Food> food_eaten){
        foodEaten = food_eaten;
    }

    public final void eat(Food food){ foodEaten.add(food); }

    public void eatAll(Food[] foods) { for (Food food : foods) { eat(food); }
        // Collections.addAll(foodEaten, foods);
    }
}

