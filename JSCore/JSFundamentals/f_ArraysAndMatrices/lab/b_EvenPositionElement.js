function evenPositionElement(input){

    console.log(input.filter((el, i) => i % 2 === 0).join(' '));
}


evenPositionElement(['20', '30', '40']);
evenPositionElement(['5', '10']);