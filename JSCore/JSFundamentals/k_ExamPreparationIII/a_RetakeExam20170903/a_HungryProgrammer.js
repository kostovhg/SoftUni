function hungryProgrammer(food, activities) {

    let [eaten, meals, commands, end, func] = [
        0, food, activities, false,
        {
            checkIndexes: (st, end) => {
                //return !!meals[st] && !!meals[end] && end > st;
                return st && end && st >= 0 && end < meals.length && end > st;
            },
            Serve: () => (meals.length > 0) ? console.log(`${meals.pop()} served!`) : null,
            Add: ([meal]) => (meal && meal !== '') ? meals.unshift(meal) : null,
            Shift: ([i1, i2]) => {
                if (func.checkIndexes(i1, i2)) {
                    let temp = [meals[i2], meals[i1]];
                    meals[i1] = temp[0];
                    meals[i2] = temp[1];
                }
            },
            Eat: () => {
                if(meals.length > 0) {
                    console.log(`${meals.shift()} eaten`);
                    eaten++;
                }
            },

            Consume: ([st, end]) => {
                if (func.checkIndexes(st, end)) {
                    let count = end - st + 1;
                    let m = meals.splice(st, count);
                    eaten += count;
                    console.log(`Burp!`);
                }
            },
            End: () => {
                console.log((meals.length > 0 ? `Meals left: ${meals.join(', ')}` : `The food is gone`));
                console.log(`Meals eaten: ${eaten}`);
                end = true;
            }
        }
    ];

    for (let command of commands) {
        let params = command.split(/\s+/);
        let com = params.shift();
        if(!end) {
            func[com](params);
        } else {
            return;
        }
    }
}


hungryProgrammer(
    ['chicken', 'steak', 'eggs'],
    ['Serve', 'Eat', 'End', 'Consume 0 1']);
/*      eggs served!
        chicken eaten
        Meals left: steak
        Meals eaten: 1  */

hungryProgrammer(
    ['fries', 'fish', 'beer', 'chicken', 'beer', 'eggs'],
    ['Add spaghetti', 'Shift 0 1', 'Consume 1 4', 'End']);
/*      Burp!
        Meals left: fries, beer, eggs
        Meals eaten: 4  */


hungryProgrammer(
    ['carrots', 'apple', 'beet'],
    ['Consume 0 2', 'End',]);
/*      Burp!
        The food is gone
        Meals eaten: 3  */

hungryProgrammer([`bacon`, `veggies`, `chiken`], [`Add`, `End`]);

hungryProgrammer(['soup', 'spaghetti', 'eggs'],
    ['Consume 0 2', 'Eat', 'Eat', 'Shift 0 1', 'End', 'Eat']);

hungryProgrammer([],[
'Serve', 'Eat', 'Consume 0 0', 'Add chicken', 'Add rice', 'Eat', 'End']);
