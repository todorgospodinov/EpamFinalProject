<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

    <fmt:setLocale value="${sessionScope.locale}"/>

    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button"/>

    <fmt:message bundle="${loc}" key="local.header.login_button" var="login_button"/>
    <fmt:message bundle="${loc}" key="local.header.registration_button" var="registration_button"/>
    <fmt:message bundle="${loc}" key="local.header.personal_account_button" var="personal_accout_button"/>
    <fmt:message bundle="${loc}" key="local.header.logout_button" var="logout_button"/>
    <fmt:message bundle="${loc}" key="local.header.catalog" var="catalog"/>
</head>
<body>
<header>
<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
    <div style="justify-content: center;" class="collapse navbar-collapse">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="controller?commandName=go_to_main_page">${catalog}</a>
            </li>
            <c:if test="${sessionScope.user != null}">
                Hello, ${sessionScope.user.name} ${sessionScope.user.patronymic}
                    <li class="nav-item"><a class="nav-link" href="#">${personal_accout_button}</a></li>
                    <li class="nav-item"><a class="nav-link" href="controller?commandName=logout_command">${logout_button}</a></li>
            </c:if>
            <c:if test="${sessionScope.user == null }">
                    <li class="nav-item"><a class="nav-link" href="controller?commandName=go_to_registration_page">${registration_button}</a></li>
                    <li class="nav-item"><a class="nav-link" href="controller?commandName=go_to_login_page">${login_button}</a></li>
            </c:if>
            <li class="nav-item">
                <form action="servletForChangeLanguage" method="post">
                    <input type="hidden" name="locale" value="en"/>
                    <input type="hidden" name="previousRequest" value="controller?commandName=go_to_main_page"/>
                    <button type="submit" class="btn btn-light">${en_button}</button>
                </form>
            </li>
            <li>
                <form action="servletForChangeLanguage" method="post">
                    <input type="hidden" name="locale" value="ru"/>
                    <input type="hidden" name="previousRequest" value="controller?commandName=go_to_main_page"/>
                    <button type="submit" class="btn btn-light">${ru_button}</button>
                </form>
            </li>
        </ul>
    </div>
    </div>
</nav>
</header>


</body>
</html>
