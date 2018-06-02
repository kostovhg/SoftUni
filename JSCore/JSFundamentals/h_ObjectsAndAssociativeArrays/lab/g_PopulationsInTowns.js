function populationsInTowns(input) {

    let result = new Map();
    input
        .map(e => e.split(/\s*<->\s*/))
        .map(kvp => [kvp[0], Number(kvp[1])])
        .forEach(kvp => {
            !result.has(kvp[0]) ?
                result.set(kvp[0], kvp[1]) :
                result.set(kvp[0], kvp[1] + result.get(kvp[0]))
        });

    result.forEach((v, k) => console.log(`${k} : ${v}`))
}

populationsInTowns([
    'Sofia <-> 1200000',
    'Montana <-> 20000',
    'New York <-> 10000000',
    'Washington <-> 2345000',
    'Las Vegas <-> 1000000'
]);

populationsInTowns([
    'Istanbul <-> 100000',
    'Honk Kong <-> 2100004',
    'Jerusalem <-> 2352344',
    'Mexico City <-> 23401925',
    'Istanbul <-> 1000'
]);

populationsInTowns([
    'Silent Hill <-> 0',
    'Tin City <-> 0',
    'King Island <-> 0',
    'Kern <-> 0',
    'Three Saints Bay <-> 1'
]);