function notify(message) {
    let theDiv = document.getElementById('notification');
    if (!theDiv) {
        theDiv = document.createElement('div');
        theDiv.id = 'notification';
        theDiv.style.display = 'none';
        document.getElementById('container').appendChild(theDiv);
    }
    theDiv.style.display = 'block';
    theDiv.textContent = message;
    setTimeout(function () {
        theDiv.style.display = 'none';
    }, 2000)
}