package p08_MilitaryElite.entities;

import p08_MilitaryElite.interfaces.IMission;

public class Mission implements IMission {

    private static final String FINISHED = "Finished";
    private static final String IN_PROGRESS = "inProgress";

    private String codeName;
    private String state;

    public Mission(String name, String state){
        this.setState(state);
        this.setCodeName(name);
    }

    private void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    private void setState(String stateStr) {
        if(FINISHED.equals(stateStr) || IN_PROGRESS.equals(stateStr)){
            this.state = stateStr;
        }
    }

    @Override
    public String getState(){
        return this.state;
    }

    @Override
    public String getCodeName(){
        return this.codeName;
    }

    @Override
    public void completeMission(){
        this.state = FINISHED;
    }

    @Override
    public String toString() {
        return String.format("Code Name: %s State: %s",
                this.getCodeName(),
                this.getState());
    }
}
