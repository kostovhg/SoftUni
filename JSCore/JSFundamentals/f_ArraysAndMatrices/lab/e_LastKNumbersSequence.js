function lastKNumbersSequence(n, k) {

    let seq = [1];
    for (let i = 1; i < n; i++) {
        seq[i] = seq.slice(Math.max(0, i - k), i).reduce((a, b) => a + b);
    }
    console.log(seq.join(' '));
}

lastKNumbersSequence(6, 3);
lastKNumbersSequence(8, 2);
