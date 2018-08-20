$(() => {
    // Prepare constants and variables
    let personInfo = {
        firstName: 'Pesho',
        lastName: 'Peshov',
        phoneNumber: '555-333-4545',
        ucl: 9311124003
    };

    let teamPossition = {
        support: [
            {text: "Tech Support", id: 'techSupport'},
            {text: "Medical Support", id: "medicalSupport"},
            {text: "Assistant Support", id: "assistantSupport"}
        ],
        crm: [
            {text: "Community Manager", id: "communityManager"},
            {text: "Customer Care Manager", id: "customerCareManager"},
            {text: "Lead Administrative Officer", id: "leadAdministrativeOfficer"},
        ],
        marketing: [
            {text: "PR Manager", id: "PRManager"},
            {text: "Social Media Manager", id: "socialMediaManager"},
            {text: "Marketing Specialist", id: "marketingSpecialist"}
        ],
        development: [
            {text: "Junior Developer", id: "juniorDeveloper"},
            {text: "Regular Developer", id: "regularDeveloper"},
            {text: "Senior Developer", id: "seniorDeveloper"},
        ],
        other: [
            {text: "Team Lead", id: "teamLead"},
            {text: "Regular Employee", id: "regularEmployee"},
            {text: "Intern", id: "intern"},
        ]
    };

    // Get all important DOM objects
    let [
        firstNameInput, lastNameInput, phoneNumberInput, uclInput,
        firstNameOut, lastNameOut, phoneNumberOut, uclOut,
        submitBtn, resetBtn, teamRadio, subRadio
    ] = [
        $('#firstName'), $('#lastName'), $('#phoneNumber'), $('#ucl'),
        $('#firstNameOut'), $('#lastNameOut'), $('#phoneNumberOut'), $('#uclOut'),
        $('#submitBtn'), $('#resetBtn'), $('input[name=team]'), $('#sub-radio')
    ];

    // Attach events
    [firstNameInput, lastNameInput, phoneNumberInput, uclInput].forEach(e => {
    e.keypress(takeInput);});
    [firstNameOut, lastNameOut, phoneNumberOut, uclOut].forEach(e => {
        e.on('click', showInput)
    });
    [submitBtn, resetBtn].forEach(b => b.on('click', resetForm));
    teamRadio.on('change', showPositions);

    // Handle events
    function showInput(e){
       let element = $(e.target);
       let key = element.attr('id').replace('Out', '');
        element.addClass('d-none');
        $(`#${key}`).val(element.text()).removeClass('d-none');
        element.text('');
    }

    function takeInput(e) {
        if(e.which === 13 ) {
            let element = $(e.target);
            // if(!isValid(element)) {
            //     alert(`The field should be filled with ${element.attr('type')}`);
            //     return;
            // };
            let key = element.attr('id');
            $(`#${key}Out`).text(element.val()).removeClass('d-none');
            element.val('');
            element.addClass('d-none');
        }
    }

    function isValid(element) {
        let type = element.attr('type');
        switch (type) {
            case "number": return /^[0-9]+$/g.test(element.val());
            case "text": return /^([A-Z][a-z]+\s?)+$/.test(element.val());
            default: return true;
        }
    }
    
    function resetForm() {
        for (let key of Object.keys(personInfo)) {
            $(`#${key}Out`).text(personInfo[key]);
        }
        $(`input[value=other]`).prop('checked', true);
        showPositions();
    }

    function showPositions() {
        subRadio.empty();
        let teamSelected = $(`input[name=team]:checked`).val();
        for (let pos of teamPossition[teamSelected]) {
            subRadio
                .append($(`<input id="${pos.id}" type="radio" name="position" value="${pos.id}" class="col">`))
                .append($(`<label for="${pos.id}" class="col text-dark">`).text(pos.text))
        }
    }
});