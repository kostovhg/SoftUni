package p04_FirstAndReserveTeam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {
    private String name;

    private List<Person> firstTeam;
    private List<Person> reserveTeam;

    public Team(String name) {
        setName(name);
        firstTeam = new ArrayList<>();
        reserveTeam = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMember(Person member) {
        if (member.getAge() < 40) {
            firstTeam.add(member);
        } else {
            reserveTeam.add(member);
        }
    }

    public List<Person> getFirstTeam() {
        return Collections.unmodifiableList(firstTeam);
    }

    public List<Person> getReserveTeam() {
        return Collections.unmodifiableList(reserveTeam);
    }
}
