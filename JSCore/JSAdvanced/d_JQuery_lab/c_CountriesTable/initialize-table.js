function initializeTable() {

    let getRow = (e) => $(e.currentTarget).parent().parent();
    let updateButtons = () => {
        $('#countriesTable a').css('display', 'inline');
        $('#countriesTable tr:eq(2) a:contains("Up")').css('display', 'none');
        $('#countriesTable tr:last a:contains("Down")').css('display', 'none');
    };

    // initialize
    // fill up
    let initialContent = [
        {name: 'Bulgaria', capital: 'Sofia'},
        {name: 'Germany', capital: 'Berlin'},
        {name: 'Russia', capital: 'Moscow'}
    ];

    initialContent.forEach(x => addCountryToTable(x.name, x.capital));

    // add functionality

    function addCountryToTable(country, town) {
        $('#countriesTable')
            .append($('<tr>')
                .append($('<td>').text(country))
                .append($('<td>').text(town))
                .append($('<td>')
                    .append($('<a>')
                        .attr('href', '#')
                        .text('[Up]')
                        .on('click', (e) => {
                            let cRow = getRow(e);
                            if (cRow.index() > 2) {
                                cRow.fadeOut(() => {
                                    cRow.insertBefore(cRow.prev());
                                    cRow.fadeIn();
                                    updateButtons();
                                });
                                //cRow.fadeIn();
                            }
                        })
                    )
                    .append($('<a>')
                        .attr('href', '#')
                        .text('[Down]')
                        .on('click', (e) => {
                            let cRow = getRow(e);
                            if (cRow.index() < $('#countriesTable tr').length - 1) {
                                cRow.fadeOut(() => {
                                    cRow.insertAfter(cRow.next());
                                    cRow.fadeIn();
                                    updateButtons();
                                });
                            }
                        }))
                    .append($('<a>')
                        .attr('href', '#')
                        .text('[Delete]')
                        .on('click', (e) => {
                            getRow(e).fadeOut().remove();
                            updateButtons()
                        }))
                ).hide().fadeIn()
            );
    }
    updateButtons();

    $('#createLink').on('click', createCountry);


    function createCountry() {
        let [country, capital] = [$('#newCountryText'), $('#newCapitalText')];
        if (country.val() && capital.val()) {
            addCountryToTable(country.val(), capital.val());
            updateButtons();
        } else {
            alert('You should add both, country and capital!')
        }
        country.val('');
        capital.val('');
    }


}