function inheriting_toString(){
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

        toString(){
            let className = this.constructor.name;
            return `${className} (name: ${this.name}, email: ${this.email})`
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


        toString() {
            return super.toString().slice(0,-1) + `, subject: ${this.subject})`
        }
    }

    class Student extends Person {
        constructor(name, email, course){
            super(name, email);
            this._course = course;
        }

        get course() {
            return this._course;
        }

        set course(value) {
            this._course = value;
        }

        toString() {
            return super.toString().slice(0,-1) + `, course: ${this.course})`
        }
    }

    return { Person, Teacher, Student }
};

// Tests
let classes = inheriting_toString();
let [Person, Teacher, Student] = [classes.Person, classes.Teacher, classes.Student];

let p = new Person('Maria', 'maria@gmail.com');
// console.log('Person: ' + p.name + '(' + p.email + ')');
let t = new Teacher('Ivan', 'iv@yahoo.com', 'PHP');
// console.log('Teacher: ' + t.name + '(' + t.email + '), teaches ' + t.subject);
let s = new Student("Ana", "ana@mail.ru", 3);
console.log(''+p);
console.log(''+t);
console.log(''+s);
