function leapYear(year) {
    let leap = (year % 400 === 0 ) || (year % 4 === 0 && year % 100 !== 0);
    console.log(leap ? "yes" : "no");
}

leapYear(1999);
leapYear(2000);
leapYear(1900);