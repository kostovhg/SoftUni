class Stringer{

    constructor(theString, length){
        this.innerString = theString;
        this.innerLength = length;
    }

    increase(length){
        let newLength = this.innerLength + length;
        this.innerLength = (newLength < 0) ? 0 : newLength;
    }

    decrease(length) {
        let newLength = this.innerLength - length;
        this.innerLength = (newLength < 0) ? 0 : newLength;
    }

    toString() {

        return `${this.innerString.substr(0, this.innerLength)}${(this.innerString.length > this.innerLength) ? '...' : ''}`;
    }

}

let test = new Stringer("Test", 5);
console.log(test.toString()); //Test

test.decrease(3);
console.log(test.toString()); //Te...

test.decrease(5);
console.log(test.toString()); //...

test.increase(4);
console.log(test.toString()); //Test