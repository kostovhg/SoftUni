class LineManager {

    constructor(stopsArray) {
        this._stops = [];
        this.stop = stopsArray;
        this.currentStop = 0;
        this.duration = 0;
    }

    set stop(arr) {
        if (!Array.isArray(arr) || arr.length === 0) throw new Error('The line manager have to be initialized with array of stops')
        for (let stopInput of arr) {
            if(typeof stopInput.name !== 'string' || typeof stopInput.timeToNext !== 'number' ||
                stopInput.name === '' || stopInput.timeToNext < 0)
                throw new Error('Invalid data');
        }
        this._stops = arr;
    }

    get atDepot() {
        return this.currentStop === this._stops.length - 1;
    }

    get nextStopName() {
        return this.atDepot ? 'At depot' : this._stops[this.currentStop + 1].name;
    }

    get currentDelay() {
        return this.currentStop > 0 ?
            this.duration - this._stops.filter((x, i) => i < this.currentStop).map(s => s.timeToNext).reduce((a, b) => a + b) :
            0;
    }

    arriveAtStop(minutes) {
        if(minutes < 0 || this.atDepot) throw new Error('No more stops')
        this.duration += minutes;
        this.currentStop++;
        return !this.atDepot;
    }

    toString() {
        return `Line summary\n` +
            `- ${this.atDepot ? 'Course completed' : 'Next stop: ' + this.nextStopName}\n` +
            `- Stops covered: ${this.currentStop}\n` +
            `- Time on course: ${this.duration} minutes\n` +
            `- Delay: ${this.currentDelay} minutes`
    }
}

// Initialize a line manager with correct values
let man = new LineManager([
    {name: 'Depot', timeToNext: 4},
    {name: 'Romanian Embassy', timeToNext: 2},
    {name: 'TV Tower', timeToNext: 3},
    {name: 'Interpred', timeToNext: 4},
    {name: 'Dianabad', timeToNext: 2},
    {name: 'Depot', timeToNext: 0},
]);

// Travel through all the stops until the bus is at depot
while (man.atDepot === false) {
    console.log(man.toString());
    man.arriveAtStop(4);
}

console.log(man.toString());

// Should throw an Error (minutes cannot be negative)
try {
    man.arriveAtStop(-4);
} catch (e) {
    console.log(e.message)
}
// Should throw an Error (last stop reached)
try {
    man.arriveAtStop(4);
} catch (e) {
    console.log(e.message)
}

// Should throw an Error at initialization
try {
    const wrong = new LineManager([
        {name: 'Stop', timeToNext: {wrong: 'Should be a number'}}
    ]);
} catch (e) {
    console.log(e.message);
}

/*
Line summary
- Next stop: Romanian Embassy
- Stops covered: 0
- Time on course: 0 minutes
- Delay: 0 minutes
Line summary
- Next stop: TV Tower
- Stops covered: 1
- Time on course: 4 minutes
- Delay: 0 minutes
Line summary
- Next stop: Interpred
- Stops covered: 2
- Time on course: 8 minutes
- Delay: 2 minutes
Line summary
- Next stop: Dianabad
- Stops covered: 3
- Time on course: 12 minutes
- Delay: 3 minutes
Line summary
- Next stop: Depot
- Stops covered: 4
- Time on course: 16 minutes
- Delay: 3 minutes
Line summary
- Course completed
- Stops covered: 5
- Time on course: 20 minutes
- Delay: 5 minutes

 */

// Initialize a line manager with correct values
man = new LineManager([
    {name: 'Depot', timeToNext: 4},
    {name: 'Depot', timeToNext: 0},
]);

// Travel through all the stops until the bus is at depot
while (man.atDepot === false) {
    console.log(man.toString());
    man.arriveAtStop(0);
}
console.log(man.toString());

try {
    man = new LineManager([
        {name: '', timeToNext: 4},
        {name: 'Depot', timeToNext: 0},
    ]);
} catch (e) {
    console.log(e.message)
}
try {
    man = new LineManager([
        {name: 'Depot', timeToNext: 4},
        {name: '', timeToNext: 0},
    ]);

} catch (e) {
    console.log(e.message)
}
try {
    man = new LineManager([
        {name: 'Depot', timeToNext: 4},
        {name: 'Depot', timeToNext: 5},
    ]);
} catch (e) {
    console.log(e.message)
}
try {
    man = new LineManager([
        {name: 'Depot', timeToNext: 4},
        {name: 'Depot', timeToNext: -1},
    ]);
} catch (e) {
    console.log(e.message)
}