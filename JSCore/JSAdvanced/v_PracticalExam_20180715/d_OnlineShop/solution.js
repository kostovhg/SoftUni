function onlineShop(selector) {
    let form = `<div id="header">Online Shop Inventory</div>
    <div class="block">
        <label class="field">Product details:</label>
        <br>
        <input placeholder="Enter product" class="custom-select">
        <input class="input1" id="price" type="number" min="1" max="999999" value="1"><label class="text">BGN</label>
        <input class="input1" id="quantity" type="number" min="1" value="1"><label class="text">Qty.</label>
        <button id="submit" class="button" disabled>Submit</button>
        <br><br>
        <label class="field">Inventory:</label>
        <br>
        <ul class="display">
        </ul>
        <br>
        <label class="field">Capacity:</label><input id="capacity" readonly>
        <label class="field">(maximum capacity is 150 items.)</label>
        <br>
        <label class="field">Price:</label><input id="sum" readonly>
        <label class="field">BGN</label>
    </div>`;
    $(selector).html(form);
    // Write your code here

    let [capacity, sum] = [0, 0];

    let [productField, priceField, quantityField, btn, display, capacityField, sumField] =
        [
            $('.custom-select:first'),
            $('#price'),
            $('#quantity'),
            $('#submit'),
            $('ul.display'),
            $('#capacity'),
            $('#sum')
        ];

    function update() {
        sum += Number(priceField.val()) * Number(quantityField.val());
        capacity += Number(quantityField.val());
        productField.val('');
        priceField.val(1);
        quantityField.val(1);
        sumField.val(sum);
        if (capacity >= 150) {
            $(capacityField).val('full').addClass('fullCapacity');
            productField.prop('disabled', true);
            priceField.prop('disabled', true);
            quantityField.prop('disabled', true);
            btn.prop('disabled', true);
        } else {
            capacityField.val(capacity);
        }
        sumField.val(sum);
    }

    function attachEvents() {
        productField.on('keyup', (event) => {
            btn.prop('disabled', productField.val() === '');
        });


        btn.on('click', (event) => {
            display
                .append($(`<li>Product: ${productField.val()} Price: ${priceField.val()} Quantity: ${quantityField.val()}</li>`));
            update();
            $(event.target).prop('disabled', true);
        });
    };

    console.log(capacityField.val());
    attachEvents();
}
