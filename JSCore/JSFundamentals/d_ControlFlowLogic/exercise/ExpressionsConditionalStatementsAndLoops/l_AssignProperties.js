function assignProperties(inputArr) {

    return o =
        {
            [inputArr[0]] : inputArr[1],
            [inputArr[2]] : inputArr[3],
            [inputArr[4]] : inputArr[5],
        };
}

assignProperties(['name', 'Pesho', 'age', '23', 'gender', 'male']);
assignProperties(['ssid', '90127461', 'status', 'admin', 'expires', '600']);

console.log(assignProperties(['name', 'Pesho', 'age', '23', 'gender', 'male']));
console.log(assignProperties(['ssid', '90127461', 'status', 'admin', 'expires', '600']));