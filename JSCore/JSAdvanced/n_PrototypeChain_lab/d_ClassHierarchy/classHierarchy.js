function classHierarchy(){

    class Figure {
        constructor(){
            if(new.target === Figure){
                throw new TypeError(`Cannot construct Abstract instances directly`);
            }
            this._area = 0;
        }
        get area() {
            return this._area;
        }
        toString(){
            return `${this.constructor.name} - `;
        }
    }

    class Circle extends Figure {
        constructor(radius){
            super();
            this.radius = radius;
        }

        get radius() {
            return this._radius;
        }

        set radius(value) {
            if(value >= 0) {
                this._radius = value;
            }
        }

        get area() {
            return Math.PI * this.radius ** 2;
        }


        toString() {
            return super.toString() + `radius: ${this.radius}`;
        }
    }

    class Rectangle extends Figure {
        constructor(width, height){
            super();
            this.width = width;
            this.height = height;
        }


        get width() {
            return this._width;
        }

        set width(value) {
            if(value >=  0) {
                this._width = value;
            }
        }

        get height() {
            return this._height;
        }

        set height(value) {
            if(value >= 0) {
                this._height = value;
            }
        }

        get area() {
            return this.width * this.height;
        }


        toString() {
            return super.toString() + `width: ${this.width}, height: ${this.height}`;
        }
    }

    return {Figure, Circle, Rectangle}
}


let classes = classHierarchy();
let [Figure, Circle, Rectangle] = [classes.Figure, classes.Circle, classes.Rectangle];
let f = new Figure();       //Error
let c = new Circle(5);
console.log(c.area);        //78.53981633974483
console.log(c.toString());  //Circle - radius: 5
let r = new Rectangle(3,4);
console.log(r.area);        //12
console.log(r.toString());  //Rectangle - width: 3, height: 4