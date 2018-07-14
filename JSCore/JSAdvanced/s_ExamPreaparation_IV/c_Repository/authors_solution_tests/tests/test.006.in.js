// Valid update
let Repository = result;
let props = {
    propA: "number",
    propB: "number"
};
let repo = new Repository(props);
let obj = {propA: 10, propB: 11};
repo.add(obj);
let id = [...repo.data.keys()].reduce((k, n) => n);
let updated = {propA: 5, propB: 13};
repo.update(id, updated);
expect(repo.get(id)).to.equal(updated, "Entity not updated");