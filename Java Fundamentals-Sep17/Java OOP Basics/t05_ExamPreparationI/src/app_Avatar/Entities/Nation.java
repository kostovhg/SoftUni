package app_Avatar.Entities;

import java.util.ArrayList;
import java.util.List;

public class Nation {
    private final List<Bender> benders;
    private final List<Monument> monuments;

    public Nation() {
        this.benders = new ArrayList<>();
        this.monuments = new ArrayList<>();
    }

    public void addBender(Bender bender) {
        this.benders.add(bender);
    }

    public void addMonument(Monument monument) {
        this.monuments.add(monument);
    }

    public List<Bender> getBenders() {
        return benders;
    }

    public List<Monument> getMonuments() {
        return monuments;
    }

    public double getNationPower() {
        Double total = 0.0;
        for (Bender b : benders) {
            total += b.getTotalPower();
        }

        long sum = 0L;
        for (Monument m :
                monuments) {
            sum += m.getMonumentAffinity();
        }

        total += (total / 100) * sum;
        return total;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Benders:");
        if(this.benders.size() < 1) {
            sb.append(" None");
        } else {
            for (Bender bender : benders) {
                sb.append(System.lineSeparator());
                sb.append("###").append(bender);
            }
        }
        sb.append(System.lineSeparator());
        sb.append("Monuments:");
        if(this.monuments.size() < 1) {
            sb.append(" None");
        } else {
            for (Monument monument : monuments) {
                sb.append(System.lineSeparator());
                sb.append("###").append(monument);
            }
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
