function gladiatorExpenses() {

    let [fights, eq]  = [arguments[0], [...arguments].slice(1)];
    console.log(`Gladiator expenses: ${[2, 3, 6, 12]
        .map((b, i) => (Math.floor(fights / b)) * eq[i]).reduce((a, b) => a+b).toFixed(2)} aureus`);
}

gladiatorExpenses(7, 2, 3, 4, 5);
gladiatorExpenses(23, 12.50, 21.50, 40, 200);