// ID incremented only on success
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
try {
    repo.add({propA: 'orange', length: 3});
} catch (err) {}
id = repo.add({color: 'magenta', length: 2});
expect(id).to.equal(2, "ID must increment only on success");