function search() {
    let searchText = $('#searchText').val();
    let matchedElements = $(`#towns li:contains('${searchText}')`);

    $('#towns li').css('font-weight', '');
    matchedElements.css('font-weight', 'bold');
    $('#result').text(matchedElements.length + ' matches found.');
}