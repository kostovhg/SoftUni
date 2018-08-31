let elements = {
    "elements": {
        "air": {
            name: "Air",
            creatures: [
                {
                    name: "Archangel",
                    power: "Power: 2000",
                    ultimate: "Ultimate: Wind Justice",
                    region: "Region: Heaven's Kingdom",
                    image: "archangel.jpg"
                }
            ]
        },
        "fire": {
            name: "Fire",
            creatures: [
                {
                    name: "Fire Guardian",
                    power: "Power: 1000",
                    ultimate: "Ultimate: Fire Ball",
                    region: "Region: Dark Dungeon",
                    image: "fire-guardian.jpg"
                }
            ]
        },
        "water": {
            name: "Water",
            creatures: [
                {
                    name: "Mermaid",
                    power: "Power: 675",
                    ultimate: "Ultimate: Allure",
                    region: "Region: Ocean Abbys",
                    image: "mermaid.jpg"
                }
            ]
        },
        "earth": {
            name: "Earth",
            creatures: [
                {
                    name: "Forest God",
                    power: "Power: 5000",
                    ultimate: "Ultimate: Nature's Grasp",
                    region: "Region: Cursed Forest",
                    image: "forest-god.jpg"
                }
            ]
        }
    }

};

function renderAllElementsInHTML(elements) {
    let el = elements.elements;
    let elArr = Object.keys(el);
    let counters = ['first', 'second', 'third', 'fourth'];
    let elementContainer = $('#elementsContainer>.container');
    for (let i = 0; i < elArr.length; i++) {
        if (i === 0 || i === elArr.length / 2) {
            elementContainer.append($('<div class="containers">'))
        }
        elementContainer.find('.containers:last').append($(`<div id="${elArr[i]}" class="${counters[i]} clickable">`))
    }
}

function backToElements() {
    $('#elementInfo').hide();
    $('#elementsContainer').show();
}

function renderSingleElement(elementObj) {
    let creatures = $('<ul class="creatures">');
    for (let creature of elementObj.creatures) {
        creatures.append($('<li>')
            .append($('<input class="radio-button" name="selector" type="radio">')
                .attr('value', creature.name))
            .append($('<label>').text(creature.name)))
    }
    creatures.find('input:first').attr('checked', true);
    let firstCreature = elementObj.creatures[0];
    let singleElement = $('<div id="elementInfo">')
        .append($('<h1 id="elementInfoTitle">').text(elementObj.name))
        .append($('<div class="back-button">')
            .append($('<button id="backToElements">')
                .text('Back to Elements')
                .on('click', backToElements)))
        .append($('<section class="second-layout">')
            .append($('<div class="first-after-click">')
                .append(creatures))
            .append($('<div class="center-after-click">')
                .append($(`<img id="creature-image" src="images/${firstCreature.image}" alt="">`)))
            .append($('<div class="right-after-click">')
                .append($('<p id="creature-name">').text(firstCreature.name))
                .append($('<p id="creature-power">').text(firstCreature.power))
                .append($('<p id="creature-ultimate">').text(firstCreature.ultimate))
                .append($('<p id="creature-region">').text(firstCreature.region))));
    $('main').append(singleElement);
}

renderAllElementsInHTML(elements);
renderSingleElement(elements.elements.air);