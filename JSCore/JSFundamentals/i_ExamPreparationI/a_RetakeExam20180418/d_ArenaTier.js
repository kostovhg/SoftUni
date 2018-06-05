function arenaTier(input) {

    let tier = new Map();

    input
        .map(i => i.split(/ -> | vs /))
        .forEach(i => {
            if (i.length > 2) {
                let [n, s, p] = i;
                if (!tier.has(n)) {
                    tier.set(n, new Map([[s, +p]]))
                } else if (~~tier.get(n).get(s) < +p) {
                    tier.get(n).set(s, +p)
                }
            } else if (i.length > 1) {
                let [g1, g2] = i;
                if (~~tier.has(g1) & ~~tier.has(g2)) {
                    if ([...tier.get(g1).keys()].filter(value => -1 !== [...tier.get(g2).keys()].indexOf(value)).length > 0) {
                        let dif = total(g1) - total(g2);
                        if (dif > 0) tier.delete(g2);
                        if (dif < 0) tier.delete(g1);
                    }
                }
            } else {
                printTier();
                return 0;
            }
        });

    function total(g) {
        return [...tier.get(g).values()].reduce((a, b) => a + b)
    }

    function printTier() {
        [...tier.keys()].sort((a, b) => ~~(total(b) - total(a)) || a.localeCompare(b))
            .forEach(g => {
                    console.log(`${g}: ${total(g)} skill`);
                    [...tier.get(g)].sort((a, b) => ~~(b[1] - a[1]) || a[0].localeCompare(b[0]))
                        .forEach((k) =>
                            console.log(`- ${k[0]} <!> ${k[1]}`))
                })
    }
}


arenaTier([
    'Pesho -> BattleCry -> 400',
    'Gosho -> PowerPunch -> 300',
    'Stamat -> Duck -> 200',
    'Stamat -> Tiger -> 250',
    'Ave Cesar'
]);

arenaTier([
    'Pesho -> Duck -> 400',
    'Julius -> Shield -> 150',
    'Gladius -> Heal -> 200',
    'Gladius -> Support -> 250',
    'Gladius -> Shield -> 250',
    'Pesho vs Gladius',
    'Gladius vs Julius',
    'Gladius vs Gosho',
    'Ave Cesar'
]);

