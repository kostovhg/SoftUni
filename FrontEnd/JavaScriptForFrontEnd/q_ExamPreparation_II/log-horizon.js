$(document).ready(function() {

    // console.log('Loading..')
    const [
        usernameInput, username, loginBtn,
        messageInput, logBtn,
        logsContainer,
    ] =  [
        $('#usernameInput'), $('#username'), $('#usernameBtn'),
        $('#message'), $('#logBtn'),
        $('#logs'), ''
    ];

    let user = 'Anonymous';

    loginBtn.on('click', switchUser);
    logBtn.on('click', addLog);

    function switchUser() {
        if (usernameInput.val() !== ''){
            user = usernameInput.val();
            usernameInput.val('');
            usernameInput.addClass('d-none');
            username.text(`${user}`);
            username.removeClass('d-none');
            loginBtn.text('Logout')
        } else {
            user = 'Anonymous';
            username.text(user);
            username.addClass('d-none');
            loginBtn.text('Set username');
            usernameInput.removeClass('d-none');
        }
    }

    function addLog() {
        let msgType = $('[name=messageType]:checked').val();
        let type = msgType.toLowerCase();
        if(type === 'error') type = 'danger';
        logsContainer.append(prepareLog(type, `${msgType}: ${messageInput.val()}`, user));
        messageInput.val('');
        updateLogs();
    }

    function prepareLog(type, msg, user){
        let log = $(`<div class="row bg-${type} p-3 m-2 text-white">`);
        log.append($(`<div class="col-6 text-left">`).text(`${msg}`));
        log.append($(`<div class="col-3 border-left border-right border-dark">`).text(user));
        log.append($(`<button type="text" class="col-3 btn btn-${type} border-0 p-0">`).text('Archive').on('click', archive));
        return log;
    }

    function archive(e) {
        $(e.currentTarget).parents('.row').remove();
        updateLogs();
    }

    function updateLogs() {
        if($('#logs').children().length === 0){
            $('#emptyMsg').removeClass('d-none');
        } else {
            $('#emptyMsg').addClass('d-none');
        }
    }


});