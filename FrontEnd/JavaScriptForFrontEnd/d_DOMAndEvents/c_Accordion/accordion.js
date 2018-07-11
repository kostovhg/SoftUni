function toggle() {
    let [btn, extra] = [
        document.getElementsByClassName('button')[0],
        document.getElementById('extra')
    ];

    console.log(extra.getAttribute('display'));
    if (btn.textContent === 'Less') {
        btn.textContent = 'More';
        extra.setAttribute('style', 'display: none');
    } else {
        btn.textContent = 'Less';
        extra.setAttribute('style', 'display: block');
    }
}