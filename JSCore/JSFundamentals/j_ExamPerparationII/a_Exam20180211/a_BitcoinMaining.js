function bitcoinMining(input) {

    const [b_lv, g_lv] = [11949.16, 67.51];
    let [coins, money, day, days, result] = [0, 0, 0, input.map(Number), []];
    for (let i = 1; i <= days.length; i++) {
        if (i % 3 === 0) {
            money += days[i - 1] * 0.7 * g_lv;
        } else {
            money += days[i - 1] * g_lv;
        }

        let c = Math.floor(money / b_lv);
        if (c > 0) {
            money -= c * b_lv;
            if (coins === 0) day = i;
            coins += c;
        }
    }

    result.push(`Bought bitcoins: ${coins.toFixed(0)}`);
    if (day !== 0) result.push(`Day of the first purchased bitcoin: ${day}`);
    result.push(`Left money: ${money.toFixed(2)} lv.`);

    console.log(result.join(`\n`))
}

/*
Bought bitcoins: 2
Day of the first purchased bitcoin: 2
Left money: 10531.78 lv.
 */

bitcoinMining(['100', '200', '300']);
bitcoinMining(['50', '100']);
bitcoinMining(['3124.15', '504.212', '2511.124']);