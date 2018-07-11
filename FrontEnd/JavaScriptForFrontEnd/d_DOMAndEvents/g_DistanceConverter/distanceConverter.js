function attachEventsListeners() {

    let units = {
        'km': 1000, 'm': 1, 'cm': 0.01, 'mm': 0.001,
        'mi': 1609.34, 'yrd': 0.9144, 'ft': 0.3048, 'in': 0.0254,
    };

    function attachEvent() {
        let [fromUnits, toUnits] = [
            document.getElementById('inputUnits'),
            document.getElementById('outputUnits'),
        ];
        document.getElementById('convert').addEventListener('click', function () {
            document.getElementById('outputDistance').value =
                document.getElementById('inputDistance').value * units[fromUnits.value] / units[toUnits.value];
        })
    }

    return attachEvent();
}