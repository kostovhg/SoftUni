function addItem() {
    let newItem = document.getElementById('newItemText');
    let list = document.getElementById('items');
    let newLi = document.createElement('li');
    newLi.textContent = newItem.value;
    newItem.value = '';
    list.appendChild(newLi);
}