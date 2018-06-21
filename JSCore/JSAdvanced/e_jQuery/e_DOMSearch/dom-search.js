function domSearch(selector, caseSensitive) {

    // first create functions for page initialization
    let [createInput, createList, remove, fade, hide, show] = [
        (labelText) => $('<label>').text(labelText).append($('<input>')),
        () => $(`<ul>`, {'class': 'items-list'}),
        (e) => $(e.currentTarget).parent().remove(),
        (e) => $(e.currentTarget).parent().fadeOut(300, () => $(e.currentTarget).parent().remove()),
        (that) => that.css('display', 'none'),
        (that) => that.css('display', '')
    ];

    // fill the body with divs
    $(selector).addClass('items-control')
        .append($(`<div>`, {"class": "add-controls"})
            .append(createInput('Enter text: '))
            .append($('<a>', {'class': 'button', 'style': 'display: inline-block;'})
                .text('Add')))
        .append($(`<div>`, {"class": "search-controls"})
            .append(createInput('Search:')))
        .append($(`<div>`, {'class': "result-controls"})
            .append(createList()));

    // get created div elements for later use
    let [add, input, search] = [
        $(`${selector} .add-controls .button`),
        $(`${selector} .add-controls label input`),
        $(`${selector} .search-controls > label > input`),
    ];

    // function for creating element
    let createItem = (element) => {
        $('ul.items-list')
            .append($('<li>', {'class': 'list-item'})
                .append($('<a>', {'class': 'button'})
                    .text('X')
                    .on('click', remove))
                .append($('<strong>').append(element)));
    };

    // add events to elements
    add.on('click', (e) => {
        e.preventDefault();
        // let val = getInput();
        createItem(input.val());
        input.val('');
    });

    // use enter instead of add button
    input.on('keypress', function (e) {
        if (e.which === 13) {
            //Disable textbox to prevent multiple submit
            $(this).attr("disabled", "disabled");
            add.trigger('click');
            //Enable the textbox again if needed.
            $(this).removeAttr("disabled");
            $()
        }
    });

    //for debugging
    // for (let el of ['Element 1', 'Element 2', 'Another element', 'Item 1', 'Item 2', 'List Item']) {
    //     createItem(el);
    // }

    search.on('keyup input change', function (e) {
        let searched = $(e.currentTarget).val();
        $(`li.list-item`).each((i, obj) => {
            let that = $(obj);
            let thatContent = that.find('strong').text();
            if (!caseSensitive) { thatContent = thatContent.toLowerCase();searched = searched.toLowerCase() }
            if (searched === '') {
                show(that);
            } else {
                (thatContent.indexOf(searched) > -1) ? show(that) : hide(that)
            }
        })
    });
}
