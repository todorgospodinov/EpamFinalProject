<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Main</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="bootstrap/bootstrap.css">
    <link rel="stylesheet" href="css/styles.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

    <fmt:setLocale value="${currentLocale}"/>

    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="locbutton.name.ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="locbutton.name.en" var="en_button"/>

    <fmt:message bundle="${loc}" key="header.login_button" var="login_button"/>
    <fmt:message bundle="${loc}" key="header.registration_button" var="registration_button"/>
    <fmt:message bundle="${loc}" key="header.personal_account_button" var="personal_accout_button"/>
    <fmt:message bundle="${loc}" key="header.logout_button" var="logout_button"/>
    <fmt:message bundle="${loc}" key="header.catalog_button" var="catalog_button"/>
    <fmt:message bundle="${loc}" key="header.contacts_button" var="contacts_button"/>
    <fmt:message bundle="${loc}" key="header.admin_button" var="admin_button"/>

</head>
<body>
<header>
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <form method="post" action="controller">
                    <button class="btn btn-secondary nav-link" name="commandName"
                            value="main_page">${catalog_button}</button>
                </form>
            </div>
            <div>
                <ul class="nav">
                    <li class="nav-item">
                        <form method="post" action="controller">
                            <button class="btn btn-secondary nav-link" name="commandName"
                                    value="main_page">${contacts_button}</button>
                        </form>
                    </li>
                    <li class="nav-item">
                        <form method="post" action="controller">
                            <button class="btn btn-secondary nav-link" name="commandName"
                                    value="main_page">${catalog_button}</button>
                        </form>
                    </li>
                    <c:if test="${sessionScope.user != null}">
                        <li class="nav-item">
                            <form method="post" action="controller">
                                <button class="btn btn-secondary nav-link" name="commandName"
                                        value="personal_account_page">${personal_accout_button}</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form method="post" action="controller">
                                <button class="btn btn-secondary nav-link" name="commandName"
                                        value="logout_command">${logout_button}</button>
                            </form>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.user == null }">
                        <li class="nav-item">
                            <form method="post" action="controller">
                                <button class="btn btn-secondary nav-link" name="commandName"
                                        value="login_page">${login_button}</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form method="post" action="controller">
                                <button class="btn btn-secondary nav-link" name="commandName"
                                        value="registration_page">${registration_button}</button>
                            </form>
                        </li>
                    </c:if>
                    <c:if test ="${sessionScope.user.role.toString().equals(\"ADMIN\")}">
                        <li class="nav-item">
                            <form method="post" action="controller">
                                <button class="btn btn-secondary nav-link" name="commandName"
                                        value="admin_page">${admin_button}</button>
                            </form>
                        </li>
                    </c:if>
                    <li class="nav-item">
                        <form action="controller" method="post">
                            <input type="hidden" name="commandName" value="switch_language"/>
                            <input type="hidden" name="newLocale" value="en"/>
                            <button class="btn btn-secondary nav-link">${en_button}</button>
                        </form>
                    </li>
                    <li class="nav-item">
                        <form action="controller" method="post">
                            <input type="hidden" name="commandName" value="switch_language"/>
                            <input type="hidden" name="newLocale" value="ru"/>
                            <button class="btn btn-secondary nav-link">${ru_button}</button>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>
</body>
</html>
