package e_IntersectionOfCircles;

import java.math.BigDecimal;

public class Circle {

    private Point center;
    private BigDecimal radius;

    public Circle(String x, String y, String r){
        this.setCenter(x, y);
        this.setRadius(r);
    }

    public Circle(String[] xyr) {
        this.setCenter(xyr[0], xyr[1]);
        this.setRadius(xyr[2]);
    }

    public void setCenter(String x, String y) {
        this.center = new Point(x, y);
    }

    public void setRadius(String r) {
        this.radius = new BigDecimal(r);
    }

    public Point getCenter() {
        return this.center;
    }

    public BigDecimal getRadius() {
        return this.radius;
    }

    public double getRadiusAsDouble(){
        return this.radius.doubleValue();
    }

    public double getArea(){
        /* pi*r^2 */
        return Math.PI * this.radius.pow(2).doubleValue();
    }

    public double getCircumference(){
        /* 2 * pi * r          */
        return 2.0 * Math.PI * this.getRadiusAsDouble();
    }

    public boolean intersectWithCircle(Circle otherCircle){
        Double centresDistance = this.center.getDistanceToPoint(otherCircle.getCenter());
        Double sumOfRadiuses = this.radius.add(otherCircle.radius).doubleValue();

        return sumOfRadiuses >= centresDistance;
    }
}
