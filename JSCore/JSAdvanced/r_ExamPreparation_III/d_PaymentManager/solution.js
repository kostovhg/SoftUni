class PaymentManager {

    constructor(title) {
        this.title = title;
    }

    set title(title) {
        this._title = title;
    }

    add(event) {
        //console.log(event.target);
        let thisRow = $(event.target).parent().parent();
        let thisId = $($($(thisRow).parentsUntil('div')).last()).parent().attr('id');
        //console.log(thisId);
        let [nameField, categoryField, priceField] = [
            $(thisRow.find(`input[name="name"]`)),
            $(thisRow.find(`input[name="category"]`)),
            $(thisRow.find(`input[name="price"]`)),
        ];
        let [name, category, price] =
            [$(nameField).val(), $(categoryField).val(), $(priceField).val()];
        if (name !== '' && category !== '' && price !== '') {
            //console.log(`add row with name: ${name}, category: ${category}, price: ${price}`)
            $(`#${thisId} .payments`).append($('<tr>')
                .append($(`<td>`).text(name))
                .append($(`<td>`).text(category))
                .append($(`<td>`).text(Number(price)))
                .append($(`<td>`)
                    .append('<button>Delete</button>')
                    .on('click', (e) => {
                        $(e.target).parent().parent().remove();
                    })));
            nameField.val('');
            categoryField.val('');
            priceField.val('');
        }
    }

    render(id) {
        $(`#${id}`).append($('<table>')
            .append($('<caption>').text(`${this._title} Payment Manager`))
            .append($('<thead>')
                .append($('<tr>')
                    .append($(`<th class="name">`).text('Name'))
                    .append($(`<th class="category">`).text('Category'))
                    .append($(`<th class="price">`).text('Price'))
                    .append($(`<th>`).text('Actions'))))
            .append($('<tbody class="payments">'))
            .append($('<tfoot class="input-data">')
                .append($('<tr>')
                    .append($(`<td>`).append($('<input name="name" type="text">')))
                    .append($(`<td>`).append($('<input name="category" type="text">')))
                    .append($(`<td>`).append($('<input name="price" type="number">')))
                    .append($(`<td>`).append('<button>Add</button>').on('click', this.add))))
        )
    }
}


// module.exports = PaymentManager;

/*
Template Table
<table>
    <caption>{Title} Payment Manager</caption>
    <thead>
        <tr>
            <th class="name">Name</th>
            <th class="category">Category</th>
            <th class="price">Price</th>
            <th>Actions</th>
        </tr>
   </thead>
    <tbody class="payments">
        <tr>
            <td><!-- Payment's name --></td>
            <td><!-- Payment's category --></td>
            <td><!-- Payment's price --></td>
            <td><button>Delete</button></td>
        </tr>
        <tr>...</tr>
    </tbody>
    <tfoot class="input-data">
        <tr>
            <td><input name="name" type="text"></td>
            <td><input name="category" type="text"></td>
            <td><input name="price" type="number"></td>
            <td><button>Add</button></td></tr>
    </tfoot>
</table>

 */