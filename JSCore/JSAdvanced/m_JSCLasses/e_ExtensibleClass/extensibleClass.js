let result = (function () {

    let id = 0;

    return class Extensible {
        constructor() {
            this.id = id++;
        }
        extend(template){
            for (let prop in template) {
                ({}.toString.call(template[prop]) === '[object Function]') ?
                    Object.getPrototypeOf(this)[prop] = template[prop] :
                    this[prop] = template[prop];
            }
        }
    }

})();
let Extensible = result;

let obj1 = new Extensible();
let obj2 = new Extensible();
let obj3 = new Extensible();
console.log(obj1.id);
console.log(obj2.id);
console.log(obj3.id);

obj1.extend({extensionMethod: () => console.log('this is a method from template'), extensionProperty: 'Property from extension'});
console.log(obj1);