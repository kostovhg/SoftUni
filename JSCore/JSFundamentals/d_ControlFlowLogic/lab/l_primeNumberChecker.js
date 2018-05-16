function primeNumberChecker(num) {
    let isPrime = true;
    if (num <= 1) {
        isPrime = false;
    } else if (num <= 3) {
        isPrime = true;
    } else if (!(num % 2 * num % 3)){ // num % 2 === 0 || num % 3 === 0
        isPrime = false;
    } for (let i = 5; i ** 2 < num;) {
        if(!(num % i * num % (i + 2))){ // (num % i === 0 || num % (i + 2) === 0
            isPrime = false;
            break;
        }
        i += 6;
    }
    console.log(isPrime);
}

primeNumberChecker(0);
primeNumberChecker(1);
primeNumberChecker(2);
primeNumberChecker(3);
primeNumberChecker(4);
primeNumberChecker(5);
primeNumberChecker(7);
primeNumberChecker(8);
primeNumberChecker(81);