// Is a valid constructor
let Repository = result;
let repo;
let props = {
    propA: "number",
    propB: "number"
};
expect(() => repo = new Repository(props), "Instance creation failed, make sure you have submitted a class").to.not.throw();