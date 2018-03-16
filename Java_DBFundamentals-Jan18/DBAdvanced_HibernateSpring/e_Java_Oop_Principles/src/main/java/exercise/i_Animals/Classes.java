package exercise.i_Animals;

public enum Classes {
    DOG ("Dog", Dog.class, "BauBau"),
    CAT ("Cat", Cat.class, "MiauMiau"),
    FROG ("Frog", Frog.class, "Frogggg"),
    KITTEN ("Kitten", Kitten.class, "Miau"),
    TOMCAT ("Tomcat", Tomcat.class, "Give me one million b***h");

    private final String value;
    private final Class theClass;
    private final String sound;

    private Classes(final String value, Class theClass, final String sound){
        this.value = value;
        this.theClass = theClass;
        this.sound = sound;
    }

    public String getValue(){
        return this.value;
    }

    public Class getTheClass(){
        return this.theClass;
    }

    public String getSound() {
        return this.sound;
    }
}
