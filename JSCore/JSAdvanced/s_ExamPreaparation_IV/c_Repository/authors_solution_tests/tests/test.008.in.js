// Count
let Repository = result;
let props = {
    propA: "number",
    propB: "number"
};
let repo = new Repository(props);
let obj = {propA: 10, propB: 11};
repo.add(obj);
expect(repo.count).to.equal(1, "Incorrect count");
repo.add(obj);
expect(repo.count).to.equal(2, "Incorrect count");
repo.add({propA: 22, propB: 3});
expect(repo.count).to.equal(3, "Incorrect count");
let id = [...repo.data.keys()].reduce((k, n) => n);
repo.del(id);
expect(repo.count).to.equal(2, "Incorrect count after deletion");