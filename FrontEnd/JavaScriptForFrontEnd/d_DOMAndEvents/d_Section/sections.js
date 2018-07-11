function create(sentences) {
    let wrapper = document.getElementById('content');
    for (let sentence of sentences) {
        let div = document.createElement('div');
        div.innerHTML=`<p style="display: none">${sentence}</p>`;
        div.addEventListener('click', (e) => {
            e.target.firstChild.style.display= 'block';
        });
        wrapper.appendChild(div);
    }
}