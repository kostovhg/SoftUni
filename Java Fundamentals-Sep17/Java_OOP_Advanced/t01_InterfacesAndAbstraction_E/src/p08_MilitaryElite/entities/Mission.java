package p08_MilitaryElite.entities;

import static p08_MilitaryElite.entities.MissionState.FINISHED;
import static p08_MilitaryElite.entities.MissionState.INPROGRES;

public class Mission {

    String codeName;
    MissionState state;

    public Mission(String name, String state){
        this.codeName = name;
        this.setState(state);
    }

    private void setState(String state) {
        switch (state.toLowerCase()){
            case "inprogress":
                this.state = INPROGRES; break;
            case "finished":
                this.state = FINISHED; break;
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return String.format("Code Name: %s State: %s",
                this.codeName,
                this.state.getState());
    }
}
