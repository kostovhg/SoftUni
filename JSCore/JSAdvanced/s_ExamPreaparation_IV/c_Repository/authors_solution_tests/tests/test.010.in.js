// Invalid update
let Repository = result;
let props = {
    color: "string",
    length: "number"
};
let repo = new Repository(props);
repo.add({color: "blue", length: 15});
let id = [...repo.data.keys()].reduce((k, n) => n);
expect(() => repo.update({propA: 10, propB: 11})).to.throw();
expect(() => repo.update({color: 23, length: 21})).to.throw();
expect(() => repo.update({color: "blue", length: "15"})).to.throw();