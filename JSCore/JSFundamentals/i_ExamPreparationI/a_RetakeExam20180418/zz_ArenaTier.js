function arenaTier(input) {

    let [result, total] = [{}, '__total__'];

    for (let command of input) {
        if (command.includes(' -> ')) {
            let [name, technique, skill] = command.split(' -> ');
            skill = Number(skill);
            if (!result[name]) {
                result[name] = {};
                result[name][technique] = skill;
                result[name][total] = skill;
            } else if (!result[name][technique] || result[name][technique] < skill) {
                result[name][total] -= ~~(result[name][technique]);
                result[name][technique] = skill;
                result[name][total] += skill;
            }
        } else if (command.includes(' vs ')) {
            let [g1, g2] = command.split(' vs ');
            if (result[g1] && result[g2] &&
                Object.getOwnPropertyNames(result[g1]).filter(value => -1 !== Object.getOwnPropertyNames(result[g2]).indexOf(value)).length > 1) {
                let dif = result[g1][total] - result[g2][total];
                if(dif > 0) delete result[g2];
                else if(dif < 0) delete result[g1];
            }
        } else {
            break;
        }
    }

    let sortedGladiators = Object.keys(result).sort().sort((g1, g2) => result[g2]['__total__'] - result[g1]['__total__']);
    for(let gName of sortedGladiators){
        console.log(`${gName}: ${result[gName][total]} skill`);
        let sortedSkills = Object.getOwnPropertyNames(result[gName])
            .sort()
            .sort((s1, s2) => result[gName][s2] - result[gName][s1])
            .filter(x => x !== total);
        for (let skill of sortedSkills) {
            console.log(`- ${skill} <!> ${result[gName][skill]}`)
        }
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

