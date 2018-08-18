$(() => {
    let [firstName, lastName, company, branch, emailOutput] =
    [$('#first-name'), $('#last-name'), $('#company'), $('#branch'), $('#work-email')];
    let inputs = {firstName, lastName, company, branch};
    let email = {firstName: '',lastName: '',company: '',branch: '',
        toString: function() {
            return `${this.firstName[0]}.${this.lastName}@${this.branch}-${this.company}.com`.toLocaleLowerCase();
        }
    };

    for (let key of Object.keys(inputs)) {
        inputs[key].on('keyup', function (e) {
            email[key] = $(e.target).val();
            emailOutput.val(email.toString())
        });
    }
    //
    // firstName.on('keyup', function (e) {
    //     email.firstName = $(e.target).val();
    //     updateEmail()
    // });
    //
    // lastName.on('keyup', function (e) {
    //     email.lastName = $(e.target).val();
    //     updateEmail();
    // });
    //
    // company.on('keyup', function (e) {
    //     email.company = $(e.target).val();
    //     updateEmail();
    // });
    //
    // branch.on('keyup', function (e) {
    //     email.branch = $(e.target).val();
    //     updateEmail();
    // });
    //
    // function updateEmail() {
    //     emailOutput.val(email.toString());
    // }
});