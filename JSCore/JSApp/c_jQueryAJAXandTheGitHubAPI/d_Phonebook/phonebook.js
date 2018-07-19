function attachEvents() {
    const BASE_URL = `https://phonebook-nakov.firebaseio.com/phonebook`;
    const TABLE = $('#phonebook');
    const PERSON = $('#person');
    const PHONE = $('#phone');


    $('#btnLoad').on('click', loadContacts);
    $('#btnCreate').on('click', createContact);

    function createContact() {
        let person = PERSON.val();
        let phone = PHONE.val();
        if (person.trim() !== '' && phone.trim() !== '') {
            let contact = {person, phone};
            $.ajax({
                method: "POST",
                url: BASE_URL + `/.json`,
                data: JSON.stringify(contact)
            }).then(function () {
                loadContacts();
                //appendContacts([contact]);
                PERSON.val('');
                PHONE.val('');
            }).catch(handleError)
        }
    }

    function loadContacts() {
        TABLE.empty();
        $.ajax({
            method: "GET",
            url: BASE_URL + ".json",
        }).then(appendContacts)
            .catch(handleError)
    }

    function appendContacts(contacts) {
        if (contacts !== null) {
            console.log(`appendContacts: ${JSON.stringify(contacts)}`);
            let keys = Object.keys(contacts);
            console.log(`keys from returned contacts: ${keys.join(', ')}`);
            keys = keys.filter(k => typeof(contacts[k]['person']) === 'string');
            keys.sort((a, b) => contacts[a].person.localeCompare(contacts[b].person));
            for (let id of keys) {
                TABLE.append($(`<li>`)
                    .text(`${contacts[id].person}: ${contacts[id].phone} `)
                    .append($(`<button>`)
                        .text(`[Delete]`)
                        .on('click', (event) => deleteContact(event, id))));
            }

        }
    }

    function deleteContact(event, id) {
        $.ajax({
            method: "DELETE",
            url: BASE_URL + `/${id}.json`,
        }).then(function (res) {
            console.log(`result from 'DELETE' ${BASE_URL + '/' + id + '.json'}: ${res} `);
            $(event.target).parent().remove();
        }).catch(handleError);

    }

    function handleError(err) {
        console.error(err)
    }
}