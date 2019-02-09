<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<nav class="navbar navbar-expand-lg navbar-dark bg-color-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand h4" href="/">MeTube&trade;</a>

    <div class="collapse navbar-collapse justify-content-end row" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <% if (request.getSession().getAttribute("username") == null) { %>
            <li class="nav-item active col-md-4">
                <a class="nav-link h5" href="/home">Home</a>
            </li>
            <li class="nav-item active col-md-4">
                <a class="nav-link h5" href="/login">Login</a>
            </li>
            <li class="nav-item active col-md-4">
                <a class="nav-link h5" href="/register">Register</a>
            </li>
            <% } else {%>
            <li class="nav-item active col-md-3">
                <a class="nav-link h5" href="/home">Home</a>
            </li>
            <li class="nav-item active col-md-3">
                <a class="nav-link h5" href="/profile">Profile</a>
            </li>
            <li class="nav-item active col-md-3">
                <a class="nav-link h5" href="/tube/upload">Upload</a>
            </li>
            <li class="nav-item active col-md-3">
                <a class="nav-link h5" href="/logout">Logout</a>
            </li>
            <% } %>
        </ul>
    </div>
</nav>
</html>