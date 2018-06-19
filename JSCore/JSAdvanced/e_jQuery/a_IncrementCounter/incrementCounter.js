function increment(selector) {

    let sel = $(selector);

    let [body, text, incr, add, result] = [
        $('body'), $('<textarea>'), $('<button>'), $('<button>'), $('<ul>')
    ];

    sel.appendTo(body);

    text.addClass('counter').val(0).appendTo(sel);
    incr.addClass('btn').attr('id', 'incrementBtn').text('Increment').appendTo(sel);
    add.addClass('btn').attr('id', 'addBtn').text('Add').appendTo(sel);
    result.addClass('results').appendTo(sel);

    add.on('click', () => {
        result.append(`<li>${text.val()}</li>`)
    });

    incr.on('click', () => {
        let v = text.val();
       text.val(++v)
    });

}