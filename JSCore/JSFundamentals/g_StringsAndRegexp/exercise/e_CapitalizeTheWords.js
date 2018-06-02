function capitalizeTheWords(input) {

    console.log(input.split(/\s+/).map(w =>
        w[0].toUpperCase() + w.substr(1).toLowerCase()).join(' '));
}

capitalizeTheWords('Capitalize these words');
capitalizeTheWords('Was that Easy? tRY thIs onE for SiZe!');




