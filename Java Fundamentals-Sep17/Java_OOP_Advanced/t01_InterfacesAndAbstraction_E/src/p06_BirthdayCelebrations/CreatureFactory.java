package p06_BirthdayCelebrations;

class CreatureFactory {

    public Creature createCreature(String[] args){
        switch (args[0].toLowerCase()){
            case "citizen":
                return new Citizen(args);
            case "pet":
                return new Pet(args);
            case "robot":
                return new Robot(args);
                default: return null;
        }
    }
}
