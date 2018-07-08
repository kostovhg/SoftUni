function createComputerHierarchy(){

    class Manufactured { // should be a base class for each class that have manufacturer property
        constructor(manufacturer){
            if(new.target === Manufactured){
                throw new Error('Abstract class cannot be instantiated directly')
            }
            this.manufacturer = manufacturer; //string property for the name of the manufacturer
        }
    }
    class Keyboard extends Manufactured {
        constructor(manufacturer, responseTime){
            super(manufacturer);
            this.responseTime = responseTime; //number property for the response time of the Keyboard.
        }
    }
    class Monitor extends Manufactured {
        constructor(manufacturer, width, height){
            super(manufacturer);
            this.width = width; //number property for the width of the screen.
            this.height = height; //number property for the height of the screen.
        }
    }
    class Battery extends Manufactured {
        constructor(manufacturer, expectedLife){
            super(manufacturer);
            this.expectedLife = expectedLife; //number property for the expected years of life of the Battery.
        }
    }
    class Computer extends Manufactured {
        constructor(manufacturer, processorSpeed, ram, hardDiskSpace){
            if(new.target === Computer){
                throw new Error('Abstract class cannot be instantiated directly')
            }
            super(manufacturer);
            this.processorSpeed = processorSpeed; //a number property containing the speed of the processor in GHz.
            this.ram = ram; // a number property containing the RAM of the computer in Gigabytes.
            this.hardDiskSpace = hardDiskSpace; // a number property containing the hard disk space in Terabytes.
        }
    }
    class Laptop extends Computer {
        constructor(manufacturer, processorSpeed, ram, hardDiskSpace, weight, color, battery){
            super(manufacturer, processorSpeed, ram, hardDiskSpace);
            this.weight = weight; // a number property containing the weight of the Laptop in Kilograms.
            this.color = color; // a string property containing the color of the Laptop.
            this.battery = battery; // an instance of the Battery class containing the Laptop's battery.
        }
        set battery(battery){
            if(!(battery instanceof Battery)){
                throw new TypeError('The given battery is not from class Battery')
            }
            this._battery = battery;
        }
        get battery(){
            return this._battery;
        }
    }
    class Desktop extends Computer {
        constructor(manufacturer, processorSpeed, ram, hardDiskSpace, keyboard, monitor){
            super(manufacturer, processorSpeed, ram, hardDiskSpace);
            this.keyboard = keyboard; // an instance of the Keyboard class containing the Desktop PC's Keyboard
            this.monitor = monitor; // an instance of the Monitor class containing the Desktop PC's Monitor
        }
        set monitor(monitor){
            if(!(monitor instanceof Monitor)){
                throw new TypeError('The given monitor is not from class Monitor')
            }
            this._monitor = monitor;
        }
        get monitor(){
            return this._monitor;
        }
        set keyboard(keyboard){
            if(!(keyboard instanceof Keyboard)){
                throw new TypeError('The given keyboard is not from class Keyboard')
            }
            this._keyboard = keyboard;
        }
        get keyboard(){
            return this._keyboard;
        }
    }

    return {
        Battery,
        Keyboard,
        Monitor,
        Computer,
        Laptop,
        Desktop
    }
}

// zero test 2
let classes = createComputerHierarchy();
let Computer = classes.Computer;
let Laptop = classes.Laptop;
let Desktop = classes.Desktop;
let Monitor = classes.Monitor;
let Battery = classes.Battery;
let Keyboard = classes.Keyboard;

let keyboard = new Keyboard('Logitech',70);
let monitor = new Monitor('Benq',28,18);
let desktop = new Desktop("JAR Computers",3.3,8,1,keyboard,monitor);

console.log(desktop.keyboard);

// zero test 3
let battery = new Battery('Energy',3);
let laptop = new Laptop("Hewlett Packard",2.4,4,0.5,3.12,"Silver",battery);
console.log(laptop.weight);