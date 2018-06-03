function autoEngineeringCompany(input) {

    let theMap = new Map();
    input
        .forEach(r => {
            let [b, m, c] = r.split(/\s+\|\s+/); // split to brand(b), model(m) and price(c) - all strings
            theMap.set(b, theMap.get(b) || new Map()); // if theMap.get(b) is false, create new Map
            theMap.get(b).set(m, ~~theMap.get(b).get(m) + +c); // set submap key m to (0|prev value) + Number(c)
        });

    let result = [];
    theMap.forEach((val, key) => {
            result.push(key);
            val.forEach((v, k) => result.push(`###${k} -> ${v}`))
        }
    );

    console.log(result.join(`\n`));

}

autoEngineeringCompany([
    'Audi | Q7 | 1000',
    'Audi | Q6 | 100',
    'BMW | X5 | 1000',
    'BMW | X6 | 100',
    'Citroen | C4 | 123',
    'Volga | GAZ-24 | 1000000',
    'Lada | Niva | 1000000',
    'Lada | Jigula | 1000000',
    'Citroen | C4 | 22',
    'Citroen | C5 | 10'
]);