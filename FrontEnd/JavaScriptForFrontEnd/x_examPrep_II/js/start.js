// On document load
$(() => {

    app.initialize();

    app.populate();

    app.fillPositions();

    $('form').bind('reset submit', function (e) {
        e.preventDefault();
        app.populate();
        app.fillPositions();
        $(':focus').blur();
    });
});