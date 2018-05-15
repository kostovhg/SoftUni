function filterByAge(minAge, firstName, firstAge, secondName, secondAge) {
    let persons = [{name: firstName, age: firstAge}, {name: secondName, age: secondAge}];
    persons.forEach(p => {
        if(p.age >= minAge) {
            console.log(p);
        }
    })
}

filterByAge(12, 'Ivan', 15, 'Asen', 9);