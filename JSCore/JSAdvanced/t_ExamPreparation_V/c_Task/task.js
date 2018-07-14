class Task {

    constructor(title, deadline) {
        this.deadline = deadline;
        this.title = title;
        this.status = 'Open';
    }

    set deadline(deadline) {
        if (deadline < Date.now()) {
            throw new Error('Deadline is in the past')
        }
        this._deadline = deadline;
    }

    get deadline() {
        return this._deadline;
    }

    get isOverdue() {
        return this.status !== "Complete" && this.deadline < Date.now();
    }

    static comparator(a, b) {
        return (
            ((a.isOverdue ? 0 : Task.statuses(a.status).weight) -
                (b.isOverdue ? 0 : Task.statuses(b.status).weight)) ||
            a.deadline - b.deadline)
    }

    toString() {
        return `[${Task.statuses(this.isOverdue? '' : this.status).icon}] ${this.title} ` +
            `${this.status === 'Complete' ? '' : (this.isOverdue ? '(overdue)' : '(deadline: ' + this.deadline + ')')}`;
    }

    static statuses(stat) {
        switch (stat){
            case 'Open': return {icon: "\u2731", weight: 2};
            case 'In Progress': return {icon: "\u219D", weight: 1};
            case 'Complete': return {icon: "\u2714", weight: 3};
            default: return {icon: "\u26A0", weight: 4};
            /*'Overdue': {icon: "\u26A0", weight: 4},*/
        }
    }
}

let date1 = new Date();
date1.setDate(date1.getDate() + 7); // Set date 7 days from now
let task1 = new Task('JS Homework', date1);
console.log(`Task1...........`);
console.log(task1);
let date2 = new Date();
date2.setFullYear(date2.getFullYear() + 1); // Set date 1 year from now
let task2 = new Task('Start career', date2);
console.log(`Task2...........`);
console.log(task2);
console.log(`Tasks 1 and 2 ...........`);
console.log(task1 + '\n' + task2);
let date3 = new Date();
date3.setDate(date3.getDate() + 3); // Set date 3 days from now
let task3 = new Task('football', date3);
console.log(`Task 3...........`);
console.log(task3);
// Create two tasks with deadline set to current time
let task4 = new Task('Task 4', new Date());
console.log(`Task 4 ...........`);
console.log(task4);
let task5 = new Task('Task 5', new Date());
console.log(`Task 5...........`);
console.log(task5);
task1.status = 'In Progress';
console.log(`Task 1 ...........`);
console.log(task1);
task3.status = 'In Progress';
console.log(`Task 3...........`);
console.log(task3);
task5.status = "Complete";
console.log(`Task 5...........`);
console.log(task5);
let tasks = [task1, task2, task3, task4, task5];
task1.status = 'In Progress';
console.log(`Task 1 ...........`);
console.log(task1);
setTimeout(() => {
    tasks.sort(Task.comparator);
    console.log(tasks.join('\n'));
}, 1000); // Sort and print one second later
task1.status = 'In Progress';
console.log(`Task 1 ...........`);
console.log(task1);
// should throw an Error
try {
    let overdueTask = new Task('Overdue Task', new Date(2005, '4', '20'));
} catch (e) {
    console.log(e.message);
}
// should throw an Error
try {
    task1.deadline = new Date(2005, '4', '20');
} catch (e) {
    console.log(e.message);
}
