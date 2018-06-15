function addItem(){
    let item = document.getElementById("newItemText").value;
    let value = document.getElementById("newItemValue").value;

    let menu = document.getElementById("menu");

    let option = document.createElement("option");
    option.text=item;
    option.value = value;
    if(item && value) {
        menu.appendChild(option);
    }
    document.getElementById("newItemText").value = '';
    document.getElementById("newItemValue").value = '';
}