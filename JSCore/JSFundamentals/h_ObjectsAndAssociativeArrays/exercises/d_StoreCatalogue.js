function storeCatalogue(input) {

    let products = new Map();
    input
        .map(r => {
            let [p, c] = r.split(/\s+:\s+/);
            return [p, Number(c)]
        })
        .sort((p1, p2) => p1[0].localeCompare(p2[0]))
        .map(e => {
            let [l, n, c] = [e[0][0], e[0], e[1]];
            products.set(l, products.get(l) || new Map());
            products.get(l).set(n, c);
        })
    ;

    let result = [];
    products.forEach((val, key) => {
            result.push(key);
            val.forEach((v, k) => result.push(`  ${k}: ${v}`))
        }
    );

    console.log(result.join(`\n`));
}

storeCatalogue([
    'Appricot : 20.4',
    'Fridge : 1500',
    'TV : 1499',
    'Deodorant : 10',
    'Boiler : 300',
    'Apple : 1.25',
    'Anti-Bug Spray : 15',
    'T-Shirt : 10'
]);

storeCatalogue([
    'Banana : 2',
    "Rubic's Cube : 5",
    'Raspberry P : 4999',
    'Rolex : 100000',
    'Rollon : 10',
    'Rali Car : 2000000',
    'Pesho : 0.000001',
    'Barrel : 10'
]);
