function showText() {
    let [link, text] = [
        document.getElementById('more'),
        document.getElementById('text')
    ];
    text.addEventListener('click', showText);
    if(link.style.display === 'none'){
        link.style.display = 'inline';
        text.style.display = 'none';
    } else {
        link.style.display = 'none';
        text.style.display = 'inline';
    }
}