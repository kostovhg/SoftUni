// Invalid delete
let Repository = result;
let props = {
    color: "string",
    length: "number"
};
let repo = new Repository(props);
expect(() => repo.get(5)).to.throw();
