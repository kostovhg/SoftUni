package app_Avatar.Entities;

public abstract class Monument {
    private String name;

    protected Monument(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public abstract long getMonumentAffinity();
}
