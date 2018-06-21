function calendar(dateArr) {

    let [day, month, year] = dateArr;
    month--;
    let cDate = new Date(year, month, 1);
    let monthStartDay = cDate.getDay();
    let months = {
        month_names: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
        month_names_short: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
    };

    let weekDays = {
        weekdays: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'],
        weekdays_short: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    };

    // useful im we receive more than one month
    let monthName = months.month_names[month] + " " + year;
    let monthId = year + months.month_names_short[month]+ $('table').length;

    // create skeleton
    $('#content')
        .append($('<table>').attr('id', monthId)
            .append($(`<caption>`).text(monthName))
            .append($(`<tbody>`)
                .append($('<tr>')
                    .append(weekDays.weekdays_short.map(wd => `<th>${wd}</th>`)))
                .append($('<tr>'))));

    let getLastRow = () => $(`#content table#${monthId} tr:last`);

    // fill dates in month
    while (true) {
        getLastRow().append($(`<td>`).text(cDate.getDate()));
        cDate.setDate(cDate.getDate() + 1);
        if (cDate.getMonth() !== month) break;
        if (cDate.getDay() === 1) {
            ($(`<tr>`)).insertAfter(getLastRow());
        }
    }

    // mark the current day
    $(`table#${monthId} td:eq(${day - 1})`).addClass('today');

    if (monthStartDay !== 1) {
        let firstWeek = $(`table#${monthId} tr`).eq(1);
        firstWeek.prepend("<td></td>".repeat(7 - (firstWeek.children('td').length)));
    }

    if (cDate.getDay() - 1 !== 0) {
        let lastWeek = $(`table#${monthId} tr`).last();
        lastWeek.append("<td></td>".repeat(7 - lastWeek.children('td').length));
    }
}
