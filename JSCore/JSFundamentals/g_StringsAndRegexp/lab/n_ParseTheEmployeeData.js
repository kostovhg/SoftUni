function parseTheEmployeeData(input) {
    let valid =  /^([A-Z][a-zA-Z]*) - ([1-9][0-9]*) - ([a-zA-Z0-9 -]+)$/;
    let persons = [], match;

    class Person {
        constructor(name, position, salary){
            this.name = name;
            this.position = position;
            this.salary = salary;
        }

        toString() {
            return `Name: ${this.name}\n` +
                `Position: ${this.position}\n` +
                `Salary: ${this.salary}`;
        }
    }

    for (let el of input) {
        if(match = valid.exec(el)){
            persons.push(new Person(match[1], match[3], match[2]));
        }
    }

    persons.forEach(p => console.log(p.toString()));
}

parseTheEmployeeData([
    'Isacc - 1000 - CEO',
    'Ivan - 500 - Employee',
    'Peter - 500 - Employee']);
parseTheEmployeeData([
    'Jonathan - 2000 - Manager',
    'Peter- 1000- Chuck',
    'George - 1000 - Team Leader']);