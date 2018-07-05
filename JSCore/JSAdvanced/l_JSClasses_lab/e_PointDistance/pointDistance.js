class Point{

    constructor(x, y) {
        [this.x, this.y] = [x, y];
    }

    static distance(point1, point2){
        return Math.sqrt((point1.x - point2.x) **2 + (point1.y - point2.y)**2);
    }
}

let p1 = new Point(5, 5);
let p2 = new Point(10, 10);
console.log(
    Point.distance(p1, p2));