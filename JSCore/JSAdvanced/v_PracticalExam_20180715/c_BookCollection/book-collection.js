class BookCollection {


    constructor(shelfGenre, room, shelfCapacity) {
        this.room = room;
        this.shelfGenre = shelfGenre;
        this.shelfCapacity = shelfCapacity;
        this.shelf = [];
    }

    set room(room) {
        if (['livingRoom', 'bedRoom', 'closet'].indexOf(room) === -1) {
            throw new Error(`Cannot have book shelf in ${room}`)
             // console.log(`Cannot have book shelf in ${room}`)
             //return `Cannot have book shelf in ${room}`;
        }
        this._room = room;
    }

    get room(){
        return this._room;
    }

    get shelfCondition(){
        return this.shelfCapacity - this.shelf.length;
    }

    addBook(bookName, author, genre) {
        let book = {author, bookName, genre};
        if (this.shelfCapacity === this.shelf.length) {
            //this.shelf[0] = {author: bookAuthor, bookName: bookName, genre: genre}
            this.shelf[0] = book;
        } else {
            //this.shelf.push({author: bookAuthor, bookName: bookName, genre: genre})
            this.shelf.push(book);
        }
        this.shelf.sort((a, b) => a.author.localeCompare(b.author));
        return this;
    }

    throwAwayBook(bookName) {
        this.shelf = this.shelf.filter(x => x.bookName !== bookName);
        // let index = this.shelf.filter((x, i) => x.bookName === bookName).map((x, i) => i)[0];
        // this.shelf.splice(index, 1);
    }

    showBooks(genre) {
        let result = [`Results for search "${genre}":`];
        this.shelf
            .filter(x => x.genre === genre)
            .forEach(x =>
                result.push(`\uD83D\uDCD6 ${x.author} - "${x.bookName}"`));
        return result.join('\n')
    }

    toString() {
        if (this.shelf.length === 0) {
            return "It's an empty shelf";
        } else {
            let result = [`"${this.shelfGenre}" shelf in ${this.room} contains:`]
            this.shelf
                .forEach(x =>
                    result.push(`\uD83D\uDCD6 "${x.bookName}" - ${x.author}`));
            return result.join('\n')
        }
    }
}

let livingRoom = new BookCollection("Programming", "livingRoom", 5)
    .addBook("Introduction to Programming with C#", "Svetlin Nakov")
    .addBook("Introduction to Programming with Java", "Svetlin Nakov")
    .addBook("Programming for .NET Framework", "Svetlin Nakov");
console.log(livingRoom.toString());
try {
    let garden = new BookCollection("Programming", "garden");
} catch (e) {
    console.log(e.message);
}
let bedRoom = new BookCollection('Mixed', 'bedRoom', 5);
bedRoom.addBook("John Adams", "David McCullough", "history");
bedRoom.addBook("The Guns of August", "Cuentos para pensar", "history");
bedRoom.addBook("Atlas of Remote Islands", "Judith Schalansky");
bedRoom.addBook("Paddle-to-the-Sea", "Holling Clancy Holling");
console.log("Shelf's capacity: " + bedRoom.shelfCondition);
console.log(bedRoom.showBooks("history"));
livingRoom.throwAwayBook('Programming for .NET Framework');
console.log(livingRoom.toString());


let lvlRoom = new BookCollection("Programming", "livingRoom", 4)
    .addBook("Introduction to Programming with C#", "Svetlin Nakov")
    .addBook("Introduction to Programming with Java", "Svetlin Nakov")
    .addBook("Programming for .NET Framework", "Svetlin Nakov");

lvlRoom.addBook('TempBook','Gosho');
lvlRoom.addBook('Peters book', 'Peter');
console.log(lvlRoom.toString());
console.log(lvlRoom.shelfCondition);