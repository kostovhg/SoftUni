package f_KingsGambitExtended.models;

/**
 * Abstract class to keep common parameters
 */
abstract class Unit {

    /**
     * common field for all models extends current class
     */
    String name;

    /**
     * common constructor to set the common field
     * @param name - unique name for the unit
     */
    Unit(String name) {
        this.name = name;
    }

    /**
     * method for getting the common field
     * @return return the name of the unit
     */
    public String getName() {
        return this.name;
    }

}
