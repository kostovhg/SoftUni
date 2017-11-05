package p03_Shapes;

public abstract class Shape {
    private double perimeter;
    private double area;

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter() {
        this.calculatePerimeter();
    }

    public void setArea() {
        this.calculateArea();
    }

    abstract double calculatePerimeter();

    abstract double calculateArea();
}
