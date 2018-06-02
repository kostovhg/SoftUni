function townsToJSON(input) {

    let extractFromTable = (table, sep) => {
        // method for splitting rows
        let splitRow = (x) => x.split(sep).filter(e => e !== '');
        // method for parsing values between strings and numbers with format for the numbers
        let parseVal = (x) => !isNaN(parseFloat(x)) && isFinite(x) ? (+x) : x;
        // Get headers values and clear the first row
        let headers = splitRow(table.shift());
        // Create empty array for objects
        let objects = [];
        // loop trow all rolls
        table.forEach((row) => {
            let obj = {};
            let rowData = splitRow(row);
            headers.forEach((header, headerIndex) =>
                obj[header] = parseVal(rowData[headerIndex]));
            objects.push(obj);
        });
        return objects;
    };

    console.log(JSON.stringify(extractFromTable(input, /(?:\s*\|\s*)/)));
}

townsToJSON([
    '| Town | Latitude | Longitude |',
    '| Sofia | 42.696552 | 23.32601 |',
    '| Beijing | 39.913818 | 116.363625 |'
]);
townsToJSON([
    '| Town | Latitude | Longitude |',
    '| Veliko Turnovo | 43.0757 | 25.6172 |',
    '| Monatevideo | 34.50 | 56.11 |'
]);
