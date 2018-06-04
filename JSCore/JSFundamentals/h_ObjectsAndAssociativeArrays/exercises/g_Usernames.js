function usernames(input) {

    input
        .sort((n1, n2) => ~~(n1.length - n2.length) || n1.localeCompare(n2))
        .filter((a, i, arr) => a !== arr[i - 1])
        .forEach(x => console.log(x));
}

usernames([
    'Ashton',
    'Kutcher',
    'Ariel',
    'Lilly',
    'Keyden',
    'Aizen',
    'Billy',
    'Braston'
]);


usernames([
    'Denise',
    'Ignatius',
    'Iris',
    'Isacc',
    'Indie',
    'Dean',
    'Donatello',
    'Enfuego',
    'Benjamin',
    'Biser',
    'Bounty',
    'Renard',
    'Rot'
]);
