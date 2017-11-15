package p05_BorderControl;

public class Robot extends BaseVisitor implements Visitor {

    private String model;

    public Robot(String[] args){
        super(args);
        this.setModel(args[0]);
    }

    private void setModel(String model){
        this.model = model;
    }

}
