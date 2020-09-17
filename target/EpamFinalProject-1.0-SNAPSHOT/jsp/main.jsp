<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
<%--    <nav class="navbar navbar-expand-sm bg-light navbar-light">--%>
<%--        <ul class="navbar-nav">--%>
<%--            <li class="nav-item active">--%>
<%--                <a class="nav-link" href="#">Catalog</a>--%>
<%--            </li>--%>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link"  href="#">Basket</a>--%>
<%--            </li>--%>
<%--        </ul>--%>
<%--    </nav>--%>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <span class="navbar-brand">Shop</span>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">Catalog</a>
                </li>
            </ul>
            <span class="navbar-text">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="main">Basket</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="login">Log in</a>
                    </li>
                </ul>
            </span>
        </div>
    </nav>
    <div>
        <form name="logoutForm" method="post" action="controller">
            <input type="hidden" name="commandName" value="logout_command">
            <br/>
            <input type="submit" value="Logout"/>
        </form>
    </div>
</div>
</body>
</html>
