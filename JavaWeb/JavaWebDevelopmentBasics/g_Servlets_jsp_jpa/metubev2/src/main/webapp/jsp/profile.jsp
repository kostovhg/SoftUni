<%@ page import="metube.domain.models.view.UserProfileViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<% UserProfileViewModel model = (UserProfileViewModel) request.getAttribute("model"); %>
<div class="container-fluid">
    <header>
        <c:import url="templates/navbar.jsp"/>
    </header>
    <main>
        <hr class="my-2"/>
        <div class="text-center mt-3">
            <h4 class="text-info text-center">@<%= model.getUsername()%>
            </h4>
            <h4 class="text-info text-center">(<%= model.getEmail()%>)</h4>
        </div>
        <hr>
        <div class="container-fluid">
            <div class="row d-flex flex-column">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Title</th>
                        <th scope="col">Author</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (int i = 0; i < model.getTubes().size(); i++) { %>
                    <tr>
                        <td><%= i + 1%></td>
                        <td><%= model.getTubes().get(i).getTitle()%></td>
                        <td><%= model.getTubes().get(i).getAuthor()%></td>
                        <td><a href="/tube/details/<%=model.getTubes().get(i).getId()%>">Details</a></td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
        <hr class="my-3"/>
    </main>
    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>
</div>
</body>
</html>