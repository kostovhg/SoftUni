function arenaTier(input) {

    let tier = new Map();

    input
        .map(i => i.split(/ -> | vs /))
        .forEach(i => {
            if (i.length > 2) {
                let [n, s, p] = i;
                if (!tier.has(n)) {
                    tier.set(n, new Map([[s, +p]]))
                } else if(~~tier.get(n).get(s) < +p) {
                    tier.get(n).set(s, +p)
                }
            } else if(i.length > 1){
                let [g1, g2] = i;
                if (~~tier.has(g1) & ~~tier.has(g2)) {
                    //if(hasCommonSkills(g1, g2)){
                    if([...tier.get(g1).keys()].filter(value => -1 !== [...tier.get(g2).keys()].indexOf(value)).length > 0){
                        let dif = total(g1) - total(g2);
                        if (dif > 0) tier.delete(g2);
                        if (dif < 0) tier.delete(g1);
                    } else {
                        //console.log(`${g1} and ${g2} haven't common skill`)
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

    function compareGladiators(g1, g2) {
        return ~~(total(g2) - total(g1)) || g1.localeCompare(g2);
    }

    function compareSkills(s1, s2) {
        return ~~(s2[1] - s1[1]) || s1[0].localeCompare(s2[0]);
    }

    function printTier(){
        [...tier.keys()].sort(compareGladiators)
            .forEach(g => {
                    console.log(`${g}: ${total(g)} skill`);
                    [...tier.get(g)].sort(compareSkills)
                        .forEach((k) =>
                            console.log(`- ${k[0]} <!> ${k[1]}`)
                        )
                }
            )
    }

    //console.log(tier);
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

