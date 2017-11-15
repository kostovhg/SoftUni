package p08_MilitaryElite.entities;

public enum MissionState {
    INPROGRES("inProgres"), FINISHED("Finished");

    private final String state;

    MissionState(String st){
        this.state = "Finished";
    }

    public String getState(){
        return this.state;
    }
}
