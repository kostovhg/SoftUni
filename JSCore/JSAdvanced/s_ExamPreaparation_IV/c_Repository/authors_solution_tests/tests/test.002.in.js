// Contains all properties, functions attached to prototype
let Repository = result;
let props = {
    propA: "number",
    propB: "number"
};
let repo = new Repository(props);
expect(repo.data).to.not.equal(undefined, "Property 'data' not found");
expect(repo.count).to.not.equal(undefined, "Accessor 'count' not found");
expect(Object.getPrototypeOf(repo).hasOwnProperty('add')).to.equal(true, "Function 'add' not found on prototype");
expect(Object.getPrototypeOf(repo).hasOwnProperty('get')).to.equal(true, "Function 'get' not found on prototype");
expect(Object.getPrototypeOf(repo).hasOwnProperty('update')).to.equal(true, "Function 'update' not found on prototype");
expect(Object.getPrototypeOf(repo).hasOwnProperty('del')).to.equal(true, "Function 'del' not found on prototype");