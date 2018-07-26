function startApp() {

    /* Constants  */
    const KINVEY_BASE_URL = `https://baas.kinvey.com/appdata/kid_SJRn6W6QX`;
    const KINVEY_AUTH_HEADER = {'Authorization': "Basic " + btoa("guest:guest")};

    /* All purpose variables */
    let booksTable = $('#books-table tbody');

    /* Attach events */
    $('.btn-info').click(loadAllBooks);
    $('#create-btn').click(showForm);
    $('.glyphicon-ok, input[type=submit]').click(createEntry);
    $('.glyphicon-plus').click(switchInputRow);


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

    /* Resulting DOM template from JSON */
    function bookRowTemplate(el) {
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

    /* Show modal for editing */
    function showEditModal() {
        let theModal = $('#editBookModal');
        theModal.find('input[headers]').val('');
        let dataRow = $(this).parents('.book');
        if(dataRow) {
            let theBookRecord = getObjectFromDiv($(dataRow));
            theModal.find('[headers="title"]').val(theBookRecord.title);
            theModal.find('[headers="author"]').val(theBookRecord.author);
            theModal.find('[headers="isbn"]').val(theBookRecord.isbn);
        }
        theModal.modal('show');
        return false;
    }

    /* Show modal for deleting */
    function showDeleteConformation() {
        let dataRow = $(this).parents('.book');
        let theBookRecord = getObjectFromDiv($(dataRow));
        theBookRecord._id = $(this).parents('.book').attr('data-id');
        let theModal = $('#deleteBookModal');
        theModal.find('.book-details').remove()
        theModal.find('.modal-body')
            .prepend($('<p class="book-details">')
                .html(`Deleting book <span>"${theBookRecord.title}"</span> from <b>${theBookRecord.author}</b> with id <b>${theBookRecord._id}</b> will be deleted permanently!`));
        theModal.modal('show').attr('href', theBookRecord._id);
        return false;
    }


    /* Resulting JSON from DOM content */
    function getObjectFromDiv(sourceElement) {
        let obj = {
            title: sourceElement.find('[headers="title"]').text(),
            author: sourceElement.find('[headers="author"]').text(),
            isbn: sourceElement.find('[headers="isbn"]').text(),
        }
        if(sourceElement.attr('data-id')){
            obj._id = sourceElement.attr('data-id');
        }
        return obj;
    }

    /* DOM update */
    function displayAllBooks(data) {
        booksTable.empty();
        $('#editable').removeClass('hidden');
        $('.create-form').addClass('hidden');
        for (let el of data) {
            booksTable.append(bookRowTemplate(el))
        }
    }

    /* DOM Manipulate */
    function switchInputRow() {
        $('.input-row').toggleClass('hidden')
    }

    function showForm() {
        $('#editable').toggleClass('hidden');
        $('.create-form').toggleClass('hidden');
    }

    /* AJAX requests */

    // AJAX request to load all books
    function loadAllBooks() {
        request('GET', '/books')
            .then(displayAllBooks)
            .catch(handleError);
    }

    // AJAX request to update book
    function updateEntry() {
        let catchDiv = $(this).parent().parent();
        let dataObj = getObjectFromDiv(catchDiv);
        request('PUT',
            `/books/${catchDiv.attr('data-id')}`
            , dataObj)
            .then(loadAllBooks)
            .catch(handleError);
    }

    // AJAX request to delete book
    function deleteEntry() {
        let catchDiv = $(this).parent().parent();
        request('DELETE',
            `/books/${catchDiv.attr('data-id')}`
        )
            .then(loadAllBooks)
            .catch(handleError)
    }

    // AJAX request to create catch
    function createEntry() {
        let dataObj = getObjectFromDiv($('.input-row'));
        console.log(dataObj);
        request('POST', '/books', dataObj)
            .then(function (res) {
                loadAllBooks();
                console.log(res)
                switchInputRow();
            })
            .catch(handleError)
    }

    function handleError(err) {
        alert(
            `Error: ${err.statusText}`
        )
    }

}