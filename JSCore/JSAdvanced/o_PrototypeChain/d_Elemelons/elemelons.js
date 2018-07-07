function elemelons() {

    class Melon {
        constructor(weight, melonSort) {
            if (new.target === Melon) {
                throw new TypeError('Abstract class cannot be instantiated directly')
            }
            this.weight = weight;
            this.melonSort = melonSort;
            this._elementIndex = this.weight * melonSort.length;
        }

        get elementIndex() {
            return this._elementIndex;
        }

        toString() {
            return `Element: ${(this.constructor.name).replace('melon', '')}\n` +
                `Sort: ${this.melonSort}\n` +
                `Element Index: ${this.elementIndex}`;
        }
    }

    class Watermelon extends Melon {
        constructor(weight, melonSort) {
            super(weight, melonSort);
        }
    }

    class Firemelon extends Melon {
        constructor(weight, melonSort) {
            super(weight, melonSort);
        }
    }

    class Earthmelon extends Melon {
        constructor(weight, melonSort) {
            super(weight, melonSort);
        }
    }

    class Airmelon extends Melon {
        constructor(weight, melonSort) {
            super(weight, melonSort);
        }
    }

    class Melolemonmelon extends Watermelon {
        constructor(weight, melonSort) {
            super(weight, melonSort);
            this.elements = ['Water', 'Fire', 'Earth', 'Air'];
        }

        morph() {
           this.elements.push(this.elements.shift())
        }
        toString() {
            // return `Element: ${this.elements[0]}\n` +
            //     `Sort: ${this.melonSort}\n` +
            //     `Element Index: ${this.elementIndex}`;
            return super.toString().replace(/^Element: .*$/gm, 'Element: ' + this.elements[0])
        }
    }

    return {
        Melon: Melon,
        Watermelon: Watermelon,
        Firemelon: Firemelon,
        Earthmelon: Earthmelon,
        Airmelon: Airmelon,
        Melolemonmelon: Melolemonmelon
    }
}


let elements = ['Water', 'Fire', 'Earth', 'Air', 'Melolemon'];
let classes = elemelons();
let melons = {};
for (let element of elements) {
    melons[element + 'melon'] = new classes[element + 'melon'](Math.random() * 25, 'MelonSort')
}

for (let key of Object.getOwnPropertyNames(melons)){
    console.log(melons[key].toString())
}
let melolemonmelon = melons['Melolemonmelon'];
melolemonmelon.morph();
console.log(melolemonmelon.toString());
melolemonmelon.morph();
console.log(melolemonmelon.toString());
melolemonmelon.morph();
console.log(melolemonmelon.toString());