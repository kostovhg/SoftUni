// ID returned correctly
let Repository = result;
let props = {
    color: "string",
    length: "number"
};
let repo = new Repository(props);
let id = repo.add({color: 'red', length: 1});
expect(id).to.equal(0, "ID must start at zero");
id = repo.add({color: 'orange', length: 3});
expect(id).to.equal(1, "ID must increment");