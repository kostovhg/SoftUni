// Valid get
let Repository = result;
let props = {
    propA: "number",
    propB: "number"
};
let repo = new Repository(props);
let obj = {propA: 10, propB: 11};
repo.add(obj);
let added = repo.get([...repo.data.keys()].reduce((k, n) => n));
expect(obj).to.equal(added, "Added entity not correct");