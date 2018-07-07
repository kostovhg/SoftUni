class CheckingAccount {

    constructor(clientId, email, firstName, lastName) {
        this.clientId = clientId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this._products = [];
    }


    get clientId() {
        return this._clientId;
    }

    set clientId(value) {
        if (!/^\d{6}$/g.test(value)) {
            throw new TypeError(`Client ID must be a 6-digit number`)
        }
        this._clientId = value;
    }

    get email() {
        return this._email;
    }

    set email(value) {
        if (!/^[a-zA-Z\d]+\@[a-zA-Z.]+$/g.test(value)) {
            throw new TypeError(`Invalid e-mail`)
        }
        this._email = value;
    }

    get firstName() {
        return this._firstName;
    }

    set firstName(value) {
        if (!/^[a-zA-Z]{3,20}$/.test(value)) {
            throw new TypeError(
                (value.length > 2 && value.length < 21) ?
                    `First name must contain only Latin characters` :
                    `First name must be between 3 and 20 characters long`)
        }
        this._firstName = value;
    }

    get lastName() {
        return this._lastName;
    }

    set lastName(value) {
        if (!/^[a-zA-Z]{3,20}$/.test(value)) {
            throw new TypeError(
                (value.length > 2 && value.length < 21) ?
                    `Last name must contain only Latin characters` :
                    `Last name must be between 3 and 20 characters long`)
        }
        this._lastName = value;
    }

    get products() {
        return this._products;
    }
}


try {
    let acc1 = new CheckingAccount('1314', 'ivan@some.com', 'Ivan', 'Petrov');
//TypeError: Client ID must be a 6-digit number
} catch (e) {
    console.log(e.message);
}

try {
    let acc2 = new CheckingAccount('131455', 'ivan@', 'Ivan', 'Petrov');
//TypeError: Invalid e-mail
} catch (e) {
    console.log(e.message);
}

try {
    let acc3 = new CheckingAccount('131455', 'ivan@some.com', 'I', 'Petrov');
// TypeError: First name must be between 3 and 20 characters long
} catch (e) {
    console.log(e.message);
}

try {
    let acc4 = new CheckingAccount('131455', 'ivan@some.com', 'Ivаn', 'P3trov');
// TypeError: "First name must contain only Latin characters
} catch (e) {
    console.log(e.message);
}
