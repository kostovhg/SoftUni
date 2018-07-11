function addItem() {

    let [text, value, menu] = [
        document.getElementById('newItemText'),
        document.getElementById('newItemValue'),
        document.getElementById('menu')
    ];
    let item = document.createElement('option');
    item.text = text.value;
    item.value = value.value;
    menu.add(item);
    text.value = '';
    value.value = '';
}