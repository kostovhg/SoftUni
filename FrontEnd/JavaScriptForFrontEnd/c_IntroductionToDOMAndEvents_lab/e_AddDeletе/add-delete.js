function addItem() {
    let newText = document.getElementById('newText');
    if(newText.value === ''){
        return;
    }

    let [list, newLi, span] = [
        document.getElementById('items'),
        document.createElement('li'),
        document.createElement('span'),
    ];

    span.innerHTML = `<a href="#">[Delete]</a>`;
    span.firstChild.addEventListener('click', deleteLi);
    newLi.appendChild(document.createTextNode(newText.value + ' '));
    newText.value = '';
    newLi.appendChild(span)
    list.appendChild(newLi);

    function deleteLi() {
        list.removeChild(this.parentNode.parentNode);
    }
}