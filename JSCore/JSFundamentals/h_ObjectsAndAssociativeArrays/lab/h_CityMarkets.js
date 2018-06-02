function cityMarkets(input) {

    let map = new Map();

    input
        .forEach(r => {
            let [town, product, quantity, price] = r.split(/ -> | : /);
            !map.has(town) ?
                map.set(town, new Map()) :
                null;
            !map.get(town).has(product) ?
                map.get(town).set(product, Number(quantity) * Number(price)) :
                map.get(town).set(product, Number(quantity) * Number(price) + map.get(town).get(product))
        });

    map.forEach((m, t) => {
        console.log(`Town - ${t}`);
        m.forEach((c, p) => console.log(`$$$${p} : ${c}`))
    })
}

cityMarkets([
    'Sofia -> Laptops HP -> 200 : 2000',
    'Sofia -> Raspberry -> 200000 : 1500',
    'Sofia -> Audi Q7 -> 200 : 100000',
    'Montana -> Portokals -> 200000 : 1',
    'Montana -> Qgodas -> 20000 : 0.2',
    'Montana -> Chereshas -> 1000 : 0.3'
]);