function gladiatorInventory(input) {

    let eq = [...(input.shift()).split(/\s+/)];
    let commands = {
        'Buy': (x) => eq.includes(x) ? null : eq.push(x),
        'Trash': (x) => {if(eq.includes(x)) {let val = commands.Get(x); eq.splice(val[1], 1); return val[0] }},
        'Repair': (x) => eq.includes(x) ? eq.push(commands.Trash(x)) : null,
        'Upgrade': (x) => {
            let [e, u] = x.split('-');
            eq.includes(e) ? eq.splice(eq.indexOf(e) + 1, 0, `${e}:${u}`) : null
        },
        'Fight!': () => console.log(eq.join(' ')),
        'Get': (x) => {let i = eq.indexOf(x); return [eq[i], i]}
    };

    input
        .map(row =>
            row.split(' '))
        .forEach(c => {
            let com = commands[c[0]];
            com(c[1]);
            //console.log(`${c} => ${eq}`);
        })

}

/*
Buy {equipment}
Trash {equipment}
Repair {equipment}
Upgrade {equipment}-{upgrade}
 */

gladiatorInventory([
    'SWORD Shield Spear',
    'Buy Bag',
    'Trash Shield',
    'Repair Spear',
    'Upgrade SWORD-Steel',
    'Fight!'
]);

gladiatorInventory([
    'SWORD Shield Spear',
    'Trash Bow',
    'Repair Shield',
    'Upgrade Helmet-V',
    'Fight!'
]);
