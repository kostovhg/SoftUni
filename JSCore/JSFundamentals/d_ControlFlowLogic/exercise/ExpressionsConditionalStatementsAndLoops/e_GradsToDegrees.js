function gradsToDegrees(grads) {
    grads = Number(grads);
    let degrees = grads * (3.6 / 4);
    degrees %= 360;
    if (degrees < 0) {
        degrees += 360;
    }
    console.log(degrees);
}

gradsToDegrees(100);
gradsToDegrees(400);
gradsToDegrees(850);
gradsToDegrees(-50);
gradsToDegrees();