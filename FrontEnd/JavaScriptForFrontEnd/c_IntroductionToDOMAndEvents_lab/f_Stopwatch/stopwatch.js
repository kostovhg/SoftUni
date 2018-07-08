function stopwatch() {
    let [time, stopBtn, startBtn, seconds, isRunning ] =
        [
            document.getElementById('time'),
            document.getElementById('stopBtn'),
            document.getElementById('startBtn'),
            0,
            false
        ];
    let interval;

    startBtn.addEventListener('click', startTimer);
    stopBtn.addEventListener('click', stopTimer);

    function update(){
        let min = ~~(seconds / 60 );
        if(min >= 60) min = 0;
        time.textContent =`${("0" + min).slice(-2)}:${("0" + (seconds % 60)).slice(-2)}`;
        seconds++;
    }

    function startTimer() {
        startBtn.setAttribute('disabled', 'true');
        stopBtn.removeAttribute('disabled');
        if(!isRunning) {
            update();
            interval = setInterval(update, 1000);
            isRunning = true;
        }
    }

    function stopTimer(){
        startBtn.removeAttribute('disabled');
        stopBtn.setAttribute('disabled', 'true');
        if(isRunning) {
            clearInterval(interval);
            isRunning = false;
            seconds = 0;
        }
    }
}