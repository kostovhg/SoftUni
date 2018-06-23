function breakfastRobot() {

    let elements = ['protein', 'carbohydrate', 'fat', 'flavour'];
    let storage = {protein: 0, carbohydrate: 0, fat: 0, flavour: 0};

    let recipes = {
        'apple': [0, 1, 0, 2],
        'coke': [0, 10, 0, 20],
        'burger': [0, 5, 7, 3],
        'omelet': [5, 0, 1, 1],
        'cheverme': [10, 10, 10, 10]
    };

    let commands = {
        restock: (args) => {
            if (storage.hasOwnProperty([args[0]])) {
                storage[args[0]] += Number(args[1]);
                return 'Success'
            } else {
                return `Error: there is no ${args[0]} ingredient type`
            }
        },
        prepare: function (args) {
            let r = recipes[args[0]];
            let a = Number(args[1]);
            for (let i = 0; i < r.length; i++) {
                if (r[i] * a > storage[elements[i]]) {
                    return `Error: not enough ${elements[i]} in stock`;
                }
            }
            elements.forEach((e, i) => storage[e] -= r[i] * a);
            return 'Success';
        },
        report: () => elements.map(e => `${e}=${storage[e]}`).join(' ')
    };

    return function execute(command) {
        if (command) {
            let args = command.split(' ');
            return commands[args.shift()](args);
        }
    }
}