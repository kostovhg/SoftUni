// Invalid delete
let Repository = result;
let props = {
    color: "string",
    length: "number"
};
let repo = new Repository(props);
expect(() => repo.del(5)).to.throw();
