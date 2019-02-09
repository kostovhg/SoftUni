<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<div class="container-fluid">
    <header>
        <c:import url="templates/navbar.jsp"/>
    </header>
    <main>
        <hr class="my-2"/>
        <div class="text-center mb-3">
            <h1>Upload</h1>
        </div>
        <hr class="my-2">
        <div class="container-fluid">
            <div class="row justify-content-center">
                <div class="form-holder text-center">
                    <form class="form-inline" action="/tube/upload" method="POST">
                        <fieldset>
                            <div class="control-group">
                                <label class="control-label h3 mb-2" for="title">Title</label>
                                <div class="controls">
                                    <input type="text" id="title" name="title" placeholder="" class="input-xlarge">
                                </div>
                            </div>
                            <br/>
                            <div class="control-group">
                                <label class="control-label h3 mb-2" for="author">Author</label>
                                <div class="controls">
                                    <input type="text" id="author" name="author" placeholder=""
                                           class="input-xlarge">
                                </div>
                            </div>
                            <br/>
                            <div class="control-group">
                                <label class="control-label h3 mb-2" for="youtube-link">Youtube Link</label>
                                <div class="controls">
                                    <input type="text" id="youtube-link" name="youtube-link" placeholder=""
                                           class="input-xlarge">
                                </div>
                            </div>
                            <br/>
                            <div class="control-group">
                                <label class="control-label h3 mb-2" for="description">Description</label>
                                <div class="controls">
                                <textarea id="description" name="description" placeholder=""
                                          class="input-xlarge" style="resize: none;" cols="75" rows="4"></textarea>
                                </div>
                            </div>
                            <br/>
                            <div class="control-group">
                                <div class="controls">
                                    <button class="btn btn-info">Upload</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
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