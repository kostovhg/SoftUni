function imperialUnits(inches) {
    let feet = Math.floor(inches / 12);
    inches = inches % 12;
    console.log(`${feet}'-${inches}"`);
}

imperialUnits(36);
imperialUnits(55);
imperialUnits(11);