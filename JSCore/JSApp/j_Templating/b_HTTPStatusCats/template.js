$(() => {
    renderCatTemplate();

    function renderCatTemplate() {
        (async () => {
            const [
                catsTemplate,
                container,
                catTemplate
            ] = await Promise.all([
                $('#cat-template').html(),
                $('#allCats'),
                $.get('./templates/catTemplate.hbs')
            ]);

            Handlebars.registerPartial('catTemplate', catTemplate);
            let rendered = Handlebars.compile(catsTemplate)({cats});
            container.append(rendered);

            $('.btn').on('click', function (e) {
                let that = $(e.currentTarget);
                if (that.text().startsWith('Show')) {
                    that.text('Hide status code');
                    that.next().css('display', 'block')
                } else {
                    that.text('Show status code');
                    that.next().css('display', 'none')
                }
            })
        })();
    }
});
