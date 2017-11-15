package p06_BirthdayCelebrations;


class Robot implements Identificable {

    private String id;
    private String model;

    public Robot(String[] args){
        super();
        this.setId(args[2]);
        this.setModel(args[1]);
    }

    private void setModel(String model){
        this.model = model;
    }

    private void setId(String id) {
        this.id = id;
    }

    @Override
    public String getBirthdate() {
        return null;
    }

    @Override
    public String getId() {
        return this.id;
    }

}
