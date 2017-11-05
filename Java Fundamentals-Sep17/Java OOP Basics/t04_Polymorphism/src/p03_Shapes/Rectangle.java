package p03_Shapes;

public class Rectangle extends Shape{
    private double height;
    private double width;

    public Rectangle(double height, double width) {
        this.setHeight(height);
        this.setWidth(width);
    }

    final double getHeight() {
        return height;
    }

    private void setHeight(double height) {
        this.height = height;
    }

    final double getWidth() {
        return width;
    }

    private void setWidth(double width) {
        this.width = width;
    }

    public double calculatePerimeter(){
        return 2 * this.getHeight() + 2 * this.getWidth();
    }

    public double calculateArea() {
        return this.getHeight() * this.getWidth();
    }
}
