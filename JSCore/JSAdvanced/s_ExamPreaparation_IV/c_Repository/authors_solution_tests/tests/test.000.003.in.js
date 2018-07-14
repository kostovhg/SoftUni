// Invalid add
let Repository = result;
let repo;
let props = {
    name: "string",
    age: "number"
};
expect(() => repo = new Repository(props), "Instance creation failed, make sure you have submitted a class").to.not.throw();

let e1 = {
    name: "Pesho",
    bday: new Date(1996, 5, 20)
};
expect(() => repo.add(e1), "Invalid entity was added").to.throw();