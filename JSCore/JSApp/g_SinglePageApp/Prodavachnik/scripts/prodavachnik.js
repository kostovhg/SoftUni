function startApp() {

    // adding pagination div to viewAds section
    $('#viewAds').append($('<div class="wrapper">')
        .append($('<div class="col-sm-12">')
            .append('<ul id="pagination-demo" class="pagination"></ul>')));

    // adding additional header to the table
    $('#ads').find('tr').first().append($('<th style="width: 200px">').text('Actions'));

    // Create additional view
    // too big view
    // $('main').append($('<section id="viewDetailsAd" class="viewDetailsAd">')
    //     .append($('<h1 class="titleForm">').text('View details for Advertisement'))
    //     .append($('<form id="formDetailsAd" class="form">')
    //         .append($('<div>')
    //             .append($('<img src="#" name="image">')))
    //         .append($('<p>').text('Publisher: ')
    //             .append($('<span name="publisher">')))
    //         .append($('<p>').text('Title: ')
    //             .append($('<span name="title">')))
    //         .append($('<p>').text('Description: ')
    //             .append($('<span rows="10">')))
    //         .append($('<p>').text('Date Published: ')
    //             .append($('<span name="datePublished">')))
    //         .append($('<p>').text('Price: ')
    //             .append($('<span name="price">')))
    //         .append($('<p>').text('Views: ')
    //             .append($('<span name="views">')))
    //         .append($('<div>')
    //             .append($('<input type="button" id="buttonHideDetails" name="back" value="Back">')))
    //     ));
    $('main').append($('<section id="viewDetailsAd" class="viewDetailsAd">')
        .append($('<h1 class="titleForm">').text('View details for Advertisement'))
        .append($('<form id="formDetailsAd" class="form" style="text-align: left">')
            .append(
                $('<img src="#" name="image">'),
                $('<p>').text('Title: ').append($('<h1 name="title">')),
                $('<p>').text('Description: ').append($('<span name="description">')),
                $('<p>').text('Publisher : ').append($('<span name="publisher">')),
                $('<p>').text('Date Published: ').append($('<span name="datePublished">')),
                $('<p>').text('Price: ').append($('<span name="price">'), $('<span>').text(' $')),
                $('<p>').text('Views: ').append($('<span name="views">')),
                $('<div>').append(
                    $('<input type="button" id="buttonHideDetails" name="back" value="Back">')))));

    // Append additional fields to Create and Edit views
    $('form').find('div').last();
    $('#viewEditAd').find('div').last().prepend($('<div>').text('Image:')
        .append($('<input type="file" name="image" accept="image/*">')));
    $('#viewCreateAd').find('div').last().prepend($('<div>').text('Image:')
        .append($('<input type="file" name="image" accept="image/*">')));


    showHideMenuLinks();
    showView('viewHome');
    attachAllEvents();
}