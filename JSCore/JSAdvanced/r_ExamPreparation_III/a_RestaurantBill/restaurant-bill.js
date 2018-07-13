function addProduct() {
    let [tBody, total, productField, priceField] = [
        $('#product-list'),
        $('#bill tfoot tr td:last'),
        $('#add-product label:contains("Product") input:first'),
        $('#add-product label:contains("Price") input:first')
    ];
    let [product, price] = [$(productField).val(), $(priceField).val()]

    if(product !== '' && price !== '') {
        $(tBody).append($(`<tr><td>${product}</td><td>${price}</td></tr>`));
        $(total).text(Number($(total).text()) + Number(price));
        $(productField).val('');
        $(priceField).val('');
    }
}