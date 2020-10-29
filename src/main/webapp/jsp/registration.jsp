<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Registration</title>
    <fmt:setLocale value="${currentLocale}"/>

    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="locbutton.name.ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="locbutton.name.en" var="en_button"/>

    <fmt:message bundle="${loc}" key="header.login_button" var="login_button"/>
    <fmt:message bundle="${loc}" key="header.registration_button" var="registration_button"/>
    <fmt:message bundle="${loc}" key="header.personal_account_button" var="personal_accout_button"/>
    <fmt:message bundle="${loc}" key="header.logout_button" var="logout_button"/>
    <fmt:message bundle="${loc}" key="header.catalog_button" var="catalog"/>

    <fmt:message bundle="${loc}" key="registration_page.name" var="name"/>
    <fmt:message bundle="${loc}" key="registration_page.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="registration_page.patronymic" var="patronymic"/>
    <fmt:message bundle="${loc}" key="registration_page.email" var="email"/>
    <fmt:message bundle="${loc}" key="registration_page.password" var="password"/>
    <fmt:message bundle="${loc}" key="registration_page.repeat.password" var="repeat_password"/>
    <fmt:message bundle="${loc}" key="main_page.menu" var="menu"/>

</head>
<body>

<header>
    <jsp:include page="header.jsp"/>
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