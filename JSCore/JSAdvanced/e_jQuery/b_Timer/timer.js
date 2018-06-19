function timer() {


    let [timeInSeconds, hours, minutes, seconds, start, stop] = [
        0, $(`#hours`), $(`#minutes`), $(`#seconds`), $(`#start-timer`), $(`#stop-timer`)
    ];


    let interv;
    start.on("click", function () {
        if (!interv) {
            interv = setInterval(() => {
                //timeInSeconds = (+hours.text() * 3600) + (+minutes.text() * 60) + +(seconds.text());
                timeInSeconds++;
                hours.text((`0` + (~~(timeInSeconds / 3600))).slice(-2));
                minutes.text((`0` + (~~(timeInSeconds % 3600 / 60))).slice(-2));
                seconds.text(
                    (`0` + ~~(timeInSeconds % 60)).slice(-2));
            }, 1000);
        }
    });

    stop.on("click", function () {
        if (interv) {
            clearInterval(interv);
            interv=null;
        }
    });

}