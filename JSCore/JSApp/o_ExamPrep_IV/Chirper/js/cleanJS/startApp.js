/**
 * For working without sammy and handlebars this function should be called in index.html
 */

function startApp(){
    showHideMenuLinks();
    showView('viewHome'); // parameter is the id of the view container tag
    attachAllEvents();
}
