function extendClass(classToExtend){
    classToExtend.prototype.species = "Human";
    classToExtend.prototype.toSpeciesString = function () {
        return `I am a ${this.species}. ${this.toString()}`
    }
}


class SomeClass{
    constructor(prop1, prop2){
        this.prop1 = prop1;
        this.prop2 = prop2;
    }
    aMethod(){
        console.log('This is a aMethod from aClass')
    }
    toString(){
        return `-> ${this.constructor.name} {prop1: ${this.prop1}, prop2: ${this.prop2}}`;
    }
};

extendClass(SomeClass);
console.log(`Some class:\n ${SomeClass}`);
console.log(`Some class __proto__: ${SomeClass.__proto__}`);
console.log(`Some class .prototype: ${SomeClass.prototype}`);
let obj = new SomeClass('property 1', 'property 2');
console.log(`Some class object.toSpeciesString() : ${obj.toSpeciesString()}`);
console.log(`Some class object print: ${obj}`);
console.log(`Some class object.__proto__: ${obj.__proto__}`);

