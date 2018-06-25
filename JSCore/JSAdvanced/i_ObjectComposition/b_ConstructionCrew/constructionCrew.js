function constructionCrew(worker) {

    if (worker.handsShaking) {
        worker.bloodAlcoholLevel += worker.weight * worker.experience * 0.1;
        worker.handsShaking = false;
    }

    return worker;
}

/*
{ weight: Number,
  experience: Number,
  bloodAlcoholLevel: Number,
  handsShaking: Boolean }
 */

console.log(
    constructionCrew(
        {
            weight: 80,
            experience: 1,
            bloodAlcoholLevel: 0,
            handsShaking: true
        }));