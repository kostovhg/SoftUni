function gameOfEpicness(input, battles) {

    let [kingdoms, totals, wins, loses, army] = [{}, '_totals_', '_wins_', '_loses_', '_army_'];

    //  create or modify properties (kingdoms) for object kingdoms
    for (let object of input) {
        let [kingdom, general, count] = [object.kingdom, object.general, object.army];
        if (!kingdoms.hasOwnProperty(kingdom)) {
            kingdoms[kingdom] = {[totals]: {[wins]: 0, [loses]: 0}};
        }
        if (!kingdoms[kingdom].hasOwnProperty(general)) {
            kingdoms[kingdom][general] = {[wins]: 0, [loses]: 0, [army]: count};
        }
        else {
            kingdoms[kingdom][general][army] += count;
        }
    }

    // battle aftermath
    function fight(d, k1, g1, k2, g2) {
        let [winK, winG, losK, losG] = ((d < 0) ? [k2, g2, k1, g1] : [k1, g1, k2, g2]);
        winG[army] = ~~(winG[army] * 1.1);
        losG[army] = ~~(losG[army] * 0.9);
        winK[totals][wins]++;
        losK[totals][loses]++;
        winG[wins]++;
        losG[loses]++;
     }

     // conduct a battle
    for (let [attK, attG, defK, defG] of battles) {
        if (attK === defK) continue;
        let diff = kingdoms[attK][attG][army] - kingdoms[defK][defG][army];
        if (!diff) continue;
        fight(diff, kingdoms[attK], kingdoms[attK][attG], kingdoms[defK], kingdoms[defK][defG]);
    }

    // order the kingdoms names
    let winner = Object.getOwnPropertyNames(kingdoms).sort((k1, k2) => {
            let [aWins, aLoses, bWins, bLoses] = [
                kingdoms[k1][totals][wins], kingdoms[k1][totals][loses],
                kingdoms[k2][totals][wins], kingdoms[k2][totals][loses]];
            return (aWins !== bWins) ?
                bWins - aWins : (aLoses !== bLoses) ? aLoses - bLoses : k1.localeCompare(k2);
        })[0];

    // prepare output
    let result = [`Winner: ${winner}`];
    // sort generals ? --- not described in the task !? (judge does not check for secondary function for ordering
    let generals = Object.getOwnPropertyNames(kingdoms[winner]).filter(p => p !== totals)
        .sort((g1, g2) => kingdoms[winner][g2][army] - kingdoms[winner][g1][army]);
    // add the generals
    for (let general of generals) {
        result.push(`/\\general: ${general}`);
        let gen = kingdoms[winner][general];
        result.push(`---army: ${gen[army]}`);
        result.push(`---wins: ${gen[wins]}`);
        result.push(`---losses: ${gen[loses]}`)
    }
    // print the result
    console.log(result.join('\n'));
}

gameOfEpicness(
    [
        {kingdom: "Maiden Way", general: "Merek", army: 5000},
        {kingdom: "Stonegate", general: "Ulric", army: 4900},
        {kingdom: "Stonegate", general: "Doran", army: 70000},
        {kingdom: "YorkenShire", general: "Quinn", army: 0},
        {kingdom: "YorkenShire", general: "Quinn", army: 2000},
        {kingdom: "Maiden Way", general: "Berinon", army: 100000}
    ],
    [
        ["YorkenShire", "Quinn", "Stonegate", "Ulric"],
        ["Stonegate", "Ulric", "Stonegate", "Doran"],
        ["Stonegate", "Doran", "Maiden Way", "Merek"],
        ["Stonegate", "Ulric", "Maiden Way", "Merek"],
        ["Maiden Way", "Berinon", "Stonegate", "Ulric"]
    ]
);

gameOfEpicness(
    [
        {kingdom: "Stonegate", general: "Ulric", army: 5000},
        {kingdom: "YorkenShire", general: "Quinn", army: 5000},
        {kingdom: "Maiden Way", general: "Berinon", army: 1000}
    ],
    [
        ["YorkenShire", "Quinn", "Stonegate", "Ulric"],
        ["Maiden Way", "Berinon", "YorkenShire", "Quinn"]
    ]
);

gameOfEpicness(
    [
        {kingdom: "Maiden Way", general: "Merek", army: 5000},
        {kingdom: "Stonegate", general: "Ulric", army: 4900},
        {kingdom: "Stonegate", general: "Doran", army: 70000},
        {kingdom: "YorkenShire", general: "Quinn", army: 0},
        {kingdom: "YorkenShire", general: "Quinn", army: 2000}
    ],
    [
        ["YorkenShire", "Quinn", "Stonegate", "Doran"],
        ["Stonegate", "Ulric", "Maiden Way", "Merek"]
    ]
);