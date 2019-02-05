<%@ page import="metube.utils.Constants" %>
<%@ page import="static metube.utils.Constants.*" %>
<%@ page import="metube.domain.models.view.TubeDetailsViewModel" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
</head>
<body>
<%
    // Load map from servlet with specific for that page attributes
    Map<String, String> attributesMap = (Map<String, String>) request.getAttribute(ATTRIBUTES_MAP);
%>
<div class="container">
    <main>
        <div class="jumbotron">
            <form action="<%= TUBES_CREATE %>" method="post" id="create-form">
                <div class="row">
                    <div class="<%= COL_MD_12 %> <%= CENTER_IT %>">
                        <h1><%= attributesMap.get(PAGE_HEADING) %>
                        </h1>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="<%= COL_MD_12 %>">
                        <div class="row <%= CENTER_IT %>">
                            <label for="<%= title %>">
                                Title
                            </label>
                        </div>
                        <div class="row <%= CENTER_IT %>">
                            <input type="text" id="<%= title %>" name="<%= TUBE_EF_NAME %>">
                        </div>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="<%= COL_MD_12 %>">
                        <div class="row <%= CENTER_IT %>">
                            <label for="<%= TUBE_EF_DESCRIPTION%>">
                                Description
                            </label>
                        </div>
                        <div class="row <%= CENTER_IT %>">
                            <textarea id="<%= TUBE_EF_DESCRIPTION %>" name="<%= TUBE_EF_DESCRIPTION %>" rows="3">
                            </textarea>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="<%= COL_MD_12 %>">
                        <div class="row <%= CENTER_IT %>">
                            <label for="<%= TUBE_EF_YOU_TUBE_LINK %>">
                                YouTube Link
                            </label>
                        </div>
                        <div class="row <%= CENTER_IT %>">
                            <input type="text" id="<%= TUBE_EF_YOU_TUBE_LINK %>" name="<%= TUBE_EF_YOU_TUBE_LINK %>">
                        </div>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="<%= COL_MD_12 %>">
                        <div class="row <%= CENTER_IT %>">
                            <label for="<%= TUBE_EF_UPLOADER %>">
                                Uploader
                            </label>
                        </div>
                        <div class="row <%= CENTER_IT %>">
                            <input type="text" id="<%= TUBE_EF_UPLOADER %>" name="<%= TUBE_EF_UPLOADER %>"
                                   data-validation="length" data-validation-length="2-56">
                        </div>
                    </div>
                </div>
                <hr>
                <div class="row mt-4">
                    <div class="<%= COL_MD_12 %> <%= CENTER_IT %>">
                        <button type="submit" class="btn btn-primary">Create Tube</button>
                    </div>
                </div>
            </form>
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
</html>

