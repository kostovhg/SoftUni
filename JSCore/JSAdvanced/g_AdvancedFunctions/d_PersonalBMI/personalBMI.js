function personalBMI(name, ...personalInfo){

    let [age, weight, height] = personalInfo;

    let [calcQuotient, calcStatus ] = [
        (w, h) => Math.round(w / ((h / 100) ** 2)),
        (q) => (q < 25) ? ((q < 18.5) ? 'underweight' : 'normal' ) : ((q < 30) ? 'overweight' : 'obese')
    ];

    let result = {
        name: name,
        personalInfo: {
            age: age,
            weight: weight,
            height: height,
        },
        BMI: calcQuotient(weight, height),
    };

    result.status = calcStatus(result.BMI);

    if(result.status === 'obese'){
        result.recommendation = 'admission required'
    }
    return result;
}

console.log(personalBMI('Peter', 29, 75, 182));
console.log(personalBMI('Honey Boo Boo', 9, 57, 137));

/*
{name: 'Peter',
    personalInfo: {
    age: 29,
        weight: 75,
        height: 182
}
BMI: 23
status: 'normal' }
*/


/*
underweight, for BMI less than 18.5;
normal, for BMI less than 25;
overweight, for BMI less than 30;
obese, for BMI 30 or more;
 */