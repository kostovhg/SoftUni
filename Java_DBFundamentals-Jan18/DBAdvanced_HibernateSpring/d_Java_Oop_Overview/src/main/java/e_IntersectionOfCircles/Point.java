package e_IntersectionOfCircles;

import java.math.BigDecimal;

public class Point {

    private BigDecimal coordinateX;
    private BigDecimal coordinateY;

    public Point(){
        this.setX("0");
        this.setY("0");
    }

    public Point(String x, String y) {
        this.setX(x);
        this.setY(y);
    }

    private void setX(String x) {
        this.coordinateX = new BigDecimal(x);
    }

    private void setY(String y) {
        this.coordinateY = new BigDecimal(y);
    }

    public BigDecimal getY() {
        return this.coordinateY;
    }

    public BigDecimal getX() {
        return this.coordinateX;
    }

    public double getDistanceToPoint(Point otherPoint){
        /*
        Returning double because the Math.sqrt is faster than other methods
        and its precision is acceptable in this case
         */
        BigDecimal commonX = getX().max(otherPoint.getX())
                .subtract(getX().min(otherPoint.getX()));
        BigDecimal commonY = getY().max(otherPoint.getY())
                .subtract(getY().min(otherPoint.getY()));

        return Math.sqrt((commonX.pow(2).add(commonY.pow(2))).doubleValue());

    }
}
