package p03_Shapes;

public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.setRadius(radius);
    }

    final double getRadius() {
        return radius;
    }

    final void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * this.getRadius();
    }

    @Override
    double calculateArea() {
        return this.getRadius() * this.getRadius() * Math.PI;
    }
}
