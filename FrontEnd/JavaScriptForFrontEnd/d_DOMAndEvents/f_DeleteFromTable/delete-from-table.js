function deleteByEmail() {
    let customers = [...document.querySelectorAll('#customers tr')]
        .filter(e => e.querySelector('td') !== null);
    let searched = document.getElementsByName('email')[0];
    for (let customer of customers) {
        console.log(customer.getElementsByTagName('td')[0].textContent);
        console.log(customer.getElementsByTagName('td')[1].textContent);
        if(customer.getElementsByTagName('td')[1].textContent === searched.value){
            customer.parentNode.removeChild(customer);
            document.getElementById('result').textContent = 'Deleted';
            return;
        }
    }
    document.getElementById('result').textContent = 'Not found.';
}