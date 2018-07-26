function attachEvents() {

    /* Constants  */
    const KINVEY_BIGGESTCATCHES_URL = `https://baas.kinvey.com/appdata/kid_SJRn6W6QX`;
    const KINVEY_AUTH_HEADER = {'Authorization': "Basic " + btoa("guest:guest")};

    /* All purpose variables */
    let catchesField = $('#catches');


    /* Attach events */
    $('.load').click(loadAllCatches);
    $('.add').click(createCatch);


    /* All AJAX methods interface */
    function request(method, endpoint, data) {
        return $.ajax({
            method: method,
            url: KINVEY_BIGGESTCATCHES_URL + endpoint,
            headers: KINVEY_AUTH_HEADER,
            data: JSON.stringify(data)
            //data: data
        })
    }

    /* Resulting DOM template from JSON */
    function catchViewTemplate(el) {
        return $(`<div class="catch" data-id="${el._id}">`)
            .append($(`<label>`).text(`Angler`))
            .append($(`<input type="text" class="angler" value="${el[`angler`]}"/>`))
            .append($(`<label>`).text(`Weight`))
            .append($(`<input type="number" class="weight" value="${el[`weight`]}"/>`))
            .append($(`<label>`).text(`Species`))
            .append($(`<input type="text" class="species" value="${el[`species`]}"/>`))
            .append($(`<label>`).text(`Location`))
            .append($(`<input type="text" class="location" value="${el[`location`]}"/>`))
            .append($(`<label>`).text(`Bait`))
            .append($(`<input type="text" class="bait" value="${el[`bait`]}"/>`))
            .append($(`<label>`).text(`Capture Time`))
            .append($(`<input type="number" class="captureTime" value="${el[`captureTime`]}"/>`))
            .append($(`<button class="update">Update</button>`).click(updateCatch))
            .append($(`<button class="delete">Delete</button>`).click(deleteCatch))
    }

    /* Resulting JSON from DOM content */
    function createDataJSON(catchDiv) {
        return {
            angler: catchDiv.find('.angler').val(),
            weight: +catchDiv.find('.weight').val(),
            species: catchDiv.find('.species').val(),
            location: catchDiv.find('.location').val(),
            bait: catchDiv.find('.bait').val(),
            captureTime: +catchDiv.find('.captureTime').val(),
        }
    }

    /* DOM update */
    function displayAllCatches(data) {
        catchesField.empty();
        for (let el of data) {
            catchesField.append(catchViewTemplate(el))
        }
    }

    /* AJAX requests */

    // AJAX request to load all catches
    function loadAllCatches() {
        request('GET', '/biggestCatches')
            .then(displayAllCatches)
            .catch(handleError);
    }

    // AJAX request to update catch
    function updateCatch() {
        let catchDiv = $(this).parent();
        let dataObj = createDataJSON(catchDiv);
        request('PUT', `/biggestCatches/${catchDiv.attr('data-id')}`, dataObj)
            .then(loadAllCatches)
            .catch(handleError);
    }

    // AJAX request to delete catch
    function deleteCatch() {
        request('DELETE', `/biggestCatches/${$(this).parent().attr('data-id')}`)
            .then(loadAllCatches)
            .catch(handleError)
    }

    // AJAX request to create catch
    function createCatch() {
        let dataObj = createDataJSON($('#addForm'));
        request('POST', '/biggestCatches', dataObj)
            .then(function(res) {
                loadAllCatches();
                $('#addForm input').val('');
            })
            .catch(handleError)
    }

    function handleError(err) {
        alert(`Error: ${err.statusText}`)
    }
}