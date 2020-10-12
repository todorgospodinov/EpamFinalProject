<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        <%@include file="/WEB-INF/js/scripts.js"%>
    </script>
    <fmt:setLocale value="${sessionScope.locale}"/>

    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button"/>

    <fmt:message bundle="${loc}" key="local.header.login_button" var="login_button"/>
    <fmt:message bundle="${loc}" key="local.header.registration_button" var="registration_button"/>
    <fmt:message bundle="${loc}" key="local.header.personal_account_button" var="personal_accout_button"/>
    <fmt:message bundle="${loc}" key="local.header.logout_button" var="logout_button"/>
    <fmt:message bundle="${loc}" key="local.header.catalog" var="catalog"/>

    <fmt:message bundle="${loc}" key="local.registration_page.name" var="name"/>
    <fmt:message bundle="${loc}" key="local.registration_page.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="local.registration_page.patronymic" var="patronymic"/>
    <fmt:message bundle="${loc}" key="local.registration_page.email" var="email"/>
    <fmt:message bundle="${loc}" key="local.registration_page.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.registration_page.repeat.password" var="repeat_password"/>
    <fmt:message bundle="${loc}" key="local.main_page.menu" var="menu"/>

</head>
<body>

<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div style="justify-content: center;" class="collapse navbar-collapse">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="controller?commandName=go_to_main_page">${catalog}</a>
                </li>
                <c:if test="${sessionScope.user != null}">
                    Hello, ${sessionScope.user.name}, ${sessionScope.user.patronymic}
                    <li class="nav-item"><a class="nav-link" href="#">${personal_accout_button}</a></li>
                    <li class="nav-item"><a class="nav-link"
                                            href="controller?commandName=logout_command">${logout_button}</a></li>
                </c:if>
                <c:if test="${sessionScope.user == null }">
                    <li class="nav-item"><a class="nav-link" href="controller?commandName=go_to_registration_page">${registration_button}</a></li>
                    <li class="nav-item"><a class="nav-link"
                                            href="controller?commandName=go_to_login_page">${login_button}</a></li>
                </c:if>
                <li class="nav-item">
                    <form action="servletForChangeLanguage" method="post">
                        <input type="hidden" name="locale" value="en"/>
                        <input type="hidden" name="previousRequest" value="controller?commandName=go_to_registration_page"/>
                        <button type="submit" class="btn btn-light">${en_button}</button>
                    </form>
                </li>
                <li>
                    <form action="servletForChangeLanguage" method="post">
                        <input type="hidden" name="locale" value="ru"/>
                        <input type="hidden" name="previousRequest" value="controller?commandName=go_to_registration_page"/>
                        <button type="submit" class="btn btn-light">${ru_button}</button>
                    </form>
                </li>
            </ul>
        </div>
    </nav>
</header>
<main>
    <div class="container">
    <div>
        <form name="registerForm" method="post" action="controller" autocomplete="off">
            <input type="hidden" name="commandName" value="register_command">
            ${email}<br/>
            <input type="text" name="register_email"/><br/>
            ${password}<br/>
            <input type="text" name="register_password"/><br/>
            ${repeat_password}<br/>
            <input type="text" name="register_password_repeat"/><br/>
            ${name}<br/>
<%--            <input type="text" name="name" pattern="[a-zA-Zа-яА-ЯЁё]{3,20}" placeholder="${name}"/>--%>
            <input type="text" name="register_name"/><br/>
            ${surname}<br/>
            <input type="text" name="register_surname"/><br/>
            ${patronymic}<br/>
            <input type="text" name="register_patronymic"/><br/>
            <div style="color: red">${errorRegisterMessage}</div>
            <br/>
            <input type="submit" value="Register"/>
        </form>
    </div>
<%--    <a href="javaScript:{openPopUp();}">Activate</a>--%>
<%--    <form action="actionName">--%>
<%--        <div id="divId" style="display:none;">--%>
<%--            UserName:<input type="text" name="userName"/>--%>
<%--        </div>--%>
<%--    </form>--%>
    </div>
</main>
</body>
</html>