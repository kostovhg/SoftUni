function showView(viewName) {
    $('main > section').hide(); // Hide all views
    $('#' + viewName).show(); // Show the selected view only
}

function showHideMenuLinks() {
    $("#linkHome").show();
    if (sessionStorage.getItem('authToken') === null) { // No logged in user
        $("#linkLogin").show();
        $("#linkRegister").show();
        $("#linkListAds").hide();
        $("#linkCreateAd").hide();
        $("#linkLogout").hide();
        $('#loggedInUser').text('').hide();
    } else { // We have logged in user
        $("#linkLogin").hide();
        $("#linkRegister").hide();
        $("#linkListAds").show();
        $("#linkCreateAd").show();
        $("#linkLogout").show();
        $('#loggedInUser').text("Welcome, " + sessionStorage.getItem('username') + "!").show();
    }
}

function showInfo(message) {
    let infoBox = $('#infoBox');
    infoBox.text(message);
    infoBox.show();
    setTimeout(function () {
        $('#infoBox').fadeOut()
    }, 3000);
}

function showError(errorMsg) {
    let errorBox = $('#errorBox');
    errorBox.text("Error: " + errorMsg);
    errorBox.show();
}

function showHomeView() {
    showView('viewHome')
}

function showLoginView() {
    showView('viewLogin');
    $('#formLogin').trigger('reset')
}

function showRegisterView() {
    $('#formRegister').trigger('reset');
    showView('viewRegister');
}

function showCreateAdView() {
    $('#formCreateAd').trigger('reset');
    showView('viewCreateAd');
}

function displayAdvert(adv) {
    //let viewDetailsAd =  $('#viewDetailsAd');

    // viewDetailsAd.empty();
    //
    // let advertInfo = $('<div>').append(
    //     $('<br>'),
    //     $(`<img scr="${adv.imageurl || '#' }">`),
    //     $('<br>'),
    //     $('<label>').text('Title: '),
    //     $('<h1>').text(adv.title),
    //     $('<label>').text('Description: '),
    //     $('<p>').text(adv.description),
    //     $('<label>').text('Publisher: '),
    //     $('<div>').text(adv.publisher),
    //     $('<label>').text('Date: '),
    //     $('<div>').text(adv.datePublished),
    //     $('<label>').text('Views: '),
    //     $('<div>').text(adv.views),
    //     $('<input type="button" id="buttonHideDetails" name="back" value="Back">'));
    //
    // $(viewDetailsAd).append(advertInfo);

    let details = $('#viewDetailsAd');
    let imgSrc = details.find('[name=image]');
    imgSrc.attr('src', '#');
    for (let key of Object.keys(adv)) {
        key !== 'imageUrl' ?
            details.find(`[name=${key}]`).text(adv[key]) :
            // details.find(`[name=image]`).attr('src', adv.image || '#' adv.image || '#');
            imgSrc.attr('src', adv[key]);
    }

}