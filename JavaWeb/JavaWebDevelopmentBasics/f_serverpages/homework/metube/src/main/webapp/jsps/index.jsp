<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
   <c:import url="templates/head.jsp" />
</head>
<body>
   <div class="container">
      <main>
         <div class="jumbotron">
            <div class="row">
               <div class="col col-md-12 d-flex justify-content-center">
                  <h1>Welcome to MeTube</h1>
               </div>
            </div>
            <hr>
            <div class="row">
               <div class="col col-md-12 d-flex justify-content-center">
                  <h3>Cool App in Beta version!</h3>
               </div>
            </div>
            <hr>
            <div class="row">
               <div class="col col-md-6 d-flex justify-content-center">
                  <a class="btn btn-primary" href="/tubes/create" role="button">Create Tube</a>
               </div>
               <div class="col col-md-6 d-flex justify-content-center">
                  <a class="btn btn-primary" href="/tubes/all" role="button">All Tubes</a>
               </div>
            </div>
         </div>
      </main>
      <footer>
         <c:import url="templates/footer.jsp" />
      </footer>
   </div>
</body>
</html>
