// Valid del
let Repository = result;
let props = {
    propA: "number",
    propB: "number"
};
let repo = new Repository(props);
let obj = {propA: 10, propB: 11};
repo.add(obj);
expect(repo.data.size).to.equal(1, "Entity not added");
let id = [...repo.data.keys()].reduce((k, n) => n);
repo.del(id);
expect(repo.data.size).to.equal(0, "Entity not deleted");