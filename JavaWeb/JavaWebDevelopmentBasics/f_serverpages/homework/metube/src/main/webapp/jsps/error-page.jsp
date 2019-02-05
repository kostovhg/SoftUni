<%@ page import="static metube.utils.Constants.*" %>
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
               <div class="<%= COL_MD_12 %> <%= CENTER_IT %>">
                  <h1>Error</h1>
               </div>
            </div>
            <hr>
            <div class="row">
               <div class="<%= COL_MD_12 %> <%= CENTER_IT %>">
                  <h3>Something went wrong!</h3>
               </div>
            </div>
            <div class="row">
               <div class="<%= COL_MD_12 %> <%= CENTER_IT %>">
                  <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZxGc9JYZAt_mrA-YhB_tlQLd4iavSKkurUgtPlccIwNOKcXXO"></img>
               </div>
            </div>
            <hr>
            <div class="row">
               <div class="<%= COL_MD_12 %> <%= CENTER_IT %>">
                  <a href="/">Back to Home page</a>
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
