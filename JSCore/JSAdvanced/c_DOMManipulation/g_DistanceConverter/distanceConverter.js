function attachEventsListeners() {

    let units = {
        'km': 1000,
        'm': 1,
        'cm': 0.01,
        'mm': 0.001,
        'mi': 1609.34,
        'yrd': 0.9144,
        'ft': 0.3048,
        'in': 0.0254
    };

    let button = document.getElementById('convert');
    button.addEventListener('click', convert);

    function convert() {

        let input = document.getElementById('inputDistance').value;
        let from = document.getElementById('inputUnits').value;
        let to = document.getElementById('outputUnits').value;

        document.getElementById('outputDistance').value = (+input * +units[from]) / +units[to];
    }

}
/*
1 km = 1000 m
1 m = 1 m
1 cm = 0.01 m
1 mm = 0.001 m
1 mi = 1609.34 m
1 yrd = 0.9144 m
1 ft = 0.3048 m
1 in = 0.0254 m
 */