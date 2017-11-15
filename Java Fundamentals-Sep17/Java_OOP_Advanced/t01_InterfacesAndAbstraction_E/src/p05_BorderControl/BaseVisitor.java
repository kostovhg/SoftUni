package p05_BorderControl;

public abstract class BaseVisitor implements Visitor{

    private String id;

    public BaseVisitor(String... tokens){
        this.setId(tokens[tokens.length - 1]);
    }

    void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
}
