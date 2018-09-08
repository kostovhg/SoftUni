function startApp() {

    /* Constants  */
    const KINVEY_BASE_URL = `https://baas.kinvey.com/appdata/kid_SJRn6W6QX`;
    const KINVEY_AUTH_HEADER = {'Authorization': "Basic " + btoa("guest:guest")};

    /* All purpose variables */
    let [booksTable, addModal, editModal, deleteModal, allModals] = [
        $('#table'), $('#addBookModal'), $('#editBookModal'), $('#deleteBookModal'), $('.modal')
    ];

    /* Attach events */
    $('#refresh-btn').click(loadAllBooks);
    addModal.submit(function(event) {createEntry(event); $(this).modal('toggle')});
    editModal.submit(function(event) {editEntry(event); $(this).modal('toggle')});
    deleteModal.submit(function(event) {deleteEntry(event); $(this).modal('toggle')});

    editModal.on('show.bs.modal', function (event) {
        let book = HTMLToObj($(event.relatedTarget).parents('.book')); // invoker parent
        let that = $(this); // current modal form

        that.find('[name="title"]').val(book.title);
        that.find('[name="author"]').val(book.author);
        that.find('[name="isbn"]').val(book.author);
        that.find('form').attr('action', `/books/${book._id}`)
    });

    deleteModal.on('show.bs.modal', function (event) {
        let book = HTMLToObj($(event.relatedTarget).parents('.book'));
        let that = $(this);

        that.find('.book-details')
            .html(`Deleting book <span>"${book.title}"</span> ` +
                    `from <b>${book.author}</b> ` +
                    `with id <b>${book._id}</b> will be deleted permanently!`);

        that.find('form').attr('action', `/books/${book._id}`)
    });

    // Reset all modal forms after hide them
    allModals.on('hidden.bs.modal', function(event){
        $(this).find('form')[0].reset();
        loadAllBooks();
    });

    /* All AJAX methods interface */
    function request(method, endpoint, data) {
        return $.ajax({
            method: method,
            url: KINVEY_BASE_URL + endpoint,
            headers: KINVEY_AUTH_HEADER,
            //data: JSON.stringify(data)
            data: data
        })
    }

    /* DOM read and write */
    /* useless load animation */
    // language=JQuery-CSS
    $(document).ajaxStart(function () {
        $('#wait').show()
    }).ajaxStop(function () {
        $('#wait').hide();
    });

    /* Resulting DOM template from JSON */
    function objToHTML(el) {
        // Return DOM element with attached functions to action buttons
        return $(`<tr class="book" data-id="${el._id}">`)
            .append($('<td>').prop('tabindex', 1).attr('name', 'title').attr('header', 'title').text(`${el['title']}`))
            .append($('<td>').prop('tabindex', 1).attr('name', 'author').attr('header', 'author').text(`${el['author']}`))
            .append($('<td>').prop('tabindex', 1).attr('name', 'isbn').attr('header', 'isbn').text(`${el['isbn']}`))
            .append($('<td>').addClass('uneditable')
                .append($(`<a href="#editBookModal" class="edit" data-toggle="modal">`)
                    .append(`<i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>`)
                    /*.on('click', showEditModal)*/)
                .append($(`<a href="#deleteBookModal" class="delete" data-toggle="modal">`)
                    .append(`<i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>`)
                    /*.on('click', showDeleteConformation)*/)
            );
    }

    /* Resulting JSON from DOM content */
    function HTMLToObj(sourceElement) {
        let obj = {};
        for (let field of sourceElement.find('[name]')) {
            let el = $(field);
            obj[el.attr('name')] = el.is('input') ? el.val() : el.text();
        }
        if (sourceElement.attr('data-id')) {
            obj._id = sourceElement.attr('data-id');
        }
        return obj;
    }

    /* Show modal for deleting and temporally add to it book data*/
    function showDeleteConformation() {
        let dataRow = $(this).parents('.book');
        let theBookRecord = HTMLToObj($(dataRow));

        deleteModal.find('.modal-body')
            .prepend($('<p class="book-details">')
                .html(`Deleting book <span>"${theBookRecord.title}"</span> ` +
                    `from <b>${theBookRecord.author}</b> ` +
                    `with id <b>${theBookRecord._id}</b> will be deleted permanently!`));

        deleteModal.find('form').attr('action', `/books/${theBookRecord._id}`)
        deleteModal.modal('show');

        return false;
    }

    /* Show modal for create and temporally add to it book data*/
    function showCreateModal() {
        editModal.find('form').attr('action', `/books`); // always the same
        editModal.modal('show');
        return false;
    }


    /* Call AJAX to update the table */
    function displayAllBooks(data) {
        booksTable.find('tbody').empty();
        let rows = $('<tbody>');
        for (let el of data) {
            rows.append(objToHTML(el))
        }
        booksTable.append(rows)
    }

    /* AJAX requests */

    // AJAX request to load all books
    function loadAllBooks() {
        request('GET', '/books')
            .then(displayAllBooks)
            .catch(handleError);
    }

    // AJAX request to update book
    function editEntry(event) {
        event.preventDefault();
        let thatForm = $(event.currentTarget).find('form');
        let dataObj = HTMLToObj(thatForm);

        // Call ajax with with attributes of the form
        request(thatForm.attr('method'), thatForm.attr('action'), dataObj)
            .then(loadAllBooks)
            .catch(handleError);
    }

    // AJAX request to delete book
    function deleteEntry(event) {
        event.preventDefault();
        let thisForm = $(event.currentTarget).find('form');
        request(thisForm.attr('method'), thisForm.attr('action'))
            .then(loadAllBooks)
            .catch(handleError);
    }

    // AJAX request to create catch
    function createEntry(event) {
        event.preventDefault();
        let thisForm = $(event.currentTarget).find('form');
        let dataObj = HTMLToObj(thisForm);
        request(thisForm.attr('method'), thisForm.attr('action'), dataObj)
            .then(loadAllBooks())
            .catch(handleError)
    }

    function handleError(err) {
        console.log(err.status);
        alert(
            `Error: ${err.statusText}`
        )
    }

}