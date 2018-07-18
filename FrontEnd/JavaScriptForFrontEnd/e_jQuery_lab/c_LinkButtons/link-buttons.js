// function attachEvents() {
//     $('.button').on('click', function(event) {
//         $('.button').each(b => $(this) === event.target ? $(b).addClass('selected') : $(b).removeClass('selected'));
//    console.log('Clicked' + $(this).text())
//     });
// }

function attachEvents() {
    let [buttons, change] = [
        $('a.button'),
        (event) => {
            buttons.each((b, t) =>
                ($(event.target).text() === $(t).text()) ?
                    $(t).addClass('selected') :
                    $(t).removeClass('selected'))
        }
    ];

    buttons.each((k, v) =>
            $(v).on('click', change));
}
