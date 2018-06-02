function lowestPricesInCities(arr) {
    let products = new Map();

    arr.map(r => {
        let [town, product, price] = r.split(/ \| /);
        !products.get(product) ?
            products.set(product, new Map()) : null;
            products.get(product).set(town, Number(price));
    });

    function print(theMap) {
        theMap.forEach((subMap, key) => {
            let top = [...subMap].sort((a, b) => a[1] - b[1])[0];
            console.log(`${key} -> ${top[1]} (${top[0]})`)
        })
    }

    //console.log(prMap);
    print(products);
}


lowestPricesInCities([
    'Sample Town | Sample Product | 1000',
    'Sample Town | Orange | 2',
    'Sample Town | Peach | 1',
    'Sofia | Orange | 3',
    'Sofia | Peach | 2',
    'New York | Sample Product | 1000.1',
    'New York | Burger | 10'
]);


lowestPricesInCities([
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