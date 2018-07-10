class PublicTransportTable {

    constructor(name) {
        this.name = name;
        this.attachEventsListeners();
    }

    set name(name) {
        this._name = name;
        $('table>caption').text(`${name}'s Public Transport`)
    }

    addVehicle(vehicle) {
        $('.vehicles-info')
            .append($('<tr class="hide-info">')
                .append($('<td>').text(`${vehicle.type}`))
                .append($('<td>').text(`${vehicle.name}`))
                .append($('<td>')
                    .append($('<button>').text('More Info')
                        .on('click', (e) => {
                            let thatRow = $(e.target).parent().parent();
                            $(e.target).text('Less Info');
                            if (thatRow.hasClass('hide-info')) {
                                thatRow.attr('class', 'show-info');
                                ($('<tr class="more-info">')
                                    .append($('<td colspan="3">')
                                        .append($('<table>')
                                            .append($('<tr>').append($('<td>').text(`Route: ${vehicle.route}`)))
                                            .append($('<tr>').append($('<td>').text(`Price: ${vehicle.price}`)))
                                            .append($('<tr>').append($('<td>').text(`Driver: ${vehicle.driver}`)))
                                        ))).insertAfter(thatRow)
                            } else {
                                thatRow.attr('class', 'hide-info');
                                $(e.target).text('More Info');
                                thatRow.next().remove();
                            }
                        }))))
    }

    attachEventsListeners() {
        $('.search-btn').on('click', () => {
            let [typeField, nameField] = [$('input[name=type]'), $('input[name=name]')];
            let [type, name] = [typeField.val(), nameField.val()];
            if (!type && !name) return null;
            $('.show-info, .hide-info').each(function () {
                let that = $(this);
                let [thatType, thatName, thatButton] = [
                    that.find('td').eq(0).text(),
                    that.find('td').eq(1).text(),
                    that.find('button')[0]];
                if (!thatType.includes(type) || !thatName.includes(name)) {
                    if (that.next().hasClass('more-info')) {
                        that.find('button')[0].click();
                    }
                    that.css('display', 'none');
                    //console.log('Change type ' + thatType + ' name ' + thatName +' to hide')
                } else {
                    that.css('display', '');
                    //console.log('Change type ' + thatType + ' name ' + thatName +' to show')
                }
            })
        })

        $('.clear-btn').on('click', () => {
            $('input[name=type]').val('');
            $('input[name=name]').val('');
            $('.show-info, .hide-info').each(function () {
                let that = $(this);
                that.css('display', '');
            })
        })
    }
}