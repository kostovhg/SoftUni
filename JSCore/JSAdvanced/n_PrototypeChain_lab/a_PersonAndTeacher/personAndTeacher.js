function personAndTeacher(){

    class Person {
        constructor(name, email){
            this._name = name;
            this._email = email;
        }


        get name() {
            return this._name;
        }

        set name(value) {
            this._name = value;
        }

        get email() {
            return this._email;
        }

        set email(value) {
            this._email = value;
        }
    }

    class Teacher extends Person {
        constructor(name, email, subject){
            super(name, email);
            this._subject = subject;
        }


        get subject() {
            return this._subject;
        }

        set subject(value) {
            this._subject = value;
        }
    }

    return { Person, Teacher }
};

let classes = personAndTeacher();
let Teacher = classes.Teacher;
let Person = classes.Person;

let aPerson = new Person('Gosho', 'email@dom.com');
let aTeacher = new Teacher('Pesho', 'myemail@dom.com', 'Programming basics');
let p = new Person('Maria', 'maria@gmail.com');
console.log('Person: ' + p.name + '(' + p.email + ')');
let t = new Teacher('Ivan', 'iv@yahoo.com', 'PHP');
console.log('Teacher: ' + t.name + '(' + t.email + '), teaches ' + t.subject);

console.log(aPerson);
console.log(aTeacher);
