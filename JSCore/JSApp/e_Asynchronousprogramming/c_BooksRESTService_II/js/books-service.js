function startApp() {

    /* Constants  */
    const KINVEY_BASE_URL = `https://baas.kinvey.com/appdata/kid_SJRn6W6QX`;
    const KINVEY_AUTH_HEADER = {'Authorization': "Basic " + btoa("guest:guest")};

    /* All purpose variables */
    let [booksTable, addModal, editModal, deleteModal, allModals] = [
        $('#books-table'), $('#addBookModal'), $('#editBookModal'), $('#deleteBookModal'), $('.modal')
    ];

    /* Attach events */
    $('#refresh-btn').click(loadAllBooks);
    addModal.submit(createEntry);
    editModal.submit(editEntry);
    deleteModal.submit(deleteEntry);

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
        allModals.find('form').attr('action', 'ajax uri');
        deleteModal.find('.book-details').remove();
    });

    /* Resulting DOM template from JSON */
    function objToHTML(el) {
        // Return DOM element with attached functions to action buttons
        return $(`<tr class="book" data-id="${el._id}">`)
            .append($('<td>').prop('tabindex', 1).prop('headers', 'title').text(`${el['title']}`))
            .append($('<td>').prop('tabindex', 1).prop('headers', 'author').text(`${el['author']}`))
            .append($('<td>').prop('tabindex', 1).prop('headers', 'isbn').text(`${el['isbn']}`))
            .append($('<td>').addClass('uneditable')
                .append($(`<a href="${el._id}" class="edit" data-toggle="modal">`)
                    .append(`<i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>`)
                    .on('click', showEditModal))
                .append($(`<a href="${el._id}" class="delete" data-toggle="modal">`)
                    .append(`<i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>`)
                    .on('click', showDeleteConformation))
            );
    }

    /* Resulting JSON from DOM content */
    function HTMLToObj(sourceElement) {
        let obj = {};
        for (let field of sourceElement.find('[headers]')) {
            let el = $(field);
            obj[el.attr('headers')] = el.is('input') ? el.val() : el.text();
        }
        if (sourceElement.attr('data-id')) {
            obj._id = sourceElement.attr('data-id');
        }
        // console.log(obj);
        return obj;
    }

    /* Show modal for editing and temporally add to it book data */
    function showEditModal() {

        let dataRow = $(this).parents('.book');
        let theBookRecord = HTMLToObj($(dataRow));

        editModal.find('[name="title"]').val(theBookRecord.title);
        editModal.find('[name="author"]').val(theBookRecord.author);
        editModal.find('[name="isbn"]').val(theBookRecord.author);

        editModal.find('form').attr('action', `/books/${theBookRecord._id}`);
        editModal.modal('show');
        return false;
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
        //console.log('Display all books was called.');
        booksTable.find('tbody').empty();
        //$('.modal').modal('hide'); // hide all modals if any!?
        let rows = $('<tbody>');
        for (let el of data) {
            rows.append(objToHTML(el))
        }
        booksTable.append(rows)
    }

    /* AJAX requests */

    // AJAX request to load all books
    function loadAllBooks() {
        //console.log('Ajax to load all books was called');
        request('GET', '/books')
            .then(displayAllBooks)
            .catch(handleError);
    }

    // AJAX request to update book
    function editEntry(event) {
        event.preventDefault();
        let thatForm = $(this).find('form');
        let dataObj = HTMLToObj(thatForm);

        // Call ajax with with attributes of the form
        request(thatForm.attr('method'), thatForm.attr('action'), dataObj)
            .then(loadAllBooks)
            .catch(handleError);
    }

    // AJAX request to delete book
    function deleteEntry(event) {
        event.preventDefault();
        let thisForm = $(this).find('form');
        //console.log($(this));
        request(thisForm.attr('method'), thisForm.attr('action'))
            .then(loadAllBooks)
            .catch(handleError);
    }

    // AJAX request to create catch
    function createEntry(event) {
        event.preventDefault();
        let thisForm = $(this).find('form');
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