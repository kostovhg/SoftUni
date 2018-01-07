package theExpanse.factories;

import theExpanse.models.colonists.Colonist;
import theExpanse.models.colonists.engineers.HardwareEngineer;
import theExpanse.models.colonists.engineers.SoftwareEngineer;
import theExpanse.models.colonists.medics.GeneralPractitioner;
import theExpanse.models.colonists.medics.Surgeon;
import theExpanse.models.colonists.others.Soldier;

import java.util.List;

import static theExpanse.utilities.Constants.*;

public class ColonistFactory {

    private ColonistFactory(){}

    public static Colonist create(List<String> params) {

        String type = params.get(0);
        String colonistId = params.get(1);
        String familyId = params.get(2);
        int talent = Integer.parseInt(params.get(3));
        int age = Integer.parseInt(params.get(4));

        switch (type) {
            case SOFTWARE_ENGINEER_CLASS_NAME:
                return new SoftwareEngineer(colonistId, familyId, talent, age);
            case HARDWARE_ENGINEER_CLASS_NAME:
                return new HardwareEngineer(colonistId, familyId, talent, age);
            case SOLDIER_CLASS_NAME:
                return new Soldier(colonistId, familyId, talent, age);
            case GENERAL_PRACTITIONER_CLASS_NAME:
                return new GeneralPractitioner(colonistId, familyId, talent, age, params.get(5));
            case SURGEON_CLASS_NAME:
                return new Surgeon(colonistId, familyId, talent, age, params.get(5));
            default:
                return null;
        }
    }
}
