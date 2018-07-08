let classes = require('../f_Computer/computer')();

function mixins() {
    
    function computerQualityMixin(classToExtend){
        if(classToExtend.prototype === undefined){
            classToExtend = classToExtend.constructor;
        }

        classToExtend.prototype.getQuality = function () {
            return (this.processorSpeed + this.ram + this.hardDiskSpace) / 3
        };

        classToExtend.prototype.isFast = function () {
            return this.processorSpeed > (this.ram / 4)
        };

        classToExtend.prototype.isRoomy = function () {
            return this.hardDiskSpace  > Math.floor(this.ram * this.processorSpeed)
        };

    }

    function styleMixin(classToExtend){

        if(classToExtend.prototype === undefined){
            classToExtend = classToExtend.constructor;
        }

        classToExtend.prototype.isFullSet = function () {
            return (this.keyboard && this.monitor) ?
            this.manufacturer === (this.keyboard.manufacturer || '') &&
                this.manufacturer === (this.monitor.manufacturer || '') :
                new TypeError('Current class has no keyboard and monitor');
        };

        classToExtend.prototype.isClassy = function () {
            return (this.battery && this.color && this.weight)? this.battery.expectedLife >=3 &&
                (this.color === 'Silver' || this.color ==='Black') &&
                this.weight < 3 :
                new TypeError('Current class is not a laptop and does not have battery, color and weight properties')
        }
    }
    

    return {computerQualityMixin, styleMixin}
}

let m = mixins();
let addQualityMixin = m.computerQualityMixin;
let addStyleMixin = m.styleMixin;
let Computer = classes.Computer;
let Laptop = classes.Laptop;
let Desktop = classes.Desktop;
let Monitor = classes.Monitor;
let Battery = classes.Battery;
let Keyboard = classes.Keyboard;

let keyboard = new Keyboard('Logitech',70);
let monitor = new Monitor('Benq',28,18);
let desktop = new Desktop("JAR Computers",3.3,8,1,keyboard,monitor);

let battery = new Battery('Energy',3);
let laptop = new Laptop("Hewlett Packard",2.4,4,0.5,3.12,"Silver",battery);

addQualityMixin(Computer);
addQualityMixin(Desktop);
addQualityMixin(Laptop);
addQualityMixin(desktop);
addQualityMixin(laptop);

addStyleMixin(Computer);
addStyleMixin(Desktop);
addStyleMixin(Laptop);
addStyleMixin(desktop);
addStyleMixin(laptop);

console.log(`Desktop quality: ${desktop.getQuality()}`);
console.log(`Desktop isFast: ${desktop.isFast()}`);
console.log(`Desktop isRoomy: ${desktop.isRoomy()}`);
console.log(`Desktop isClassy: ${desktop.isClassy()}`);
console.log(`Desktop isFullSet: ${desktop.isFullSet()}`);

console.log(`Laptop quality: ${laptop.getQuality()}`);
console.log(`Laptop isFast: ${laptop.isFast()}`);
console.log(`Laptop isRoomy: ${laptop.isRoomy()}`);
console.log(`Laptop isClassy: ${laptop.isClassy()}`);
console.log(`Laptop isFullSet: ${laptop.isFullSet()}`);




