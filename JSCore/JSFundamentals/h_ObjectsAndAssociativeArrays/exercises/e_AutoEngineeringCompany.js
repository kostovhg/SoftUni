function autoEngineeringCompany(input) {

    let theMap = new Map();
    input
        .forEach(r => {
            let [b, m, c] = r.split(/\s+\|\s+/);
            if(!theMap.has(b)){
                theMap.set(b, new Map());
            }
            if(!theMap.get(b).has(m)) {
                theMap.get(b).set(m, 0);
            }
            theMap.get(b).set(m, +c + theMap.get(b).get(m));
        });

    let result = [];
    theMap.forEach((val, key) => {
            result.push(key);
            val.forEach((v, k) => result.push(`###${k} -> ${v}`))
        }
    );

    console.log(result.join(`\n`));

}

/*
“{carBrand}
  ###{carModel} -> {producedCars}
  ###{carModel2} -> {producedCars}
  ...”
 */

autoEngineeringCompany([
    'Audi | Q7 | 1000',
    'Audi | Q6 | 100',
    'BMW | X5 | 1000',
    'BMW | X6 | 100',
    'Citroen | C4 | 123',
    'Volga | GAZ-24 | 1000000',
    'Lada | Niva | 1000000',
    'Lada | Jigula | 1000000',
    'Citroen | C4 | 22',
    'Citroen | C5 | 10'
]);