class Repository {

    constructor(prop){
        this.data = new Map();
        this.props = prop;
    }


    validate(entity){
        for (let property of [...Object.getOwnPropertyNames(this.props)]) {
            if(!entity.hasOwnProperty(property)){
                throw new Error(`Property ${property} is missing from the entity!`)
            }
            if(typeof entity[property] !== this.props[property]){
                throw new TypeError(`Property ${property} is of incorrect type!`)
            }
        }
        return true;
    }

    add(entity){
        if(this.validate(entity)){
            this.data.set(this.count, entity);
        }
        return this.count - 1;
    }
    get(id){
        if(!this.data.has(id)){
            throw new Error(`Entity with id: ${id} does not exist!`)
        }
        return this.data.get(id);
    }

    update(id, newEntity){
        this.get(id);
        if(this.validate(newEntity)){
            this.data.set(id, newEntity);
        }
    }

    del(id){
        this.get(id);
        this.data.delete(id);
    }
    get count(){
        return this.data.size;
    }
}

// Initialize props object
let properties = {
    name: "string",
    age: "number",
    birthday: "object"
};
//Initialize the repository.js
let repository = new Repository(properties);
// Add two entities
let entity = {
    name: "Kiril",
    age: 19,
    birthday: new Date(1998, 0, 7)
};
console.log(repository.add(entity)); // Returns 0
console.log(repository.add(entity)); // Returns 1
console.log(repository.get(0));
// {"name":"Kiril","age":19,"birthday":"1998-01-06T22:00:00.000Z"}
console.log(repository.get(1));
// {"name":"Kiril","age":19,"birthday":"1998-01-06T22:00:00.000Z"}
//Update an entity
entity = {
    name: 'Valio',
    age: 19,
    birthday: new Date(1998, 0, 7)
};
repository.update(1, entity);
console.log(repository.get(1));
// {"name":"Valio","age":19,"birthday":"1998-01-06T22:00:00.000Z"}
// Delete an entity
repository.del(0);
console.log(repository.count); // Returns 1
let anotherEntity = {
    name1: 'Nakov',
    age: 26,
    birthday: new Date(1991, 0, 21)
};
try {
    repository.add(anotherEntity); // should throw an Error
} catch (e){
    console.log(e.name)
    console.log(e.message)
}
anotherEntity = {
    name: 'Nakov',
    age: 26,
    birthday: 1991
};

try {
repository.add(anotherEntity); // should throw a TypeError
} catch (e){
    console.log(e.name)
    console.log(e.message)
}

try {
    repository.del(-1); // should throw Error for invalid id
} catch (e){
    console.log(e.name)
    console.log(e.message)
}
