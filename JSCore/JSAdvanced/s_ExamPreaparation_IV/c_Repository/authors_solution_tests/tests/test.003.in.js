// Constructor sets initial state to correct values
let Repository = result;
let props = {
    propA: "number",
    propB: "number"
};
let repo = new Repository(props);
expect(repo.data.size).to.equal(0, "Data Map must be empty");
expect(repo.count).to.equal(0, "Starting size must be zero");