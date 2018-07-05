class Rat {

    constructor(name) {
        this.name = name;
        this._unitedRats = [];
    }

    unite(otherRat) {
        if(otherRat instanceof Rat){
            this._unitedRats.push(otherRat);
        }
    }

    getRats() {
        return this._unitedRats;
    }

    toString() {
        return this.name + this._unitedRats.map(r => `\n##${r}`).join('');
    }

}

let test = new Rat("Pesho");

console.log(typeof test);
console.log(test.toString()); //Pesho

console.log(test.getRats()); //[]

test.unite(new Rat("Gosho"));
test.unite(new Rat("Sasho"));
console.log(test.getRats());
//[ Rat { name: 'Gosho', unitedRats: [] },
//  Rat { name: 'Sasho', unitedRats: [] } ]

console.log(test.toString());
// Pesho
// ##Gosho
// ##Sasho

let r = Rat;
let rat2 = new r("Viktor");
let rat3 = new r("Vichi");
let rat4 = "fake rat";

rat2.unite(rat4);
console.log(rat2);