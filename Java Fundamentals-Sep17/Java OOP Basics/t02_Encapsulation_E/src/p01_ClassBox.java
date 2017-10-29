import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p01_ClassBox {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        double length = Double.parseDouble(reader.readLine());
        double width = Double.parseDouble(reader.readLine());
        double height = Double.parseDouble(reader.readLine());

        try {
            Box box = new Box(length, width, height);

            System.out.printf("Surface Area - %.2f%n", box.surfaceArea());
            System.out.printf("Lateral Surface Area - %.2f%n", box.lateralSurface());
            System.out.printf("Volume - %.2f%n", box.volume());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Box {
    private double length;
    private double width;
    private double height;

    private void setLength(double length) {
        if (length <= 0.0) {
            throw new IllegalArgumentException("Length cannot be zero or negative.");
        }
        this.length = length;
    }

    private void setWidth(double width) {
        if (width <= 0.0) {
            throw new IllegalArgumentException("Width cannot be zero or negative.");
        }
        this.width = width;
    }

    private void setHeight(double height) {
        if (height <= 0.0) {
            throw new IllegalArgumentException("Height cannot be zero or negative.");
        }
        this.height = height;
    }

    Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    double surfaceArea() {
        return 2 * (length * width + width * height + height * length);
    }

    double lateralSurface() {
        return 2 * height * (length + width);
    }

    double volume() {
        return height * length * width;
    }
}

