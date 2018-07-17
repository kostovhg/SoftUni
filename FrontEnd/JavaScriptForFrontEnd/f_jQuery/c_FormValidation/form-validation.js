function validate() {

    // necessary variables (some of them - functions)
    let submit = $('#submit');
    let company = $(`#company`);
    let companyInfo = $('#companyInfo');
    let valid = $("#valid");
    let isCompany = () => company.is(':checked');
    let format = (field, v) => (!v) ? field.css('border', '2px solid red') : field.css('border', 'none');
    let inputs = $('.pairContainer input').toArray().map(i => $(i).attr('id'));


    /* change submit behavior */
    submit.on('click', function (e) {
        e.preventDefault();
        validateForm();
    });

    companyInfo.css('display', 'table'); // obviously for judge there is no point to hide and show company info field...

    /*  add behavior on checkbox to show and hide fieldset #companyInfo */
    // company.on('click', () => isCompany() ?
    //     companyInfo.css('display', 'table') :
    //     companyInfo.css('display', 'none'));

    /* validations for all inputs (#company checkbox should not be checked for validity) */
    let validations = {
        username: (v) => (/^[a-zA-Z0-9]{3,20}$/g).test(v),
        password: (v) => (/^\w{5,15}$/g).test(v) && $(`#confirm-password`).val() === v,
        'confirm-password': (v) => (validations.password(v) && $(`#password`).val() === v),
        email: (v) => (/^.*?@.*?\..*$/g).test(v),
        company: (v) => true,
        companyNumber: (v) => isCompany() ? (v >= 1000 && v <= 9999) : true,
    };
    // the regular expressions for this task are so dumb!!! who is using email like a@.??

    /* attach function to submit button */
    function validateForm() {
        let formValidity = true; // nullify validity of all fields
        for (let input of inputs) {
            let field = $(`input[id=${input}]`);
            let fieldValidity = validations[input](field.val());
            format(field, fieldValidity);
            formValidity &= fieldValidity;
        }
        (formValidity) ? valid.css('display', 'table') : valid.css('display', 'none');
    }

    /* add behavior for each field and on focusout */
    // for (let input of inputs) {
    //     let field = $(`input[id=${input}]`);
    //     //console.log(field);
    //     field.on('focusout', function () {
    //         console.log(`check the validity of ${input} with val ${$(this).val()}`);
    //         format(field, validations[input](field.val()));
    //     })
    // }

}
