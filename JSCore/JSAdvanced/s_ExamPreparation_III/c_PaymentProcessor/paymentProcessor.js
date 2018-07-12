class PaymentProcessor {

    constructor(options) {
        this._payments = new Map(); // let's do it as a map
        this._options = {types: ["service", "product", "other"], precision: 2};
        this.setOptions(options);
    }

    get(id){
        if(!this._payments.has(id)){
            throw new Error('ID does not exist')
        }
        let payment = this._payments.get(id);
        return `Details about payment ID: ${payment.id}\n` +
        `- Name: ${payment.name}\n` +
        `- Type: ${payment.type}\n` +
        `- Value: ${payment.value}`
    }

    registerPayment(id, name, type, value) {
        try {
            this.validate(id, name, type, value);
            this._payments.set(id, ({id, name, type, value: this.formatValue(value)}));
        } catch (e) {
            throw new Error(e);
        }
    }

    formatValue(val){
        return Number(val).toFixed(this._options.precision)
    }

    validate(id, name, type, value) {
        if ( typeof id !== 'string'){
            throw new TypeError('ID should be string')
        };
        if ( id === ''){
            throw new Error('ID can not be empty')
        };
        if ( typeof name !== 'string'){
            throw new TypeError(`Payment\'sname should be string`)
        }
        if ( name === ''){
            throw new Error(`Payment\'s name can not be empty`)
        };
        if (this._options.types.indexOf(type) < 0){
            throw new Error(`Material type: ${type} is not a valid option`)
        }
        if ( typeof value !== 'number'){
            throw new TypeError(`Payment\'s value: ${value} should be number`)
        };
        if (this._payments.has(id)) {
            throw new Error(`Payment's ID: ${id} already exist`)
        }
        return true;
    }


    deletePayment(id) {
        if(!this._payments.has(id)){
            throw new Error('ID does not exist')
        }
        this._payments.delete(id);
    }

    setOptions(options) {
        if (options === undefined || options === null || options === ''){
            //options = {types: ["service", "product", "other"], precision: 2};
            // do not change default options
            return false;
        }
        if (options.hasOwnProperty('types')){
            this._options.types = options.types;
        }
        if (options.hasOwnProperty('precision')){
            this._options.precision = options.precision;
        }
    }

    toString() {
        return `Summary:\n` +
            `- Payments: ${this._payments.size}\n` +
            `- Balance: ${this.formatValue([...this._payments.values()].map(v => Number(v.value)).reduce((a, b) => a + b))}`
    }

}

// Initialize processor with default options
const generalPayments = new PaymentProcessor();
generalPayments.registerPayment('0001', 'Microchips', 'product', 15000);
generalPayments.registerPayment('01A3', 'Biopolymer', 'product', 23000);
console.log(generalPayments.toString());

// Should throw an error (invalid type)
try {
    generalPayments.registerPayment('E028', 'Rare-earth elements', 'materials', 8000);
} catch (e){
    console.log(e.message);
}
generalPayments.setOptions({types: ['product', 'material']});
generalPayments.registerPayment('E028', 'Rare-earth elements', 'material', 8000);
console.log(generalPayments.get('E028'));
generalPayments.registerPayment('CF15', 'Enzymes', 'material', 55000);

// Should throw an error (ID not found)
try{
generalPayments.deletePayment('E027');
} catch (e){
    console.log(e.message);
}
// Should throw an error (ID not found)
try {
generalPayments.get('E027');
} catch (e){
    console.log(e.message);
}

generalPayments.deletePayment('E028');
console.log(generalPayments.toString());

// Initialize processor with custom types
const servicePyaments = new PaymentProcessor({types: ['service']});
servicePyaments.registerPayment('01', 'HR Consultation', 'service', 3000);
servicePyaments.registerPayment('02', 'Discount', 'service', -1500);
console.log(servicePyaments.toString());

// Initialize processor with custom precision
const transactionLog = new PaymentProcessor({precision: 5});
transactionLog.registerPayment('b5af2d02-327e-4cbf', 'Interest', 'other', 0.00153);
console.log(transactionLog.toString());

/*
Summary:
- Payments: 2
- Balance: 38000.00
Details about payment ID: E028
- Name: Rare-earth elements
- Type: material
- Value: 8000.00
Summary:
- Payments: 3
- Balance: 93000.00
Summary:
- Payments: 2
- Balance: 1500.00
Summary:
- Payments: 1
- Balance: 0.00153

 */