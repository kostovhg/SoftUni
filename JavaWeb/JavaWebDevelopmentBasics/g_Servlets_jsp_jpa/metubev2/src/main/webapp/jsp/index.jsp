<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<div class="container-fluid">
    <header>
        <c:import url="templates/navbar.jsp" />
    </header>
    <main>
        <hr class="my-3"/>
        <div class="jumbotron">
            <p class="h1 display-3">Welcome to MeTube&trade;!</p>
            <p class="h3">The simplest, easiest to use, most comfortable Multimedia Application.</p>
            <hr class="my-3">
            <p><a href="/login">Login</a> if you have an account or <a href="/register">Register</a> now and start tubing.</p>
        </div>
        <hr class="my-3"/>
    </main>
    <footer>
        <c:import url="templates/footer.jsp" />
    </footer>
</div>
</body>
