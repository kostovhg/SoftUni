function dnaHelix(n) {

    let bases = [
        ['A', 'T'],
        ['C', 'G'],
        ['T', 'T'],
        ['A', 'G'],
        ['G', 'G']
    ];

    let getBase = (function () {
        let cBase = -1;
        return function () {
            cBase++;
            return cBase % (bases.length);
        }
    })();

    let getCurrent = (function () {
        return (function () {
            let cPos = 1;
            let cDir = -1;
            return function () {
                if(cPos ===2 || cPos === 0) cDir *= -1;
                cPos += cDir;
                let cBase = getBase();
                console.log(getMessage(cPos, cBase).join(''));
            }
        })();
    }());

    function getStars(cPos) {
        return '*'.repeat(2 - cPos);
    }

    function getMiddle(cPos) {
        return '-'.repeat(2 * cPos);
    }

    function getMessage(cPos, cBase) {
        return [getStars(cPos), bases[cBase][0], getMiddle(cPos), bases[cBase][1], getStars(cPos)];
    }

    for (let i = 0; i < n; i++) {
        getCurrent();
    }
}

//dnaHelix(4);
dnaHelix(10);