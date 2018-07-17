function increment(selector){
    let container = $(selector);
    let fragment = document.createDocumentFragment();
    let textArea = $('<textarea>');
    let incrementBtn = $('<button>Increment</button>');
    let addBtn = $('<button>Add</button>');
    let list = $('<ul>');

    // Textarea frmation
    textArea.val(0);
    textArea.addClass('counter');
    textArea.attr('disabled', true);

    // Buttons formation
    incrementBtn.addClass('btn');
    incrementBtn.attr('id', 'incrementBtn');
    addBtn.addClass('btn');
    addBtn.attr('id', 'addBtn');


    // List formation
    list.addClass('results');

    // Events
    $(incrementBtn).on('click', function() {
        textArea.val(+textArea.val() + 1);
    });

    $(addBtn).on('click', function () {
        let li = $(`<li>${textArea.val()}</li>`);
        li.appendTo(list);
    });

    textArea.appendTo(fragment);
    incrementBtn.appendTo(fragment);
    addBtn.appendTo(fragment);
    list.appendTo(fragment);

    container.append(fragment);
}