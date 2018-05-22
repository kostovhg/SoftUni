function roadRadar(input) {

    let [speed, zone] = [input[0], input[1]];

    function getLimit(zone){
        switch (zone){
            case 'motorway': return 130;
            case 'interstate': return 90;
            case 'city': return 50;
            case 'residential': return 20;
        }
    }

    let limit = getLimit(zone);

    function getInfraction(speed, limit){
        let overSpeed = speed - limit;
        if(overSpeed <= 0){
            return false;
        } else {
            if(overSpeed > 40) return 'reckless driving';
            else if (overSpeed > 20) return 'excessive speeding';
            else return 'speeding';
        }
    }

    let res = getInfraction(speed, limit);
    if (res) console.log(res);
}


roadRadar([40, 'city']);
roadRadar([21, 'residential']);
roadRadar([120, 'interstate']);
roadRadar([200, 'motorway']);
