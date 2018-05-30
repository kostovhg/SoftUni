function restaurantBill(input) {

    let products = [];
    let sum = 0;

    input.forEach((x, i) =>
        i % 2 ?
            sum += Number(x) :
            products.push(x));


    console.log(`You purchased ${products.join(', ')} for a total sum of ${sum}`)

}


restaurantBill(['Beer Zagorka', '2.65', 'Tripe soup', '7.80', 'Lasagna', '5.69']);
restaurantBill(['Cola', '1.35', 'Pancakes', '2.88']);