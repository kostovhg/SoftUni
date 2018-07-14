// Independent repos
let Repository = result;
let repo1 = new Repository(props = {color: "string", length: "number"});
let repo2 = new Repository(props = {name: "string", counter: "number", someArr: "object"});
let id1 = repo1.add({color: 'yellow', length: 5});
let id2 = repo2.add({name: "vasil", counter: 3, someArr: [1, 2, 3]});
expect(id1).to.equal(id2, "IDs must increment independently");
repo1.add({color: 'blue', length: 15});
repo1.add({color: 'orange', length: 4});
id1 = repo1.add({color: 'red', length: 32});
expect(id1).to.equal(3, "First ID not incremented correctly");
repo2.add({name: "ivan", counter: 8, someArr: [4, 5, 6]});
id2 = repo2.add({name: "maria", counter: 1, someArr: ['a', 'b']});
expect(id2).to.equal(2, "Second ID not incremented correctly");