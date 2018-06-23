function argumentInfo(){

    let argumentsByType = {};
    let orderOfAppearance = 0;
    [...arguments].forEach(a => {
            let type = typeof(a);
            if(argumentsByType[type]) {
                argumentsByType[type][0]++;
            } else {
                argumentsByType[type] = [1, orderOfAppearance++];
            }
            console.log(`${type}: ${a}`)
        }
    );
    Object.getOwnPropertyNames(argumentsByType)
        .sort((a,b) => (argumentsByType[b][0] - argumentsByType[a][0]) || argumentsByType[a][1] - argumentsByType[b][1])
        .forEach(t => console.log(`${t} = ${argumentsByType[t][0]}`));
}

argumentInfo('cat', 42, function () { console.log('Hello world!'); });
argumentInfo({ name: 'bob'}, 3.333, 9.999);
argumentInfo(42, 'cat', 15, 'kitten', 'tomcat');
