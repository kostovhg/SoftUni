function secretData(input) {

    let allREgex = [
        /\*[A-Z][a-zA-Z]*(?= |\t|$)/g,
        /\+[0-9-]{10}(?= |\t|$)/g,
        /\_[0-9a-zA-Z]+(?= |\t|$)/g,
        /\![0-9a-zA-Z]+(?= |\t|$)/g];

    for (let sent of input) {
        console.log(
            sent
                .replace(allREgex[0], m => "|".repeat(m.length))
                .replace(allREgex[1], m => "|".repeat(m.length))
                .replace(allREgex[2], m => "|".repeat(m.length))
                .replace(allREgex[3], m => "|".repeat(m.length))
        )
    }
}

secretData(
    [   `Agent *Ivankov was in the room when it all happened.`,
        `The person in the room was heavily armed.`,
        `Agent *Ivankov had to act quick in order.`,
        `He picked up his phone and called some unknown number.`,
        `I think it was +555-49-796`,
        `I can't really remember...`,
        `He said something about "finishing work" with subject !2491a23BVB34Q and returning to Base _Aurora21`,
        `Then after that he disappeared from my sight.`,
        `As if he vanished in the shadows.`,
        `A moment, shorter than a second, later, I saw the person flying off the top floor.`,
        `I really don't know what happened there.`,
        `This is all I saw, that night.`,
        `I cannot explain it myself...`]
);