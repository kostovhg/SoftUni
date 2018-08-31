/**
 * Fetch JSON object from firebase.
 * @param {*} element - string. If null, return only elementsContainer names JSON.
 * @param {*} creatureIndex - integer. If null return only
 */
function fetchData(element, creatureIndex) {

    let url = `https://js-retake.firebaseio.com/elements`;
    let level = arguments.length;
    if (level > 0 && /fire|air|water|earth/.test(element)) {
        url += `/${element}`;
        if (level > 1 && creatureIndex === parseInt(creatureIndex, 10)) {
            url += `/creatures/${creatureIndex}`;
        }
    }
    url += `.json`;
    return $.get(url, function (r) {
        // return r["level"] = level;
        if (level === 0) {
            for (let key of Object.keys(r)) {
                r[key] = key;
            }
        }
        return r;
    });
}
// existing DOM elements selectors
let [
    elementsContainer, elementInfo,
    firstRow, secondRow,
    elementTitle, backBtn,
    elementSection,
    elementLeft, creaturesList,
    elementCenter, elementRight
] = [
    $('#elementsContainer'), $('#elementInfo'),
    $('.container>:first'), $('.container>:last'),
    $('#elementInfoTitle'), $('#backToElements'),
    $('#elementInfo>section-layout'),
    $('.first-after-click'), $('.creatures'),
    $('.center-after-click'), $('#right-after-click'),
];

// Not initially existing DOM elements 


backBtn.on('click', switchToElements);
elementRight.addClass('right-after-click');

function switchToCreature() {
    elementsContainer.hide();
    elementInfo.show();
}

function switchToElements() {
    clearCreature();
    creaturesList.empty();
    elementInfo.hide();
    elementsContainer.show();
}

function clearCreature() {
    elementCenter.empty();
    elementRight.empty();
}

function renderAllElementsInHTML() {

    fetchData()
        .done(function (res) {
            let counters = ['first', 'second', 'third', 'fourth'];
            let elementsNames = ['air', 'fire', 'water', 'earth'];

            for (let i = 0; i < counters.length; i++) {
                let element = $(`<div id="${res[elementsNames[i]]}" class="${counters[i]} clickable">`);
                element.on('click', renderSingleElement);
                element.appendTo((i < counters.length / 2) ? firstRow : secondRow);
            }
        })
        .fail(console.log);
}


function renderCreature(e) {
    let [creatureImg, creatureProperties] = [
        $('#creature-image'), [
            'name',
            'power',
            'ultimate',
            'region'
        ]
    ];
    fetchData(e.data.element, e.data.index)
        .then(function (res) {
            clearCreature();
            let newImg = res.image.replace(' ', '');
            elementCenter.append($(`<img id="creature-image" src="images/${newImg}" alt="">`));
            console.log(res)
            for (let prop of creatureProperties) {
                console.log(prop);
                let par = $('<p>').attr('id', `creature-${prop}`).text(res[prop]);
                console.log(par)
                elementRight
                    .append(par);
            }

        })
        .fail(console.log);

}


function renderSingleElement(e) {
    let element = $(e.target).attr('id');
    fetchData(element)
        .then(function (res) {
            elementTitle.text(res.name);
            // prepare list of radio buttons
            creaturesList.empty();
            res.creatures.forEach((creature, i) => {
                creaturesList
                    .append($(`<li>`)
                        .append($('<input class="radio-button" name="selector" type="radio">')
                            .attr('value', i)
                            .change({
                                element,
                                index: i
                            }, renderCreature))
                        .append($('<label>').text(creature.name)));
            });
            creaturesList.find('input:first').addClass('checked').click();
            switchToCreature();
        })
        .fail(console.log);
}

renderAllElementsInHTML();