// Check property names
let Repository = result;

expect(typeof Repository).to.equal('function', "You must submit a class");

let repo;

expect(() => repo = new Repository(), "Instance creation failed, make sure you have submitted a class").to.not.throw();

expect(repo.data instanceof Map).to.equal(true, "Data must be a Map");
expect(typeof repo.add).to.equal('function', "Function 'add' not found");
expect(typeof repo.get).to.equal('function', "Function 'get' not found");
expect(typeof repo.update).to.equal('function', "Function 'update' not found");
expect(typeof repo.del).to.equal('function', "Function 'del' not found");
expect(typeof repo.count).to.equal('number', "Getter 'count' not found");