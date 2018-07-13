class PublicTransportTable {

    constructor(name) {
        this.name = name;
        this.attachEventsListeners();
    }

    set name(name) {
        this._name = name;
        $(`table>caption`).text(`${name}'s Public Transport`)
    }

    addVehicle(vehicle) {
        $(`.vehicles-info`)
            .append($(`<tr class="hide-info"><td>${vehicle.type}</td><td>${vehicle.name}</td></tr>`)
                .append($('<td>')
                    .append($('<button>More Info</button>')
                        .on('click', (e) => {
                            let thatRow = $(e.target).parent().parent();
                            if (thatRow.hasClass('hide-info')) {
                                $(e.target).text('Less Info');
                                thatRow.attr('class', 'show-info')
                                    .after($(`<tr class="more-info"><td colspan="3">` +
                                        `<table><tr><td>Route: ${vehicle.route}</td></tr>` +
                                        `<tr><td>Price: ${vehicle.price}</td></tr>` +
                                        `<tr><td>Driver: ${vehicle.driver}</td></tr>`))
                            } else {
                                thatRow.attr('class', 'hide-info');
                                $(e.target).text('More Info');
                                thatRow.next().remove();
                            }}))));
    }

    attachEventsListeners() {
        $('.search-btn').on('click', () => {
            let [type, name] = [$('input[name=type]').val(), $('input[name=name]').val()];
            if (!type && !name) return null;
            $('.show-info, .hide-info').each(function () {
                let that = $(this);
                if (!that.find('td').eq(0).text().includes(type) || !that.find('td').eq(1).text().includes(name)) {
                    if (that.next().hasClass('more-info')) {
                        that.find('button')[0].click();
                    }
                    that.css('display', 'none');
                } else {
                    that.css('display', '');
                }
            })
        });

        $('.clear-btn').on('click', () => {
            $('input[name=type]').val('');
            $('input[name=name]').val('');
            $('.show-info, .hide-info').each(function () {
                $(this).css('display', '');
            })
        })
    }
}
