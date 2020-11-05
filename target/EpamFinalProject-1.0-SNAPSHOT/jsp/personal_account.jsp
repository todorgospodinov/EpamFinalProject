<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>

<html>
<head>
    <title><fmt:message key="personal_account.title"/></title>
</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>
<main>
    <h1>Hello, ${user.name} ${user.patronymic}</h1>
    <div class="container">
        <div class="row">
            <!-- Первый блок -->
            <div class="col-4 ml-auto mr-3 bg-success" style="height: 200px;">111</div>
            <!-- Второй блок -->
            <div class="col-4 mr-auto ml-3 bg-danger" style="height: 200px;">222</div>
        </div>
    </div>
</main>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
