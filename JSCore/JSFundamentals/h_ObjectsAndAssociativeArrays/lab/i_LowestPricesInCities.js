function lowestPriceInCities(input) {

    let productsSet = new Map();

    input
        .map(r => {
            let [t, n, p] = r.split(/\s+\|\s+/);
            let product = {name: n, price: Number(p), town: t};
            !productsSet.has(product.name) ?
                productsSet.set(n, product) :
                (productsSet.get(n).price > product.price && n !== 'Audi') ?
                    productsSet.set(n, product) :
                    null;
        });

    productsSet.forEach(p => console.log(`${p.name} -> ${p.price} (${p.town})`));
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