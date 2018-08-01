const BASE_URL = 'https://baas.kinvey.com/';
const APP_KEY = 'kid_SJRn6W6QX';
const APP_SECRET = 'c08ff89210474e59b69199bdfa33b2ce';
const AUTH_HEADERS = {'Authorization': "Basic " + btoa(APP_KEY + ":" + APP_SECRET)};
const BOOKS_PER_PAGE = 8;
const NO_IMAGE = `https://storage.googleapis.com/9cf4cb42c6f448c38e5140b7052ddc75/0c320c96-3f98-4ccc-915a-aa657f7daf2d/download.jpeg`;

let requester = (() => {

    function getAuth(type) {
        return type === 'basic' ? `Basic ${btoa(APP_KEY + ":" + APP_SECRET)}` : `Kinvey ${sessionStorage.getItem('authToken')}`
    }

    function makeRequest(method, authType, module, url) {
        return {
            method,
            url: `${BASE_URL}${module}/${APP_KEY}/${url}`,
            headers: {
                'Authorization': getAuth(authType),
            },
        };
    }

    /* all of the following function should be called in async function as:
    try {
            let data = await requester.post('authType, module, url, data);
            saveSession(data);
            showView('theView')
    } catch (err) {
        console.log(err.responseText)
    }
      * */

    function get(authType, module, url) {
        return $.ajax(makeRequest("GET", authType, module, url));
    }

    function post(authType, module, url, data) {
        let request = $.ajax(makeRequest("POST", authType, module, url));
        request.data = data;
        return $.ajax(request)
    }

    function update(authType, module, url, data) {
        let request = $.ajax(makeRequest("PUT", authType, module, url));
        request.data = data;
        return $.ajax(request)
    }

    function remove(authType, module, url) {
        return $.ajax(makeRequest("DELETE", authType, module, url))
    }

    return {get, post, update, remove}
})();

function loginUser() {
    showView('viewLogin');
    let registerForm = $('#formLogin');
    let [username, password] = [
        registerForm.find('[name=username]').val(),
        registerForm.find('[name=passwd]').val()
    ];
    $.ajax({
        method: "POST",
        url: `${BASE_URL}user/${APP_KEY}/login`,
        headers: AUTH_HEADERS,
        data: {username, password}
    }).then(function (res) {
        signInUser(res, 'Login successful.');
        showView('viewHome');
    }).catch(function (err) {
        handleAjaxError(err);
    })
}

function registerUser() {
    let registerForm = $('#formRegister');
    let [username, password] = [
        registerForm.find('[name=username]').val(),
        registerForm.find('[name=passwd]').val()
    ];
    $.ajax({
        method: "POST",
        url: `${BASE_URL}user/${APP_KEY}/`,
        headers: AUTH_HEADERS,
        data: {username, password}
    }).then(function (res) {
        signInUser(res, 'Registration successful.')
    }).catch(function (err) {
        handleAjaxError(err);
    })

}

function listAds() {
    $.ajax({
        method: "GET",
        url: `${BASE_URL}appdata/${APP_KEY}/adv?query={}&sort={"views": -1}`,
        headers: {'Authorization': "Kinvey " + sessionStorage.getItem('authToken')}
    }).then(function (res) {
        if (Array.isArray(res)) {
            // res = res.sort((a1, a2) => +a2.views - +a1.views);
            displayPaginationAndAds(res);
        }
        showView('viewAds');
    }).catch(function (err) {
        handleAjaxError(err);
    });
}

