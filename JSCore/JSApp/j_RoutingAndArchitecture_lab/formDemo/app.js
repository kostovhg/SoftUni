const app = Sammy('#main', function () {

    this.get('#/index.html', function () {
        this.swap("<h2>Home Page</h2>")
    });
    this.get('#/about', function () {
        this.swap("<h2>About Page</h2>")
    });
    this.get('#/contact', function () {
        this.swap("<h2>Contact Page</h2>")
    });

    this.get('#/catalog/:productId', (context)=> {
        console.log(context.params.productId);
        alert(context.params.productId)
    })
});

$(()=>{
   app.run();
});