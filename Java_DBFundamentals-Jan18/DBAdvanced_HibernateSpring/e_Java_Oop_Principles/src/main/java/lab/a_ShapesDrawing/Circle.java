package lab.a_ShapesDrawing;

public class Circle implements Drawable {


    private Integer radius;

    public Circle(Integer unnecessary, Integer not_needed, Integer radius){
        this.radius = radius;
    }

    @Override
    public void draw() {
        double r_in = this.radius - 0.4;
        double r_out = this.radius + 0.4;

        for (double y = this.radius; y >= -this.radius; --y){
            for(double x = -this.radius; x < r_out; x += 0.5){
                double value = x * x + y * y;
                if (value > r_in * r_in && value <= r_out * r_out){
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }
}
