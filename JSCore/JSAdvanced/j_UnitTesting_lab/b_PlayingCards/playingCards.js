function playingCards(card, suit){
    let VALID_CARDS = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A'];
    let VALID_SUITS = {'S': '\u2660', 'H': '\u2665',  'D': '\u2666', 'C':'\u2663'};

    if (VALID_CARDS.indexOf(card) < 0 ){
        throw new Error(`Invalid card face: ${card}`)
        //console.log('invalid')
    }

    if (!VALID_SUITS.hasOwnProperty(suit)){
        throw new Error(`Invalid card suit: ${suit}`)
        //console.log('invalid')
    }
    return {value: card, suit: VALID_SUITS[suit], toString: function () {
            return this.value + this.suit;
        }}
}

console.log('' + playingCards('A', 'S'));
console.log('' + playingCards('5', 'H'));
// console.log('' + playingCards('1', 'H'));
console.log('' + playingCards('10', 'P'));
