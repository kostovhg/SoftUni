function systemComponents(input) {

    let [theMap, result] = [new Map(), []];

    input.map(r => {
        let [s, c, sc] = r.split(/\s+\|\s+/);
        !theMap.has(s) ? theMap.set(s, new Map([[c, [sc]]])) :
            !theMap.get(s).has(c) ? theMap.get(s).set(c, [sc]) : theMap.get(s).get(c).push(sc)
    });

    [...theMap].sort((a, b) => ~~(b[1].size - a[1].size) || a[0].toLowerCase().localeCompare(b[0].toLowerCase()))
        .forEach((system) => {
        result.push(`${system[0]}`);
        [...system[1]].sort((a, b) => b[1].length - a[1].length).forEach(component => {
            result.push(`|||${component[0]}`);
            component[1].forEach(sub => result.push(`||||||${sub}`))
        });
    });
    console.log(result.join('\n'));
}

systemComponents([
    'SULS | Main Site | Home Page',
    'SULS | Main Site | Login Page',
    'SULS | Main Site | Register Page',
    'SULS | Judge Site | Login Page',
    'SULS | Judge Site | Submittion Page',
    'Lambda | CoreA | A23',
    'SULS | Digital Site | Login Page',
    'Lambda | CoreB | B24',
    'Lambda | CoreA | A24',
    'Lambda | CoreA | A25',
    'Lambda | CoreC | C4',
    'Indice | Session | Default Storage',
    'Indice | Session | Default Security'
]);

systemComponents([
    'SULS | Main Site | Home Page',
    'SULS | Main Site | Login Page',
    'SULS | Main Site | Register Page',
    'SULS | Judge Site | Login Page',
    'SULS | Judge Site | Submittion Page',
    'Lambda | CoreA | A23',
    'SULS | Digital Site | Login Page',
    'Lambda | CoreB | B24',
    'Lambda | CoreA | A24',
    'Lambda | CoreA | A25',
    'Lambda | CoreC | C4',
    'Lambda | CoreC | C26',
    'Lambda | CoreC | C27',
    'Lambda | CoreC | C28',
    'Indice | Session | Default Storage',
    'Indice | Session | Default Security'
]);