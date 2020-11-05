<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" uri="customTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>

<html>
<head>
    <title><fmt:message key="admin_page.title"/></title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">

</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>
<main>
    <div class="container">
        <div class="col-12 mx-auto my-lg-4 p-3 bg-light">
            <div class="col-6">
                <form method="post" action="controller">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" name="searchUsersQuery" placeholder="<fmt:message key="admin_page.user_search"/>">
                        <div class="input-group-append">
                            <button class="btn btn-secondary" name="commandName"
                                    value="find_users_command"><fmt:message key="admin_page.search"/>
                            </button>
                        </div>
                    </div>
<%--                    <div>--%>
<%--                        <label><fmt:message key="admin_page.user_search"/></label>--%>
<%--                        <input type="text" class="form-control" name="searchUsersQuery"/><br/>--%>
<%--                    </div>--%>
<%--                    <button class="btn btn-secondary nav-link" name="commandName"--%>
<%--                            value="find_users_command"><fmt:message key="admin_page.search"/>--%>
<%--                    </button>--%>
                </form>
            </div>
            <c:if test="${users.size() != 0}">
                <table class="table">
                    <thead class="thead-light">
                    <tr>
                        <th><fmt:message key="admin_page.name"/></th>
                        <th><fmt:message key="admin_page.surname"/></th>
                        <th><fmt:message key="admin_page.patronymic"/></th>
                        <th><fmt:message key="admin_page.role"/></th>
                        <th><fmt:message key="admin_page.status"/></th>
                        <th><fmt:message key="admin_page.email"/></th>
                        <th colspan="2"><fmt:message key="admin_page.actions"/></th>
                    </tr>
                    </thead>
                    <tag:user-list-custom-tag/>
                </table>
            </c:if>
            <c:if test="${users.size() == 0}">
                <p>No result</p>
            </c:if>
        </div>
    </div>
</main>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
