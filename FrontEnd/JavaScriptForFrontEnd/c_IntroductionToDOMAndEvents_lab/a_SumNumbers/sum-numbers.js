function calc() {

   let [num1, num2, sum] = [
       document.getElementById('num1'),
       document.getElementById('num2'),
       document.getElementById('sum')
   ];
   sum.value = (Number(num1.value) + Number(num2.value));
}