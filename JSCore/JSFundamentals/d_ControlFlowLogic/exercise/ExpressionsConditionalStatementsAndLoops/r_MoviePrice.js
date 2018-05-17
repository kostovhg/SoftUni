/*
Movie Title     Monday      Tuesday     Wednesday       Thursday        Friday      Saturday        Sunday

The Godfather       12      10          15              12.50           15          25          30
Schindler's List    8.50    8.50        8.50            8.50            8.50        15          15
Casablanca          8       8           8               8               8           10          10
The Wizard of Oz    10      10          10              10              10          15          15
 */
function moviePrice(input) {

    let weekdays = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday'];
    let moviesTitles = ['the godfather', 'schindler\'s list', 'casablanca', 'the wizard of oz'];
    let prices = [ 
        [12, 10, 15, 12.50, 15, 25, 30],
        [8.50, 8.50, 8.50, 8.50, 8.50, 15, 15], 
        [8, 8, 8, 8, 8, 10, 10], 
        [0, 10, 10, 10, 10, 15, 15]
    ];

    let titleIndex = moviesTitles.indexOf(input[0].toLowerCase());
    let weekdayIndex = weekdays.indexOf(input[1].toLowerCase());

    if(titleIndex < 0 || weekdayIndex < 0) {
        return 'error';
    }

    return prices[titleIndex][weekdayIndex];

}

console.log(moviePrice(['The Godfather', 'Friday']));
console.log(moviePrice(['casablanca', 'sunday']));
console.log(moviePrice(['Schindler\'s LIST', 'monday']));
console.log(moviePrice(['SoftUni', 'Nineday']));