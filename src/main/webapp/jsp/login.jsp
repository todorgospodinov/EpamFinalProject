<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>

<html>
<head>
    <title><fmt:message key="login_page.title"/></title>
</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>
<main>
    <div class="container">
        <div class="col-4 mx-auto my-lg-4 p-3 bg-light">
            <form method="post" action="controller" autocomplete="off">
                <div>
                    <label for="email"><fmt:message key="login_page.email"/></label>
                    <input id="email" class="form-control" type="text" name="email"/><br/>
                </div>
                <div>
                    <label for="password"><fmt:message key="login_page.password"/></label>
                    <input id="password" class="form-control" type="password" name="password"/><br/>
                </div>
                <c:if test="${userDataIncorrect}">
                    <div style="color: red">
                        <p><fmt:message key="login_page.incorrect_data"/></p>
                    </div>
                </c:if>
                <c:if test="${userSuccessChangePassword}">
                    <div style="color: red">
                        <p><fmt:message key="login_page.user_success_change_password"/></p>
                    </div>
                </c:if>
                <button type="submit" class="btn btn-secondary btn-block"
                        name="commandName" value="login_command">
                    <fmt:message key="login_page.login_button"/>
                </button>

                <button type="submit" class="btn btn-secondary btn-block"
                        name="commandName" value="forgot_password_page">
                    <fmt:message key="login_page.forgot_password_button"/>
                </button>
            </form>
        </div>
    </div>
</main>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
