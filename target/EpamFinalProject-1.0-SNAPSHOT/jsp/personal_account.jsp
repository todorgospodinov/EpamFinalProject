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
    <div class="container">
        <div class="col-12 mx-auto my-lg-4 p-3 bg-light">
            <h1 class="display-3">${user.getName()} ${user.getSurname()} ${user.getPatronymic()}</h1>
            <h3 class="lead">${user.getEmail()}</h3>
            <form method="post" action="controller" autocomplete="off">
                <button type="submit" class="btn btn-secondary"
                        name="commandName" value="change_password_page">
                    <fmt:message key="personal_account.change_password_button"/>
                </button>
            </form>
            <c:if test="${orders.size() != 0}">
                <h1><fmt:message key="personal_account.history"/></h1>
                <table class="table">
                    <thead class="thead-light">
                    <tr>
                        <th><fmt:message key="personal_account.order_id"/></th>
                        <th><fmt:message key="personal_account.creation_date"/></th>
                        <th><fmt:message key="personal_account.closing_date"/></th>
                        <th><fmt:message key="personal_account.status"/></th>
                        <th><fmt:message key="personal_account.action"/></th>
                    </tr>
                    </thead>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <th>${order.getOrderId()}</th>
                            <th>${order.getCreationDate()}</th>
                            <th>${order.getClosingDate()}</th>
                            <th>${order.getStatus()}</th>
                            <th>
                                <form method="post" action="controller" autocomplete="off">
                                    <input type="hidden" name="commandName" value="order_page">
                                    <button class="btn btn-secondary" name="orderId"
                                            value="${order.getOrderId()}"><fmt:message
                                            key="personal_account.more_info"/>
                                    </button>
                                </form>
                            </th>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </div>
</main>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
