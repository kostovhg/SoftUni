// Invalid add
let Repository = result;
let props = {
    color: "string",
    length: "number"
};
let repo = new Repository(props);
expect(() => repo.add({propA: 10, propB: 11})).to.throw();
expect(() => repo.add({color: 23, length: 21})).to.throw();
expect(() => repo.add({color: "blue", length: "15"})).to.throw();