function attachEvents() {
    (async () => {
        const [
            root,
            inputTowns,
            submitBtn,
            townsTemplate
        ] = await Promise.all([
            $('#root'),
            $('#towns'),
            $('#btnLoadTowns'),
            $('#towns-template').html(),
        ]);

        submitBtn.on('click', function () {
            $('#towns-list').remove();
            let inputs = inputTowns.val().split(', ');
            inputTowns.val('');
            let renderedHTML = Handlebars.compile(townsTemplate);
            root.append(renderedHTML({towns: inputs}))
        })
    })();
}