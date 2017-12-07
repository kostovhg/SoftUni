package c_GraphicEditor;


public class GraphicEditor {

    static void drawShape(Drawable drawable){
        drawable.draw();
    }

    public static void main(String[] args) {
        Shape rec = new Rectangle();
        Shape cir = new Circle();

        GraphicEditor.drawShape(rec);
        GraphicEditor.drawShape(cir);
    }
}
