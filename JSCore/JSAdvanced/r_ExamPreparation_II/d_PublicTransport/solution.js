class PublicTransportTable {

    constructor(name) {
        this.name = name;
        this._vehicles = [];
        this.addEventListeners();
    }

    set name(name) {
        this._name = name;
        this.changeHeaderName();
    }

    changeHeaderName() {
        $('table caption').text(`${this._name}'s Public Transport`);
    }

    addVehicle(obj) {
        let tr = $(`<tr class="hide-info"><td>${obj.type}</td><td>${obj.name}</td></tr>`)
        let button = $('<button>More Info</button>');
        let trExtra = $(`<tr class="more-info"><td colspan="3"><table>` +
            `<tr><td>Route: ${obj.route}</td></tr>` +
            `<tr><td>Price: ${obj.price}</td></tr>` +
            `<tr><td>Driver: ${obj.driver}</td></tr>`);
        button.on('click',  (event) => {
            if ($(event.target).text() === 'More Info') {
                $(event.target).text('Less Info')
                trExtra.insertAfter(tr);
            } else {
                $(event.target).text('More Info');
                trExtra.remove();
            }
        });

        tr.append($('<td>').append(button));
        $('.vehicles-info').append(tr);
    }

    addEventListeners(){

        $('.search-btn').on('click', function () {
            let typeForm = $('input[name="type"]');
            let nameForm = $('input[name="name"]');
            let type = typeForm.val();
            let name = nameForm.val();
            let rows = $('.vehicles-info > tr').not('.more-info');
            console.log(type + name);

            if (name || type) {

                for (let i = 0; i < rows.length; i++) {
                    let firstChild = $(rows[i]).find('td').eq(0);
                    let secondChild = $(rows[i]).find('td').eq(1);
                    if(!secondChild.text().includes(name) || !firstChild.text().includes(type)){
                        $(rows[i]).css('display', 'none');
                        let but = $(rows[i]).find('td').eq(2).find('button');
                        if (but.text() === 'Less Info')
                            but.click();
                        console.log(but)
                    } else {
                        $(rows[i]).css('display', '');
                    }
                }
            }
        });

        $('.clear-btn').on('click', function () {
            let typeForm = $('input[name="type"]');
            let nameForm = $('input[name="name"]');
            let type = typeForm.val();
            let name = nameForm.val();
            let rows = $('.vehicles-info > tr').not('.more-info');
                typeForm.val('');
                nameForm.val('');
            for (let i = 0; i < rows.length; i++) {
                $(rows[i]).css('display', '');
            }
        })
    }
}