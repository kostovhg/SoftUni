// Project specific entries and entries
const [ENTITY_NAME] = ['cars'];

/*
Handlebars templates locations as constants
 */
/* Handlebars templates locations */
// common for all projects
const [HEADER_SECTION, FOOTER_SECTION, // footer and header
    NAVIGATION_SECTION, WELCOME_SECTION , // navigation and welcome section (welcome is the home for non registered users)
    LOGIN_SECTION, REGISTER_SECTION, // login and register
    HOME_SECTION, // home section is home for logged in users
    LAYOUT
] = [
    './templates/common/header.hbs', './templates/common/footer.hbs',
    './templates/common/navigation.hbs', './templates/main/main.hbs',
    './templates/forms/login.hbs',
    './templates/forms/register.hbs',
    './templates/main/car-listings.hbs',
    './templates/layout.hbs'
];
// Specific for the project
const [
    CREATE_FORM,
    EDIT_FORM,
    DETAILS,
    MY_LIST,
    LIST_ALL,
    LISTING,
    MY_LISTING,
] = [
    './templates/main/create-listing.hbs',
    './templates/main/edit-listing.hbs',
    './templates/main/listing-details.hbs',
    './templates/main/my-listings.hbs',
    './templates/main/car-listings.hbs',
    './templates/partials/listing.hbs',
    './templates/partials/my-listing.hbs',
];

/*
Routes as parameters of constant 'authRoutes'
 */
// common routes
const authRoutes = {
    index: 'index.html', // this
    welcome: '#/welcome', // and this should be the same
    register: '#/register', // to show register form
    login: '#/login', // tho show login form
    logout: '#/logout', // to logout user
    home: '#/home', // for logged in users,
    profile: '#/profile', // open usr profile
};
// custom routes
const customRoutes = {
    all: '#/all', // list all listings view
    my: '#/my', // list my listing view
    create: '#/create', // show create view
    create_entity: `#/${ENTITY_NAME}/create`, // ... create entity route
    delete: `#/${ENTITY_NAME}/delete/:entityId`, // .. deleting entity route
    details: `#/${ENTITY_NAME}/details/:entityId`, // ... get data for specific entity
    edit: `#/${ENTITY_NAME}/edit/:entityId`,
};
// and few messages probably
const msgs = {
    register: 'User registration successful.',
    login: 'Login successful.',
    logout: 'Logout successful.',
    create: 'listing created.',
    update_interpolation: (name) => {return `Listing ${name} updated.`},
    delete: 'Listing deleted.',
};

let nav_sections = {
    'index.html': 'nav_home',
    welcome: 'nav_home',
    home: 'nav_home',
    all: 'nav_all',
    my: 'nav_my',
    create: 'nav_create',
};

/**
 * Bind ajaxStart and ajaxStop to loading box visibility
 * On first ajax start loadingBox will show up.
 * On last ajax end loadingBox will hide again.
 */
$(document).ajaxStart(function () {
    $('#loadingBox').show();
}).ajaxStop(function () {
    $('#loadingBox').hide();
});