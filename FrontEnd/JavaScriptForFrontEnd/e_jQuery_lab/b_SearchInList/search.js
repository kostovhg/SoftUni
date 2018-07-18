function search() {
    let towns = $('#towns li');
    let searchField = $('#searchText');
    if (searchField.val() !== '') {
        let count = 0;
        $(towns).each((x, v) => {
                if ($(v).text().indexOf(searchField.val()) > -1) {
                    $(v).css('font-weight', 'bold');
                    count++;
                };
            });
        $('#result').text(`${count} matches found`);
    } else {
        $('#result').text(``);
    }
    //console.log(towns);
}