function XMLMessenger(input) {

    let regex = /^<message\s+((([a-z]+)=\"([a-zA-Z0-9\s\.]+)\"\s*)+)>([\s|\S]+)<\/message>$/m;

    let matches = regex.exec(input);

    if(matches) {
        let [sender, recipient] = [
            /\bfrom=\"([a-zA-Z\s\.]+)\"/,
            /\bto=\"([a-zA-Z\s\.]+)\"/
        ];

        let senderName = sender.exec(matches[1]);
        let recName = recipient.exec(matches[1]);
        let msg = matches[5].split(/\n/g);

        if(!senderName || !recName){
            console.log(`Missing attributes`);
            //console.log(`===================`)
            return;
        } else {
            senderName = senderName[1];
            recName = recName[1];
            let html = [`<article>`];
            html.push(` <div>From: <span class="sender">${senderName}</span></div>`);
                html.push(` <div>To: <span class="recipient">${recName}</span></div>`);
            html.push(` <div>`);
            for (let line of msg) {
                html.push(`    <p>${line}</p>`)
            }
            html.push(` </div>`);
            html.push(`</article>`);
            console.log(html.join(`\n`));
        }
    } else {
        console.log(`Invalid message format`);
    }

//console.log(`-------------------`)
}

// XMLMessenger(`<message to="Bob" from="Alice" timestamp="1497254092">Hey man, what's up?</message>`);
XMLMessenger(`<message from="John Doe" to="Alice">Not much, just chillin. How about you?</message>`);
// XMLMessenger(`<message from="Alice" timestamp="1497254112">Same old, same old</message>`);
// XMLMessenger(`<message to="Bob" from="Alice" timestamp="1497254114">Same old, same old\n` +
//     `Let's go out for a beer</message>`);
// XMLMessenger(`<message to="Alice" from="Charlie"><img src="fox.jpg"/></message><meta version="2"/>`);
// XMLMessenger(`)    <message from="Hillary" to="Edward" secret:true>VGhpcyBpcyBhIHRlc3Q</message>`);
