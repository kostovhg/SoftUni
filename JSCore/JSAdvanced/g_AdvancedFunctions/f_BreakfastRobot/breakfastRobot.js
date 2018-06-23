/**
 * Function that is called as closure to keep few variables on initialization,
 * after that its return can be called to implement commands
 * @return {Function} that proceed the given commands
 */

function breakfastRobot() {

    /**
     * Variables for:
     * @elements that's are ingredients are microelements that compose different food type
     * @available that are a storage container that keep the complete amount of each ingredient
     * @recipes that are the recipes for each food type
     */
    let [elements, available, recipes] = [
        ['protein', 'carbohydrate', 'fat', 'flavour'],
        {},
        {
            'apple': {carbohydrate: 1, flavour: 2},
            'coke': {carbohydrate: 10, flavour: 20},
            'burger': {carbohydrate: 5, fat: 7, flavour: 3},
            'omelet': {protein: 5, fat: 1, flavour:1},
            'cheverme': {protein: 10, carbohydrate: 10, fat: 10, flavour: 10}
        }
    ];
    /**
     * Initialize the container
     */
    (() => elements.forEach(e => available[e] = 0))();

    /**
     * Initialize the commands commands
     * @type {{restock: restock, prepare: prepare, report: (function(): string)}}
     */
    let commands = {
        restock: (el, quantity) => {
            if (el in available) {
                available[el] += quantity;
                return 'Success'
            } else {
                return `Error: there is no ${el} ingredient type`
            }
        },
        prepare: (food, amount) => {
            let recipe = recipes[food];
            let components = Object.getOwnPropertyNames(recipe);
            for (let c of components){
                if (recipe[c] * amount > available[c]) {
                    return `Error: not enough ${c} in stock`;
                }
            };
            components.forEach(el => available[el] -= recipe[el] * amount);
            return 'Success';
        },
        report: () => elements.map(e => `${e}=${available[e]}`).join(' ')
    };

    return (command) => {
            let args = command.split(' ');
            return commands[args.shift()]((args[0] ||''), ~~[args[1]]);
    }
}

// Tests
let manager0 = breakfastRobot();
console.log(manager0('restock flavour 50'));
console.log(manager0('prepare coke 4'));
console.log('--------------');

let manager1 = solution();
console.log(manager1('restock carbohydrate 10'));
console.log(manager1('restock flavour 10'));
console.log(manager1('prepare apple 1'));
console.log(manager1('restock fat 10'));
console.log(manager1('prepare burger 1'));
console.log(manager1('report'));
console.log('--------------');

let manager2 = breakfastRobot();
console.log(manager2('prepare cheverme 1'));
console.log(manager2('restock protein 10'));
console.log(manager2('prepare cheverme 1'));
console.log(manager2('restock carbohydrate 10'));
console.log(manager2('prepare cheverme 1'));
console.log(manager2('restock fat 10'));
console.log(manager2('prepare cheverme 1'));
console.log(manager2('restock flavour 10'));
console.log(manager2('prepare cheverme 1'));
console.log(manager2('report'));

let manager3 = breakfastRobot();
console.log(manager3('restock protein 100'));
console.log(manager3('restock carbohydrate 100'));
console.log(manager3('restock fat 100'));
console.log(manager3('restock flavour 100'));
console.log(manager3('report'));
console.log(manager3('prepare apple 2'));
console.log(manager3('report'));
console.log(manager3('prepare apple 1'));
console.log(manager3('report'));