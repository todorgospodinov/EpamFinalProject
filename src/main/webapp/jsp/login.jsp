<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Login</title>
    <fmt:setLocale value="${currentLocale}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="login_page.email" var="email"/>
    <fmt:message bundle="${loc}" key="login_page.password" var="password"/>

</head>
<body>

<main>
    <jsp:include page="header.jsp"/>

    <div class="container">
        <div>
            <form method="post" action="controller" autocomplete="off">
                <input type="hidden" name="commandName" value="login_command">
                <div>
                    <label>${email}</label>
                    <input type="text" name="email"/><br/>
                </div>
                <div>
                    <label>${password}</label>
                    <input type="password" name="password"/><br/>
                </div>
                <input type="submit" value="Login"/>
            </form>
        </div>
    </div>
</main>
</body>
</html>
