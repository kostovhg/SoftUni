function attachEvents() {

    const BASE_URL = `https://judgetests.firebaseio.com/`;
    let [location, currentDiv, upcomingDiv, forecastDiv, symbols] = [
        $('#location'), $('#current'), $('#upcoming'), $('#forecast'),
        {
            'Sunny': '&#x2600;',  // ☀
            'Partly sunny': '&#x26C5;',  // ⛅
            'Overcast': '&#x2601;',  // ☁
            'Rain': '&#x2614;',  // ☂
            'Degrees': '&#176;',  // °
        }
    ];

    // Get request to firebase
    function request(endpoint) {
        //console.log(BASE_URL + endpoint + `.json`);
        return $.ajax({
            method: "GET",
            url: BASE_URL + endpoint + `.json`
        })
    }

    // Get the forecast for wanted location or error
    function getForecast() {
        request('locations')
            .then(displayForecast)
            .catch(handleError)
    }


    // Display forecast
    function displayForecast(allLocations) {
        let searchedLocation = location.val();
        let locationCode = allLocations
            .filter(l => l['name'] === searchedLocation)
            .map(l => l['code'])[0];
        if (!locationCode) handleError();

        let currentCondition = request(`forecast/today/${locationCode}`);
        let upcomingConditions = request(`forecast/upcoming/${locationCode}`);

        Promise.all([currentCondition, upcomingConditions])
            .then(displayConditions).catch(handleError)

    }

    // Modify DOM on successful request return
    function displayConditions([current, upcoming]) {
        $('span', forecastDiv).remove();
        forecastDiv.css('display', '');

        function appendDataToCurrent() {
            let forecast = current.forecast
            currentDiv.append($('<span>')
                .addClass('condition symbol').html(symbols[forecast['condition']]))
                .append($('<span>').addClass('condition')
                    .append($('<span>')
                        .addClass('forecast-data')
                        .text(current['name']))
                    .append($('<span>')
                        .addClass('forecast-data')
                        .html(`${current['forecast']['low']}${symbols['Degrees']}/${current['forecast']['high']}${symbols['Degrees']}`))
                    .append($('<span>')
                        .addClass('forecast-data')
                        .html(`${current['forecast']['condition']}`)))
        }

        function appendDataToUpcoming() {
            //console.log(upcoming['forecast'])
            for (let coming of upcoming['forecast']) {
                upcomingDiv.append($('<span>')
                    .addClass('upcoming')
                    .append($('<span>').addClass('symbol').html(symbols[coming['condition']]))
                    .append($('<span>').addClass('forecast-data')
                        .html(`${coming['low']}${symbols['Degrees']}/${coming['high']}${symbols['Degrees']}`))
                    .append($('<span>').addClass('forecast-data')
                        .html(`${coming['condition']}`)))
            }
        }

        appendDataToCurrent();
        appendDataToUpcoming();
    }

    // Display error (obviously judge does not care how exactly the error will be shown)
    function handleError(err) {
        //console.log(err)
        forecastDiv.css('display', '').prepend($('<span>').text('Error'));
        setTimeout(() => forecastDiv.fadeOut(500), 3000);
    }

    $('#submit').click(getForecast);
}