class Dialog {

    constructor(msg, callback) {
        this.message = msg;
        this.callback = callback;
        this.inputs = [];
    }

    addInput(label, name, type) {
        this.inputs.push({label, name, type});
    }

    render() {
        let dialog = $('<div class="dialog">')
            .append($(`<p>`).text(this.message))
        for (let input of this.inputs) {
            dialog.append($('<label>').text(input.label))
                .append($(`<input>`)
                    .attr('name', input.name)
                    .attr('type', input.type))
        }
        dialog.append($('<button>').text('OK')
            .on('click', (event) => {
                let [that, param] = [$(event.target), {}];
                [...that.parent().find('input')]
                    .filter(i => $(i).val() !== '')
                    .forEach(i => param[$(i).attr('name')] = $(i).val());
                this.callback.call(that, param);
                that.next().click();
            }))
            .append($('<button>').text('Cancel')
                .on('click', (event) =>
                    $(event.target).parent().parent().remove()));

        $('body').append($('<div class="overlay">').append(dialog));
    }
}