function createAd() {
    let createForm = $('#formCreateAd');
    let [title, description, datePublished, price, image, fileReader, imageRequestReturn] = [
        createForm.find('[name=title]').val(),
        createForm.find('[name=description]').val(),
        createForm.find('[name=datePublished]').val(),
        createForm.find('[name=price]').val(),
        createForm.find('[name=image]')[0].files[0],
        new FileReader(),
        {}
    ];

    let data = {};
    data.title = title;
    data.description = description;
    data.datePublished = datePublished;
    data.price = price;
    // data.views = 0;

    // returns uploadURL
    function imageRequest() {
        let $data = {
            "_filename": image.name,
            "_acl": {
                'gr': true,
                "creator": sessionStorage.getItem('userId')
            },
            '_public': true,
            // 'size': image.size, // automatically added
            // 'mimeType': image.type,
        };

        $.ajax({
            method: "POST",
            url: `${BASE_URL}blob/${APP_KEY}`,
            headers: {
                'Authorization': "Kinvey " + sessionStorage.getItem('authToken'),
                'X-Kinvey-API-Version': 3,
                'X-Kinvey-Content-Type': image.type
            },
            data: $data
        }).then(function (res) {

            imageUpload(res);
        }).catch(function (err) {
            handleAjaxError(err);
        });
    }

    // Upload raw file data
    function imageUpload(imageRequestData) {

        fileReader.onload = function () {
            var $data = {'file': reader.result};
            $.ajax({
                type: 'POST',
                url: imageRequestData._uploadURL,
                headers: {
                    'Content-Type': image.type,
                    'Content-Length': image.size,
                },
                data: $data,
                processData: false,
                contentType: false, // this is important!!!
            }).then(function (res) {
                uploadAd(res);
            }).catch(function (err) {
                handleAjaxError(err);
            });
            fileReader.readAsDataUrl(image);
        };
    }

    // upload ad
    function uploadAd(adToUpload) {
        let $data = {
            title,
            publisher: sessionStorage.getItem('username'),
            description,
            price,
            datePublished,
            imageUrl: adToUpload._downloadURL,
        };
        $.ajax({
            method: "POST",
            url: `${BASE_URL}appdata/${APP_KEY}/adv`,
            headers: {'Authorization': "Kinvey " + sessionStorage.getItem('authToken')},
            data: $data,
        }).then(function (res) {
            showInfo('Advertisement created.');
            listAds();
        }).catch(function (err) {
            handleAjaxError(err);
        });
    }

    if (image) {
        if (['image/jpeg', 'image/jpg', 'image/png', 'image/gif'].indexOf(image.type) === -1) {
            alert('Error : Only JPEG, PNG & GIF allowed');
            return;
        }
        imageRequest();
    } else {
        uploadAd({_downloadURL: NO_IMAGE})
    }
}

function deleteAd(ad) {
    $.ajax({
        method: "DELETE",
        url: `${BASE_URL}appdata/${APP_KEY}/adv/${ad._id}`,
        headers: {'Authorization': "Kinvey " + sessionStorage.getItem('authToken')}
    }).then(function (res) {
        listAds();
    }).catch(function (err) {
        handleAjaxError(err);
    });
}

function loadAdForEdit(ad) {
    let editView = $('#viewEditAd');
    editView.find('[name=id]').val(ad._id);
    for (let key of Object.keys(ad)) {
        if (key === 'image') continue;
        editView.find(`[name=${key}]`).val(ad[key]);
    }
    showView('viewEditAd');
}

function editAd(e) {

    let theForm = $('#viewEditAd');
    let [id, publisher, title, description, datePublished, price, image] = [
        theForm.find('[name=id]').val(),
        theForm.find('[name=publisher]').val(),
        theForm.find('[name=title]').val(),
        theForm.find('[name=description]').val(),
        theForm.find('[name=datePublished]').val(),
        theForm.find('[name=price]').val(),
        //theForm.find('[name=image]')[0].files[0]
    ];

    $.ajax({
        method: "PUT",
        url: `${BASE_URL}appdata/${APP_KEY}/adv/${id}`,
        headers: {'Authorization': "Kinvey " + sessionStorage.getItem('authToken')},
        data: {publisher, title, description, price, datePublished}
    }).then(function (res) {
        showInfo('Advertisement edited.');
        listAds();
    }).catch(function (err) {
        handleAjaxError(err);
    })
}

