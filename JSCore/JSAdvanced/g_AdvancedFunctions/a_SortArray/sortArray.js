function sortArray(arr, method){

    let sortType = {
        'asc': (a, b) => a - b,
        'desc': (a, b) => b - a
    };

    return arr.sort(sortType[method]);
}

sortArray([14, 7, 17, 6, 8], 'asc');
sortArray([14, 7, 17, 6, 8], 'desc');
