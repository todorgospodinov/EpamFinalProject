<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
<%--    <nav class="navbar navbar-expand-lg navbar-light bg-light">--%>
<%--        <span class="navbar-brand">Shop</span>--%>
<%--        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--            <span class="navbar-toggler-icon"></span>--%>
<%--        </button>--%>
<%--        <div class="collapse navbar-collapse" id="navbarText">--%>
<%--            <ul class="navbar-nav mr-auto">--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="#">Catalog</a>--%>
<%--                </li>--%>
<%--            </ul>--%>
<%--            <span class="navbar-text">--%>
<%--                <ul class="navbar-nav mr-auto">--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" href="main.jsp">Basket</a>--%>
<%--                    </li>--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" href="login.jsp">Log in</a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </span>--%>
<%--        </div>--%>
<%--    </nav>--%>
    <div>
        <form name="loginForm" method="post" action="controller" autocomplete="off">
            <input type="hidden" name="commandName" value="login_command">
            Login:<br/>
            <input type="text" name="login"/><br/>
            Password:<br/>
            <input type="password" name="password"/><br/>
            <div style="color: red">${errorLoginPasswordMessage}</div>
            <br/>
            <input type="submit" value="Login"/>
        </form>
        <div>
            <form name="registerForm" method="post" action="controller" autocomplete="off">
                <input type="hidden" name="commandName" value="register_command">
                Login:<br/>
                <input type="text" name="register_login"/><br/>
                Password:<br/>
                <input type="password" name="register_password"/><br/>
                Repeat password:<br/>
                <input type="password" name="register_password_repeat"/><br/>
                <div style="color: red">${errorRegisterMessage}</div>
                <br/>
                <input type="submit" value="Register"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>
