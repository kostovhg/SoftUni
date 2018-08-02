$(() => {
    (async function () {

        const [listDiv, detailsDiv, data,
            contactsTemplate, detailsTemplate,
            partialContact, partialPersonalInfo, partialPersonalContacts
        ] = await Promise.all([
            $('#list'),
            $('#details'),
            $.get('./data.json'),
            $.get('./templates/contacts.hbs'),
            $.get('./templates/details.hbs'),
            $.get('./templates/partials/contact.hbs'),
            $.get('./templates/partials/personalInfo.hbs'),
            $.get('./templates/partials/personalContacts.hbs'),
        ]);

        Handlebars.registerPartial('contact', partialContact);
        Handlebars.registerPartial('personalInfo', partialPersonalInfo);
        Handlebars.registerPartial('personalContacts', partialPersonalContacts);

        let contacts = Handlebars.compile(contactsTemplate)({contacts: data});
        let details = Handlebars.compile(detailsTemplate);

        listDiv.append(contacts);

        $('.contact').on('click', function(){
            $('#details').find('.content').remove();
            let index = $(this).attr('data-id');
            let html = details(data[index]);
            detailsDiv.append(html);
            $('.active').removeClass('active');
            $(this).addClass('active')
        })
    })();
});
//
// (async () => {
//     const data = await $.get('./data.json');
//     const contactHTML = await $.get('./templates/contacts.hbs');
//     const partialContactHTML = await $.get('./templates/partials/contact.hbs');
//     Handlebars.registerPartial('contact', partialContactHTML)
//     let contactsTemplate = Handlebars.compile(contactHTML);
//     let finalData = {contacts: data};
//     let resultHTML = contactsTemplate(finalData);
//
//     $('#list').append(resultHTML);
// })();
