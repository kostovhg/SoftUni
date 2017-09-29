import java.util.Scanner;

public class p12_ToTheStars {
    private static Scanner sc = new Scanner(System.in);

    public static class StarField{
        String name;
        Double xStart;
        Double xEnd;
        Double yStart;
        Double yEnd;

        public StarField(String[] line){
            this.name = line[0].toLowerCase();
            Double x = Double.parseDouble(line[1]);
            Double y = Double.parseDouble(line[2]);
            this.xStart = x - 1.0;
            this.xEnd = x + 1.0;
            this.yStart = y - 1.0;
            this.yEnd = y + 1.0;
        }

        public boolean InTheField(double x, double y){
            return (x >= this.xStart && x <= this.xEnd && y >= this.yStart && y <= this.yEnd);
        }
    }

    public static void main(String[] args) {
        StarField[] stars = new StarField[3];
        for (int i = 0; i < 3; i++) {
            stars[i] = new StarField(sc.nextLine().split("\\s+"));
        }

        String[] initCoord = sc.nextLine().split("\\s+");
        double x = Double.parseDouble(initCoord[0]);
        double y = Double.parseDouble(initCoord[1]);
        int turns = Integer.parseInt(sc.nextLine());

        for (double v = y; v <= turns + y; v++) {
            String position = "space";
            for (StarField field :
                    stars) {
                if (field.InTheField(x, v)) position = field.name;
            }
            System.out.println(position);
        }
    }
}