function systemComponents(input) {

    let theMap = new Map();
    input
        .map(r => {
            let [s, c, sc] = r.split(/\s+\|\s+/);
            !theMap.has(s) ? theMap.set(s, new Map([[c, [sc]]])) :
                !theMap.get(s).has(c) ?
                    theMap.get(s).set(c, [sc]) :
                    theMap.get(s).get(c).push(sc)
        });

    // without this condition only the zero test can't pass
    function sortMapKeys(k1, k2) {
        let len = (theMap.get(k2).size - theMap.get(k1).size);
        return !(len) ? (k1.toLowerCase()).localeCompare(k2.toLowerCase()) : len;
    }


    function sortMapValues(v1, v2) {
        if (Array.isArray(v1)) {
            return v2.length - v1.length;
        }
        return v2.size - v1.size;
    }

    //console.log(theMap);
    let result = [];
    [...theMap.keys()]
        .sort(sortMapKeys)
        .sort((a, b) => sortMapValues(theMap.get(a), theMap.get(b)))
        .forEach((system) => {
            result.push(`${system}`);
            [...theMap.get(system).keys()]
                .sort((a, b) => sortMapValues(theMap.get(system).get(a), theMap.get(system).get(b)))
                .forEach(component => {
                    result.push(`|||${component}`);
                    theMap.get(system).get(component).forEach(sub =>
                        result.push(`||||||${sub}`))
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