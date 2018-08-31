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
                },
                {
                    name: "Elemental",
                    power: "Power: 3500",
                    ultimate: "Ultimate: Tunder Storm",
                    region: "Region: Tunders",
                    image: "air-elemental.png"
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
                },
                {
                    name: "Diablo",
                    power: "Power: ~",
                    ultimate: "Ultimate: Blast Wave",
                    region: "Region: Molten Core",
                    image: "diablo.jpg"
                }
            ]
        }
    }
};

function renderAllElements(elements) {
    for (let key of Object.keys(elements.elements)) {
        console.log(key);
    }
}
function renderSingleElement(element){
    let el = elements.elements[element];
    let result = [el.name];
    result.push('Creatures:');
    for (let creature of el['creatures']) {
        result.push(`<--${creature.name}-->`)
    }
    console.log(result.join('\n'));
}
function renderSingleCreature(creatures, creatureName){
    let creature = creatures.find(c => c.name=== creatureName);
    let result = [creatureName];
    result.push(creature.power);
    result.push(creature.ultimate);
    result.push(creature.region);
    console.log(result.join('\n'));
}

renderAllElements(elements);
renderSingleElement('air');
renderSingleCreature(elements.elements.air.creatures, "Archangel");
