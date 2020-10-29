<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Main</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <fmt:setLocale value="${currentLocale}"/>

    <fmt:setBundle basename="local" var="loc"/>

</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>
<main>
        <div class="container">
            <h1 class="display-3">Welcome to my website</h1>
            <p class="lead">Website is under construction</p>
        </div>
</main>
</body>
</html>
