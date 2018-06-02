function cappyJuice(input) {

    let [bottles, juices, delim, bottleVolume] = [new Set(), new Set(), /\s+=>\s+/, 1000];

    input.forEach(row => {
            let [type, quantity] = row.split(delim);
            quantity = Number(quantity);
            juices[type] = ~~juices[type] + quantity;
            juices[type] >= bottleVolume ?
                bottles[type] = ~~(bottles[type]) + ~~(juices[type] / bottleVolume) :
                null;
            juices[type] %= bottleVolume;
        }
    );

    for (let key in bottles) {
        console.log(`${key} => ${bottles[key]}`);
    }

    // Object.keys(bottles).forEach(b => console.log(`${b} => ${bottles[b]}`));
}


cappyJuice([
    'Orange => 2000',
    'Peach => 1432',
    'Banana => 450',
    'Peach => 600',
    'Strawberry => 549'
]);
cappyJuice([
    'Kiwi => 234',
    'Pear => 2345',
    'Watermelon => 3456',
    'Kiwi => 4567',
    'Pear => 5678',
    'Watermelon => 6789']);
