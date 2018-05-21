function treasureLocator(input) {

    let islands = [
        {name: 'Tuvalu', x1: 1, x2: 3, y1: 1, y2: 3},
        {name: 'Tonga', x1: 0, x2: 2, y1: 6, y2: 8},
        {name: 'Samoa', x1: 6, x2: 7, y1: 3, y2: 6},
        {name: 'Cook', x1: 4, x2: 9, y1: 7, y2: 8},
        {name: 'Tokelau', x1: 8, x2: 9, y1: 0, y2: 1}
    ];

    let points = readPoints();
    let result = [''];

    function readPoints() {
        let p = [/*{x: 0, y: 0, n: 0}*/];
        for (let i = 0; i < input.length; i+=2) {
            p.push({x: input[i], y: input[i + 1], n: i/2 + 1 });
        }
        return p;
    }

    let check = function (island, p){
        if(p.x < island.x1)
            return false;
        if(p.x > island.x2)
            return false;
        if(p.y < island.y1)
            return false;
        return p.y <= island.y2;
    };

    for (let i = 0; i < points.length; i++) {
        let islandName = islands.find(x => check(x, points[i]));
        if(islandName){
            result.push(islandName.name);
        } else {
            result.push(`On the bottom of the ocean`);
        }
    }

    console.log(result.join(`\n`))

}

treasureLocator([4, 2, 1.5, 6.5, 1, 3]);
treasureLocator([6, 4]);
