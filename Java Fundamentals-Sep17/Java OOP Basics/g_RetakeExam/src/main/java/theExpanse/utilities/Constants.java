package theExpanse.utilities;

public class Constants {

    public static final String TERMINATING_COMMAND = "end";
    public static final String DEFAULT_COLONY_NAME = "theColony";

    // Output formats
    public static final String COLONY_POTENTIAL_FORMAT = "potential: %d";
    public static final String FAMILIES_OUTPUT_FORMAT = "families: %d/%d";
    public static final String FAMILY_CAPACITY_FORMAT = "-%s: %d/%d";
    public static final String COLONISTS_FORMAT = "-%s: %d";


    // Entities classes names / create command parameters
    public static final String SOFTWARE_ENGINEER_CLASS_NAME = "SoftwareEngineer";
    public static final String HARDWARE_ENGINEER_CLASS_NAME = "HardwareEngineer";
    public static final String SOLDIER_CLASS_NAME = "Soldier";
    public static final String GENERAL_PRACTITIONER_CLASS_NAME = "GeneralPractitioner";
    public static final String SURGEON_CLASS_NAME = "Surgeon";

    // Medics classes signs
    public static final String SURGEON_PRECISE_SIGN = "precise";
    public static final String SURGEON_BUTCHER_SIGN = "butcher";
    public static final String GP_CARING_SIGN = "caring";
    public static final String GP_CARELESS_SIGN = "careless";

    // commands
    public static final String INSERT_COMMAND = "insert";
    public static final String REMOVE_COMMAND = "remove";
    public static final String GROW_COMMAND = "grow";
    public static final String POTENTIAL_COMMAND = "potential";
    public static final String CAPACITY_COMMAND = "capacity";
    public static final String FAMILY_COMMAND = "family";

    // additional command parameters
    public static final String REMOVE_COLONIST_PARAMETER = "colonist";
    public static final String REMOVE_FAMILY_PARAMETER = "family";

    // Colony messages
    public static final String COLONY_IS_FULL_MESSAGE = "colony is full";
    public static final String FAMILY_IS_FULL_MESSAGE = "family is full";
    public static final String FAMILY_DOES_NOT_EXIST_MESSAGE = "family does not exist";

}
