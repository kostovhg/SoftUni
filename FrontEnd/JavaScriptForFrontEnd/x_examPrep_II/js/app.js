let app = (() => {

    teamSelector.on('change', fillPositions);

    /**
     * Function that takes clicked value field and hide it
     * and after that shows the coresponding input with value from value field
     * 
     * @param {*} e - event
     */
    function switchToInput(e) {
        let that = $(e.currentTarget);
        let key = that.attr('id').slice(0, -3); // cut 'Val' from paragraph id
        that.hide();
        inputSelectors[key].val(that.text()).show();
        inputSelectors[key].val(that.text()).focus();
    }

    /**
     * Function that gets current closed input field and hide it.
     * After that transfer input value to value field and shows value field
     *
     */
    function siwtchToValue() {
        let that = $(this);
        that.hide();
        valuesSelectors[that.attr('id')].text(that.val()).show();
    }

    /**
     * Function responsible for attaching events and fill selectors object 
     * on page load.
     * The inputs fields should have id coresponding to each object properties names.
     * The value fields should have id coresponding to each object properties names + "Val";
     * 
     * Example: 
     *  for obj {"propertyName": "propertyValue"} and values for each as "val"
     *  we will select input fields and value fields in form:
     *      <label for="propertyName">"propertyValue"</label>
     *      <input id="propertyName" input="val">   (initialy hidden)
     *      <p id="propertyNameVal">val</p>         (initialy visible)
     */
    function initialize() {
        // Go trough default profile properties names and
        for (let key of Object.keys(defaultProfile)) {
            // Create jQuery selector for each coresponding input 
            inputSelectors[key] = $(`#${key}`);
            // Use above selector to attach event listener
            inputSelectors[key].pressEnter(siwtchToValue).on('focusout', siwtchToValue);
            // Create jQuery selector for all values
            valuesSelectors[key] = inputSelectors[key].next();
            // Add value and event listener for above selector (for values)
            valuesSelectors[key]
                .on('click', switchToInput);
        }
    }


    /**
     * Function responsible for fill values for all values field on initialization
     *
     */
    function populate() {
        // Go trough all values and update them
        for (let key of Object.keys(valuesSelectors)) {
            valuesSelectors[key].text(defaultProfile[key]);
        }
        // Also select team radio button for defaultProfile
        selectTeam(defaultProfile.team);
    }

    /**
     * Get currently selected radio buton from "teams" radio buton group
     *
     * @returns - selected radiobuton value or default value
     */
    function getSelectedTeam() {
        return teamSelector.filter(':checked').prop('value') || defaultProfile.team;
    }

    /**
     * Changes the selectet team from team radio buttons
     *
     * @param {*} teamName - string representing value for radio button to be selected;
     */
    function selectTeam(teamName) {
        let teamToSelect = teamSelector.filter(`[value=${teamName}]`);
        if(teamToSelect.length < 1){
            teamToSelect = teamSelector.filter(`[value=${defaultProfile.team}]`);
        }
        //teamSelector.filter(`[value=${teamName}]`).prop('checked', true);
        teamToSelect.prop('checked', true);
    }

    /**
     * Function that clears and populate position div on change of team radio buton
     *
     */
    function fillPositions() {
        positionSelector.empty(); 
        let selectedTeam = getSelectedTeam();
        let teamPositions = teams[selectedTeam];
        for (const key of Object.keys(teamPositions)) {
            positionSelector
                .append($(`<label class="p-2" for="${key}">`)
                    .html(`<input type="radio" id=${key} name="positions" value="${key}" class="mr-4"/>${teamPositions[key]}`));
        }
    }

    return {
        initialize,
        switchToInput,
        siwtchToValue,
        populate,
        fillPositions,
    };
})();