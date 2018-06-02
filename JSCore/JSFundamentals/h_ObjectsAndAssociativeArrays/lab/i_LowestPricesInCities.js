function lowestPriceInCities(input) {

    let productMap = new Map();

    input
        .map(r => {
            let [town, name, price] = r.split(/\s+\|\s+/);
            if(!productMap.has(name)){
                productMap.set(name, new Map())
            }
            productMap.get(name).set(town, +price);
        });

    productMap.forEach((subMap, productKey) => {
        let sortedSubMap = Array.from(subMap.keys()).sort((k1, k2) => subMap.get(k1) - subMap.get(k2));
        console.log(`${productKey} -> ${subMap.get(sortedSubMap[0])} (${sortedSubMap[0]})`)
    });
}

lowestPriceInCities([
    'Sample Town | Sample Product | 1000',
    'Sample Town | Orange | 2',
    'Sample Town | Peach | 1',
    'Sofia | Orange | 3',
    'Sofia | Peach | 2',
    'New York | Sample Product | 1000.1',
    'New York | Burger | 10'
]);


lowestPriceInCities([
    'Sofia City | Audi | 100000',
    'Sofia City | BMW | 100000',
    'Sofia City | Mitsubishi | 10000',
    'Sofia City | Mercedes | 10000',
    'Sofia City | NoOffenseToCarLovers | 0',
    'Mexico City | Audi | 1000',
    'Mexico City | BMW | 99999',
    'New York City | Mitsubishi | 10000',
    'New York City | Mitsubishi | 1000',
    'Mexico City | Audi | 100000',
    'Washington City | Mercedes | 1000'
]);