function loadDetails(ad) {

    $.ajax({
        method: "GET",
        url: `${BASE_URL}appdata/${APP_KEY}/adv/${ad._id}`,
        headers: {'Authorization': "Kinvey " + sessionStorage.getItem('authToken')},
    }).then(function (res) {
        // In case of using form for display
        // let details = $('#viewDetailsAd');
        // for (let key of Object.keys(res)) {
        //     details.find(`[name=${key}]`).text(ad[key]);
        // }
        // if used as in the description
        displayAdvert(res);
        showView('viewDetailsAd')
    }).catch(function (err) {
        handleAjaxError(err);
    })
}

function saveAuthInSession(userInfo) {
    sessionStorage.setItem('authToken', userInfo._kmd.authtoken);
    sessionStorage.setItem('username', userInfo.username);
    sessionStorage.setItem('userId', userInfo._id);
}

function logoutUser() {
    $.ajax({
        method: "POST",
        url: `${BASE_URL}user/${APP_KEY}/_logout`,
        headers: {'Authorization': "Kinvey " + sessionStorage.getItem('authToken')}
    }).then(function (res) {
        sessionStorage.clear();
        showInfo('Logout successful.');
        showHideMenuLinks();
        showHomeView();
    }).catch(function (err) {
        handleAjaxError(err);
    });
}

function signInUser(res, message) {
    saveAuthInSession(res);
    showInfo(message);
    showHideMenuLinks();
    listAds();
}

function displayPaginationAndAds(ads) {

    showView('viewAds');

    let adsDiv = $('#ads');
    let adsTable = adsDiv.find('table');

    $('#empty-collection-warning').remove();

    $(adsTable).find('tr').slice(2, -1).remove();

    if (ads.length < 1) {
        adsTable.hide();
        adsDiv.append($('<p id="empty-collection-warning" style="text-align: center">').text('No adv available.'))
    } else {
        $('#ads table').show();
        let pagination = $('#pagination-demo');
        if (pagination.data("twbs-pagination")) {
            pagination.twbsPagination('destroy')
        }
        pagination.twbsPagination({
            totalPages: Math.ceil(ads.length / BOOKS_PER_PAGE),
            visiblePages: 5,
            next: 'Next',
            prev: 'Prev',
            onPageClick: function (event, page) {
                adsTable.find('tr').slice(1).remove();
                let startAd = (page - 1) * BOOKS_PER_PAGE;
                let endAd = Math.min(startAd + BOOKS_PER_PAGE, ads.length);
                $(`a:contains(${page})`).addClass('active');
                for (let i = startAd; i < endAd; i++) {
                    let ad = ads[i];
                    let row = $(`<tr>`)
                        .append($('<td>').text(ad.title))
                        .append($('<td>').text(ad.publisher))
                        .append($('<td>').text(ad.description))
                        .append($('<td>').text(ad.price))
                        .append($('<td>').text(ad.datePublished))
                        .append($('<td>')
                            .append($('<a href="#">')
                                .text('[Read More]')
                                .on('click', () => loadDetails(ad))));
                    /*if (ad.publisher === sessionStorage.getItem('username')) {*/
                    if (ad._acl && ad._acl.creator === sessionStorage.getItem('userId')) {
                        row.find('td').last()
                            .append($('<a href="#">').text('[Delete]').on('click', () => deleteAd(ad)))
                            .append($('<a href="#">').text('[Edit]').on('click', () => loadAdForEdit(ad)));
                    }
                    adsTable.append(row);
                }
            }
        })
    }
}

function handleAjaxError(response) {
    let errorMsg = JSON.stringify(response);
    if (response.readyState === 0)
        errorMsg = "Cannot connect due to network error.";
    if (response.responseJSON && response.responseJSON.description)
        errorMsg = response.responseJSON.description;
    showError(errorMsg);
}