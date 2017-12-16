package hell.repositories;

import hell.interfaces.Hero;
import hell.interfaces.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HeroRepository implements Repository<Hero> {

    private Map<String, Hero> heroes;

    public HeroRepository() {
        this.heroes = new LinkedHashMap<>();
    }

    @Override
    public void add(Hero element) {
        this.heroes.putIfAbsent(element.getName(), element);
    }

    @Override
    public List<Hero> findAll() {
        return new ArrayList<>(this.heroes.values());
    }

    @Override
    public Hero findByName(String name) {
        return this.heroes.get(name);
    }
}
