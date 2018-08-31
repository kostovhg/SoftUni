// This object is the default profile to be filled on page load and form submit or reset
// its properties names coresponding to input fields id's (and 'team' points to selected radio button id)
// example:
//      object {
//          propName: propValue  
//      }
//      will be used in DOM as
//      <label for="propName"><input id="propName">propvalue</label>
let defaultProfile = {
    firstName: "Pesho",
    lastName: "Peshov",
    phoneNumber: "555-333-4545",
    ucl: "9311124003",
    team: "other"
};


// This object contains all possible teams and positions for each team
// properties names will corespond to radio buttons id's
// and properties values will fill in the label for each input 
// example:
//      object {
//          propName: propValue  
//      }
//      will be used in DOM as
//      <label for="propName"><input type="radio" ... id="propName">propvalue</label>
let teams = {
    support: {
        tech: "Tech Support",
        medical: "Medical Support",
        assistant: "Assistant Support",
    },
    crm: {
        comunityManager: "Community Manager",
        customerCareManager: "Customer Care Manager",
        leadAdministrativeOfficer: "Lead Administrative Officer"
    },
    marketing: {
        prManager: "PR Manager",
        socialMediaManager: "Social Media Manager",
        marketingSpecialist: "Marketing Specialist",
    },
    development: {
        juniorDeveloper: "Junior Developer",
        regularDeveloper: "Regular Developer",
        seniorDeveloper: "Senior Developer"
    },
    other: {
        teamLead: "Team Lead",
        regularEmployee: "Regular Employee",
        intern: "Intern",
    }
};

// Initialize two objects to be fill with jQuery selectors
// one for inputs, other for values 
//and two selectors for radio buttons 
// as teamSelector will be array for all iputs for teams (each input)
// and position selector will be the container for position radio buttons (the div that will contain the inputs)
let [
    inputSelectors, valuesSelectors, teamSelector, positionSelector
] = [
    {}, {}, $(`input[name=team]`), $('#positions')
];

// Plugin (https://stackoverflow.com/a/6524584)
// for attach event on enter for array of DOM elements
// selected with jQuery
$.fn.pressEnter = function (fn) {
    return this.each(function () {
        let that = $(this);
        that.bind('enterPress', fn);
        that.keyup(function (e) {
            if (e.keyCode === 13)
                that.trigger("enterPress");
        });
    });
};