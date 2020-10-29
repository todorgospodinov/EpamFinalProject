<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" uri="customTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin</title>

    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>
<main>
    <div class="container">
        <form method="post" action="controller">
            <div>
                <label>Search</label>
                <input type="text" name="searchUsersQuery"/><br/>
            </div>
            <button class="btn btn-secondary nav-link" name="commandName"
                    value="find_users_command">Download</button>
        </form>
        <c:if test="${users.size() != 0}">
            <tag:user-list-custom-tag/>
        </c:if>
        <c:if test="${users.size() == 0}">
            <p>No result</p>
        </c:if>
    </div>
</main>
</body>
</html>
