// Add, get, delete, update, count
let Repository = result;
let repo;
let props = {
    name: "string",
    age: "number"
};
expect(() => repo = new Repository(props), "Instance creation failed, make sure you have submitted a class").to.not.throw();

let e1 = {
    name: "Pesho",
    age: 21
};
repo.add(e1);
expect(repo.count).to.equal(1, "Count value not correct");
expect(repo.add(e1)).to.equal(1, "Did not return correct ID when adding");
repo.update(1, {name: "Gosho", age: 20});
expect(repo.get(1).name).to.equal("Gosho", "Did not update correctly");
expect(repo.count).to.equal(2, "Count value not correct");
repo.del(0);
expect(repo.count).to.equal(1, "Count value not correct");