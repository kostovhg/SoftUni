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

    // Add validations to the forms
    // let [validation, datLenAttr, errMsg] = [
    //     'data-validation', 'data-validation-length', 'data-validation-error-msg'
    // ];
    //
    // let [title, description, date, price] = [
    //     $('input[name="title"]'),
    //     $('input[name="description"]'),
    //     $('input[name="datePublished"]'),
    //     $('input[name="price"]'),
    // ];
    //
    //
    // title
    //     .attr(validation, 'alphanumeric length')
    //     .attr(datLenAttr, '3-30')
    //     .attr(errMsg, `The title should only contain 3-30 alphanumeric characters.`);
    //
    // description
    //     .attr(validation, 'length')
    //     .attr(datLenAttr, '3-120')
    //     .attr(errMsg, 'The description should only contain 3-120 characters.');
    //
    // date
    //     .val(Date.now())
    //     .attr(validation, 'date')
    //     .attr('data-validation-format', 'yyyy-mm-dd')
    //
    // price
    //     .val('0.0')
    //     .attr(validation, 'number')
    //     .attr('data-validation-allowing', 'range[0, 1000000000],float')
    //
    // $(document).ready(function () {
    //
    //     $('form').each(function () {
    //         $(this).validate({
    //             submitHandler: function (form) { // for demo
    //                 alert('valid form');
    //                 return false;
    //             }
    //         });
    //     });
    //
    // });

    showHideMenuLinks();
    showView('viewHome');
    attachAllEvents();
}