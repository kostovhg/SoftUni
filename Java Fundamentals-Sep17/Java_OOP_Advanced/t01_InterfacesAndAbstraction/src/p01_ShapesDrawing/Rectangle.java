package p01_ShapesDrawing;

public class Rectangle implements Drawable {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void drawComplete() {
        for (int i = 0; i < height; i++) {
            System.out.print("*");
            for (int k = 1; k < width - 1; k++) {
                System.out.print(" ");
                if(i == 0 || i == (height - 1)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print(" ");
            System.out.print("*");
            System.out.print("\n");
        }
    }

    @Override
    public void draw(){
        drawLine(this.width, '*', '*');
        for (int i = 1; i < this.height - 1; ++i)
            drawLine(this.width, '*', ' ');
        drawLine(this.width, '*', '*');
    }

    private void drawLine(int width, char end, char mid){
        System.out.print(end);
        for (int i = 1; i < width - 1; ++i)
            System.out.print(mid);
        System.out.println(end);
    }


}
