function cappyJuice(input) {

    let [bottles, juices, regex, bottleVolume] = [new Set(), new Set(), /\s+=>\s+/, 1000];

    input.map(row => {
            let [j, q] = row.split(regex);
            q = Number(q);
            juices[j] = ~~juices[j] + q;
            juices[j] >= bottleVolume ?
                bottles[j] = ~~(bottles[j]) + ~~(juices[j] / bottleVolume) :
                undefined;
            juices[j] %= bottleVolume;
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
