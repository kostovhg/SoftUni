const BASE_URL = `https://testapp-77f91.firebaseio.com/phonebook/contacts`;
const TABLE = $('#phonebook');
const PERSON = $('#person');
const PHONE = $('#phone');


$('#btnLoad').on('click', loadContacts);
$('#btnCreate').on('click', createContact);

function createContact() {
    let name = PERSON.val();
    let phone = PHONE.val();
    if (name.trim() !== '' && phone.trim() !== '') {
        let contact = {name, number: phone};
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
        keys.sort((a, b) => contacts[a].name.localeCompare(contacts[b].name));
        for (let id of keys) {
            TABLE.append($(`<li>`)
                .text(`${contacts[id].name}: ${contacts[id].number} `)
                .append($(`<a href="#">`)
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

