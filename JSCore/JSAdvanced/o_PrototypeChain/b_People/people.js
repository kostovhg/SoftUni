function people() {

    class Employee {

        constructor(name, age) {
            if (new.target === Employee) {
                throw new TypeError('Employee class is abstract and can not be initialized');
            }
            this.name = name;
            this.age = age;
            this.salary = 0;
            this.tasks = [];
        }

        work() {
            let currentTask = this.tasks.shift();
            console.log(`${this.name}${currentTask}`);
            this.tasks.push(currentTask);
        }

        getSalary(){
            return this.salary;
        }

        collectSalary() {
            console.log(`${this.name} received ${this.getSalary()} this month.`)
        }
    }

    class Junior extends Employee {
        constructor(name, age){
            super(name, age);
            this.tasks.push(` is working on a simple task.`);
        }
    }

    class Senior extends Employee {
        constructor(name, age){
            super(name, age);
            this.tasks.push(
                ` is working on a complicated task.`,
                ` is taking time off work.`,
                ` is supervising junior workers.`);
        }
    }

    class Manager extends Employee {
        constructor(name, age){
            super(name, age);
            this.dividend = 0;
            this.tasks.push(` scheduled a meeting.`, ` is preparing a quarterly report.`);
        }

        getSalary(){
            return this.salary + this.dividend;
        }
    }

    return {Employee, Junior, Senior, Manager}
}

let result = people();

var guy1 = new result.Junior('pesho', 20);
var guy2 = new result.Senior('gosho', 21);
var guy3 = new result.Manager('ivan', 22);
guy1.work();
guy2.work();
guy2.work();