function fruitOrVegetable(input) {

    let fruits = ['banana', 'apple', 'kiwi', 'cherry', 'lemon', 'grapes', 'peach'];
    let vegetables = ['tomato', 'cucumber', 'pepper', 'onion', 'garlic', 'parsley'];
    let result;
    if (fruits.indexOf(input) !== -1) {
        result = 'fruit';
    }
    else if (vegetables.indexOf(input) !== -1) {
        result = 'vegetable';
    }
    else {
        result = 'unknown';
    }
    console.log(result);
}

fruitOrVegetable('banana');
fruitOrVegetable('cucumber');
fruitOrVegetable('pizza');