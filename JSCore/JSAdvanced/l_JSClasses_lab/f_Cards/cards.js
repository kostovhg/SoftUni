let result = (function () {
    let Suits = {
        CLUBS: "\u2663",    // ♣
        DIAMONDS: "\u2666", // ♦
        HEARTS: "\u2665",   // ♥
        SPADES: "\u2660"    // ♠
    };
    let Faces = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A'];

    class Card {
        constructor(face, suit) {
            [this.face, this.suit] = [face, suit];
        }

        get face() {
            return this._face;
        }

        set face(f) {
            if (!Faces.includes(f)) {
                throw new Error("Invalid card face: " + f);
            }
            this._face = f;
        }

        get suit() {
            return this._suit;
        }

        set suit(s) {
            if (!Object.keys(Suits).map(k => Suits[k]).includes(s))
                throw new Error("Invalid card suite: " + s);
            this._suit = s;
        }

        toString() {
            return `${this._face}${this._suit}`;
        }
    }

    return {Suits, Card}
}());


let Card = result.Card;
let Suits = result.Suits;

let card = new Card("Q", Suits.CLUBS);
console.log(card.toString());
card.face = "A";
card.suit = Suits.DIAMONDS;
console.log(card.toString());
try {
    let card1 = new Card('1', Suits.HEARTS);
    console.log(card1);
} catch (e) {
    console.log(e.message);
}

try {
    let card2 = new Card("1", Suits.CLUBS);
    console.log(card2);
} catch (e) {
    console.log(e.message);
}
try {
    let card3 = new Card("2", Suits.Pesho);
    console.log(card3);
} catch (e) {
    console.log(e.message);
}
try {
    let card4 = new Card("3", 'hearts');
    console.log(card4);
} catch (e) {
    console.log(e.message);
}