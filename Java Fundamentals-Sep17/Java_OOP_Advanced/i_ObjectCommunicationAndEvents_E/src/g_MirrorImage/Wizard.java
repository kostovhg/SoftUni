package g_MirrorImage;

import java.util.ArrayList;
import java.util.List;

public class Wizard {

    private int id;
    private String name;
    private int magicalPower;
    private List<Wizard> copies;

    public Wizard(int id, String name, int magicalPower) {
        this.id = id;
        this.name = name;
        this.magicalPower = magicalPower;
        this.copies = new ArrayList<>(2);
    }

    public List<Wizard> getCopies() {
        return this.copies;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void castFireball() {
        System.out.println(String.format("%s casts Fireball for %d damage", this.toString(), this.magicalPower));
        for (Wizard copy : copies) {
            copy.castFireball();
        }
    }

    public void createReflection (){
        System.out.println(String.format("%s casts Reflection", this.toString()));
        for (Wizard copy : this.copies) {
            copy.createReflection();
        }
        for (int i = 0; i < 2; i++) {
            if(this.copies.size() >= 2 ) break;
            Wizard nextWizard = WizardFactory.create(this.name, this.magicalPower / 2 );
            this.copies.add(nextWizard);
            Repository.add(nextWizard);
            //System.out.println(String.format("%s created a %s", this.toString(), copies.get(copies.size() - 1).toString()));
        }
    }

    @Override
    public String toString() {
        return String.format("%s %d", this.name, this.id);
    }
}
