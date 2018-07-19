function attachEvents() {
    let url = `https://ajaxdemos-bfee6.firebaseio.com/messenger/.json`;
    // const TEXTAREA = $('#messages');
    // const AUTHOR = $('#author');
    // const CONTENT = $('#content ');
    // const SUBMIT = $('#submit');
    // const REFRESH = $('#refresh');

    $('#refresh').on('click', function () {
        $('#messages').text('');
        $.ajax({
            method: "GET",
            url: url
        }).then(function (res) {
            let keys = Object.keys(res);
            keys
                .filter(k => res[k].hasOwnProperty('author'))
                .sort((a, b) => res[a].timestamp - res[b].timestamp);
            for (let id of keys) {
                //console.log(res[id]);
                $('#messages').append(`${res[id].author}: ${res[id].content}\n`)
            }
            $('#author').val('');
            $('#content').val('');
        }).catch(function (err) {
            //console.warn(err)
        })
    });

    $('#submit').on('click', function () {
        let author = $('#author').val();
        let content = $('#content').val();
        if (author !== '' && content !== '') {
            $.ajax({
                method: "POST",
                url: url,
                data: JSON.stringify({author: author, content: content, timestamp: Date.now() })
            }).then(function (res) {
                $('#refresh').click();
            }).catch(function (err) {
                //console.warn(err)
            })
        }
    })
}