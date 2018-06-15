function attachEventsListeners() {

    let [days, hours, minutes, seconds] = [
        document.getElementById('days'),
        document.getElementById('hours'),
        document.getElementById('minutes'),
        document.getElementById('seconds')
    ];

    let [daysBtn, hoursBtn, minutesBtn, secondsBtn] = [
        document.getElementById('daysBtn'),
        document.getElementById('hoursBtn'),
        document.getElementById('minutesBtn'),
        document.getElementById('secondsBtn')
    ];

    daysBtn.addEventListener('click', convertDays);
    hoursBtn.addEventListener('click', convertHours);
    minutesBtn.addEventListener('click', convertMinutes);
    secondsBtn.addEventListener('click', convertSeconds);

    function convertDays() {
        let d = Number(days.value);
        let h = d * 24;
        let m = h * 60;
        let s = m * 60;
        hours.value = h;
        minutes.value = m;
        seconds.value = s;
    }

    function convertHours(){
        let h = Number(hours.value);
        days.value =  h / 24;
        minutes.value = h * 60;
        seconds.value = h * 3600;
    }

    function convertMinutes() {
        let m = Number(minutes.value);
        days.value = m / 1440;
        hours.value = (m / 60);
        seconds.value = m * 60;
    }

    function convertSeconds() {
        let s = Number(seconds.value);
        minutes.value = s /60;
        hours.value = s / 3600;
        days.value = s/86400;
    }
}