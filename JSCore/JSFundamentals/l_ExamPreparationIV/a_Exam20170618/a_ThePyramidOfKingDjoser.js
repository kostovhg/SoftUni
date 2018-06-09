function thePyramidOfKingDjoser(base, increment) {

    let [stone, marble, lapis, gold] = [0, 0, 0, Math.ceil(((base % 2) ? increment : 4 * increment))];
    let [inside, outside, steps] = [0, 0, ~~((base + 1) / 2)];

    for (let step = 1; step < steps; step++, base -= 2) {
        inside = (base - 2) ** 2;
        outside = (base ** 2) - inside;
        stone += inside * increment;
        !(step % 5) ? lapis += outside * increment : marble += outside * increment;
    }

    console.log(`Stone required: ${Math.ceil(stone)}\n` +
        `Marble required: ${Math.ceil(marble)}\n` +
        `Lapis Lazuli required: ${Math.ceil(lapis)}\n` +
        `Gold required: ${Math.ceil(gold)}\n` +
        `Final pyramid height: ${~~(steps * increment)}`);

}


thePyramidOfKingDjoser(11, 1);

thePyramidOfKingDjoser(11, 0.75);

thePyramidOfKingDjoser(12, 1);

thePyramidOfKingDjoser(23, 0.5);