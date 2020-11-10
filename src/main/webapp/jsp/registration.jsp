<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>

<html>
<head>
    <title><fmt:message key="registration_page.title"/></title>
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
                    <label for="email"><fmt:message key="registration_page.email"/></label>
                    <input id="email" class="form-control" type="text" name="email"/><br/>
                </div>
                <div>
                    <label for="password"><fmt:message key="registration_page.password"/></label>
                    <input id="password" class="form-control" type="text" name="password"/><br/>
                </div>
                <div>
                    <label for="password_repeat"><fmt:message key="registration_page.repeat_password"/></label>
                    <input id="password_repeat" class="form-control" type="text" name="password_repeat"/><br/>
                </div>
                <div>
                    <label for="name"><fmt:message key="registration_page.name"/></label>
                    <input id="name" class="form-control" type="text" name="name"/><br/>
                </div>
                <div>
                    <label for="surname"><fmt:message key="registration_page.surname"/></label>
                    <input id="surname" class="form-control" type="text" name="surname"/><br/>
                </div>
                <div>
                    <label for="patronymic"><fmt:message key="registration_page.patronymic"/></label>
                    <input id="patronymic" class="form-control" type="text" name="patronymic"/><br/>
                </div>
                <c:if test="${userDataIncorrect}">
                    <div style="color: red">
                        <p><fmt:message key="registration_page.incorrect_data"/></p>
                    </div>
                </c:if>
                <c:if test="${incorrectImageMessage}">
                    <div style="color: red">
                        <p><fmt:message key="registration_page.incorrect_image"/></p>
                    </div>
                </c:if>
                <div class="form-row text-center">
                    <div class="col-12">
                        <button type="submit" class="btn btn-secondary"
                                name="commandName" value="register_command">
                            <fmt:message key="registration_page.register_button"/>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</main>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>