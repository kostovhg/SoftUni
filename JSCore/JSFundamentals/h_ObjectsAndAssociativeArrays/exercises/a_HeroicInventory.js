function heroicInventory(input) {

    let [heroes, hDelim, iDelim] = [[], /\s+\/\s+/, /\s+,\s+/];

    input
        .map(row => row.split(hDelim))
        .forEach(e =>
            heroes.push({name: e[0], level: +e[1], items: e.length > 2 ?
                e[2].split(iDelim).filter(i => i !== '') : []}));

    console.log(JSON.stringify(heroes));
}

heroicInventory([
    'Isacc / 25 / Apple, GravityGun',
    'Derek / 12 / BarrelVest, DestructionSword',
    'Hes / 1 / Desolator, Sentinel, Antara'
]);


heroicInventory([
    'Jake / 1000 / Gauss, HolidayGrenade'
]);
