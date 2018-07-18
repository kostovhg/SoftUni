function attachEvents() {
    let [selected, background, items] = [
        'data-selected',
        'background-color',
        $('#items li')
    ];

    items.on('click', (e) => {
        let t = $(e.target);
        (t.attr(selected)) ?
            t.css(background,'').removeAttr(selected) :
            t.css(background,'#DDD').attr(selected, true)
    });

    $('#showTownsButton').on('click', () => {
        $('#selectedTowns').text([...items]
            .filter(x => $(x).attr(selected))
            .map(x => $(x).text())
            .join(', '))
    })
}