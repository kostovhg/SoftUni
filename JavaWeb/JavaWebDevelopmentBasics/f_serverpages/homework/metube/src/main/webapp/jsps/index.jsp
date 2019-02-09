<%@ page import="static metube.utils.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- import common app html's head -->
   <c:import url="templates/head.jsp" />
<body>
   <div class="container">
      <main>
         <div class="jumbotron">
            <div class="row">
               <div class="<%= COL_MD_12 %> <%= CENTER_IT %>">
                  <h1>Welcome to MeTube</h1>
               </div>
            </div>
            <hr>
            <div class="row">
               <div class="<%= COL_MD_12 %> <%= CENTER_IT %>">
                  <h3>Cool App in Beta version!</h3>
               </div>
            </div>
            <hr>
            <div class="row">
               <div class="col col-md-6 <%= CENTER_IT %>">
                  <a class="btn btn-primary" href="<%= TUBES_CREATE %>" role="button">Create Tube</a>
               </div>
               <div class="col col-md-6 <%= CENTER_IT %>">
                  <a class="btn btn-primary" href="<%= TUBES_ALL %>" role="button">All Tubes</a>
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
