// Valid add
let Repository = result;
let props = {
    propA: "number",
    propB: "number"
};
let repo = new Repository(props);
repo.add({propA: 10, propB: 11});
expect(repo.data.size).to.equal(1, "Entity not added");