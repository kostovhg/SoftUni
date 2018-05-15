function figureArea(w, h, W, H) {

    class Rectangle {
        constructor(width, height) {
            this.height = height;
            this.width = width;
        }

        get area(){
            return this.calcArea();
        }

        calcArea() {
            return this.height * this.width;
        }
    }

    let rec1 = new Rectangle(w, h);
    let rec2 = new Rectangle(W, H);
    let rec3 = new Rectangle(Math.min(w, W), Math.min(h, H));

    console.log(rec1.area + rec2.area - rec3.area);
}


figureArea(2, 4, 5, 3);
figureArea(13, 2, 5, 8);

