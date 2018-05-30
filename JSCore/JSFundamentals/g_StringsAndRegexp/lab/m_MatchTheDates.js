function matchTheDates(input) {
    let pattern = /\b(?<day>[0-9]{1,2})-(?<month>[A-Z][a-z]{2})-(?<year>\d{4})\b/g;
    //let dates = [], match;
    let stringDates = [], match;
    //let months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];

    for (let sentence of input) {
        while(match = pattern.exec(sentence)) {
            try {/*
                dates.push(new Date(
                    Number(match.groups.year),
                    months.indexOf(match.groups.month),
                    Number(match.groups.day)));*/
                stringDates.push(
                    `${match.groups.day}-${match.groups.month}-${match.groups.year} (Day: ${match.groups.day}, Month: ${match.groups.month}, Year: ${match.groups.year})`
                )
            } catch (err){

            }
        }
    }

    /*
    function validateDate(d){
        return Object.prototype.toString.call(d) === "[object Date]";
    }
    console.log(dates
        .filter(validateDate)
        .map(d =>
            `${d.getDate()}-${months[d.getMonth()]}-${d.getFullYear()} (Day: ${d.getDate()}, Month: ${months[d.getMonth()]}, Year: ${d.getFullYear()})`)
        .join('\n'));*/
    console.log(stringDates.join('\n'));

}

matchTheDates(['1-Jan-1999 is a valid date.',
'So is 01-July-2000.',
'I am an awful liar, by the way – Ivo, 28-Sep-2016.']);

matchTheDates(['I am born on 30-Dec-1994.',
'This is not date: 512-Jan-1996.',
'My father is born on the 29-Jul-1955.']);