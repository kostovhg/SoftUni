function objectInheritance(input) {

    let cars = new Map();

    let creator = (function () {
            let create = ([name, parent, parentName]) =>
                cars.set(name, parent ? Object.create(cars.get(parentName)) : {});
            let set = ([name, key, value]) => cars.get(name)[key] = value;
            let print = ([name]) => {
                let car = cars.get(name), objects = [];
                for (let key in car) {
                    objects.push(`${key}:${car[key]}`)
                }
                console.log(objects.join(', '));
            };
            return {create, set, print};
        }
    )
    ();

    for (let line of input) {
        let [command, ...args] = line.split(' ');
        creator[command](args);
    }
}

/*
create <name> - creates an object with the supplied <name>
create <name> inherits <parentName> - creates an object with the given <name>,
    that inherits from the parent object with the <parentName>
set <name> <key> <value> - sets the property with key equal to <key> to <value>
    in the object with the supplied <name>.
print <name> - prints the object with the supplied <name> in the format
    "<key1>:<value1>,<key2>:<value2>…" -
    the printing should also print all inherited properties from parent objects.
    Inherited properties should come after own properties.
 */

objectInheritance([
    'create c1',
    'create c2 inherit c1',
    'set c1 color red',
    'set c2 model new',
    'print c1',
    'print c2'
]);
