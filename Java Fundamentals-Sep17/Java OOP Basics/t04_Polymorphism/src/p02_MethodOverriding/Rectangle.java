package p02_MethodOverriding;

public class Rectangle {
    protected double sideA;
    protected double sideB;

    public void setSideA(double sideA) {
        this.sideA = sideA;
    }

    public void setSideB(double sideB) {
        this.sideB = sideB;
    }

    public Rectangle(double sideA, double sideB) {
        this.setSideA(sideA);
        this.setSideB(sideB);
    }

    public Rectangle(double sideA){
        this.setSideA(sideA);
    }

    public double area(){
        return sideA * sideB;
    }
}

class Square extends Rectangle {
    public Square(double sideA) {
        super(sideA);
    }

    @Override
    public double area(){
        return sideA * sideA;
    }
}
