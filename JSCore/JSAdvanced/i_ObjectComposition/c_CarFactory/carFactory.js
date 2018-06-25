function carFactory(order) {

    return (function (that) {

        let engines = [
            {power: 90, volume: 1800},
            {power: 120, volume: 2400},
            {power: 200, volume: 3500}];
        let carriage = [
            {type: 'hatchback', color: ''},
            {type: 'coupe', color: ''}
        ];
        let getEngine = (hp) => {
            for (let engine of engines) {
                if(hp <= engine.power){
                    return engine;
                }
            }
        };

        //console.log(that);
        let getCarriage = (wanted) =>  {
            let c = carriage.find(x => x.type === wanted);
            c.color = that.color;
            return c;
        };

        let getWheels = (size) => Array.apply(null, Array(4)).map(x => size - (size + 1) % 2);

        let theEngine = getEngine(that.power);
        let theCarriage = getCarriage(that.carriage);
        let theWheels = getWheels(that.wheelsize);

        delete that.power;
        delete that.color;
        delete that.carriage;
        delete that.wheelsize;

        that.engine = theEngine;
        that.carriage = theCarriage;
        that.wheels = theWheels;

       //console.log(that);

        return that

    })(order);

}
/*
{ model: <model name>,
  power: <minimum power>,
  color: <color>,
  carriage: <carriage type>,
  wheelsize: <size> }
 */

// { model: 'VW Golf II',
//     engine: { power: 90,
//     volume: 1800 },
//     carriage: { type: 'hatchback',
//         color: 'blue' },
//     wheels: [13, 13, 13, 13] }
console.log(
carFactory(
    { model: 'VW Golf II',
    power: 90,
    color: 'blue',
    carriage: 'hatchback',
    wheelsize: 14 }));
console.log(
carFactory(
    { model: 'Opel Vectra',
    power: 110,
    color: 'grey',
    carriage: 'coupe',
    wheelsize: 17 }));
