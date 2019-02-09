<%@ page import="static metube.utils.Constants.*" %>
<%@ page import="metube.domain.models.view.AllTubesViewModel" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- import common app html's head -->
<c:import url="templates/head.jsp"/>
<body>
<% List<AllTubesViewModel> tubes = (List<AllTubesViewModel>) request.getAttribute(TUBES_LIST); %>
<div class="container">
    <main>
        <div class="jumbotron">
            <div class="row">
                <div class="<%= COL_MD_12 %> <%= CENTER_IT %>">
                    <h1><%= request.getAttribute(PAGE_HEADING) %>
                    </h1>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="<%= COL_MD_12 %> <%= CENTER_IT %>">
                    <h3><%= request.getAttribute(PAGE_SUBHEADING) %>
                    </h3>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="<%= COL_MD_12 %> <%= CENTER_IT %>">
                    <% if (tubes != null) { %>
                    <ul>
                        <c:forEach items="${tubesList}" var="tube">
                            <li>
                                <a href="<c:url value="/tubes/details?name=${tube.getName()}"/>">${tube.name}</a></li>
                        </c:forEach>
                    </ul>
                    <% } else { %>
                    <p>No tubes – <a href="<%= TUBES_CREATE %>">Create some!</a></p>
                    <% } %>
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
        <c:import url="templates/footer.jsp"/>
    </footer>
</div>
</body>

