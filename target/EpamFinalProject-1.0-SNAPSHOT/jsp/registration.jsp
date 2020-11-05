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
                    <label><fmt:message key="registration_page.email"/></label>
                    <input class="form-control" type="text" name="email"/><br/>
                </div>
                <div>
                    <label><fmt:message key="registration_page.password"/></label>
                    <input class="form-control" type="text" name="password"/><br/>
                </div>
                <div>
                    <label><fmt:message key="registration_page.repeat_password"/></label>
                    <input class="form-control" type="text" name="repeat_password"/><br/>
                </div>
                <div>
                    <label><fmt:message key="registration_page.name"/></label>
                    <input class="form-control" type="text" name="name"/><br/>
                </div>
                <div>
                    <label><fmt:message key="registration_page.surname"/></label>
                    <input class="form-control" type="text" name="surname"/><br/>
                </div>
                <div>
                    <label><fmt:message key="registration_page.patronymic"/></label>
                    <input class="form-control" type="text" name="patronymic"/><br/>
                </div>
                <c:if test="${userDataIncorrect}">
                    <div style="color: red">
                        <p><fmt:message key="registration_page.incorrect_data"/></p>
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