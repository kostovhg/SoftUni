function attachEvents() {

    $('#items > li').on('click', function () {
        if ($(this).attr('data-selected')) {
            $(this).removeAttr('data-selected').css('background', '')

        } else {
            $(this).attr('data-selected', 'true').css('background', '#DDD');
        }
    })

    $('#showTownsButton').on('click', function () {
        $('#selectedTowns').text('').text('Selected towns: ' +
            $('#items > li[data-selected=true]').toArray().map(li => $(li).text()).join(", "))
    })
}