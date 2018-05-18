function dayOfWeek(dayName) {

    let weekNum = [
        'monday',
        'tuesday',
        'wednesday',
        'thursday',
        'friday',
        'saturday',
        'sunday'
    ].indexOf(dayName.toLowerCase());

    console.log((weekNum > -1) ? weekNum + 1 : 'error');
}


dayOfWeek('Monday');

dayOfWeek('Friday');

dayOfWeek('Frabjoyousday');
