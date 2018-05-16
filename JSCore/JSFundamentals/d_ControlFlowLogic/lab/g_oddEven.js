function oddEven(num) {
    let rem = num % 2;
    if(rem === 0)
        console.log('even');
    else {
        console.log(Math.floor(rem) === rem ? 'odd' : 'invalid');
    }
}

oddEven(5);
oddEven(8);
oddEven(1.5);