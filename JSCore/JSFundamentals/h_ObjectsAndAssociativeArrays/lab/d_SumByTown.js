function sumByTowns(input) {

    let result = {};

    input
        .forEach((v, i) => {
            !(i % 2) ? !result[v] ?
                result[v] = +(input[i + 1]) :
                result[v] += +(input[i + 1]) : null
        });

    console.log(JSON.stringify(result));

}

sumByTowns(['Sofia', '20', 'Varna', '3', 'Sofia', '5', 'Varna', '4']);
sumByTowns(['Sofia', '20', 'Varna', '3', 'sofia', '5', 'varna', '4']);