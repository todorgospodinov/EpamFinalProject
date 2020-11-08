<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" uri="customTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>

<html>
<head>
    <title><fmt:message key="add_product.title"/></title>

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
        <div class="col-4 mx-auto my-lg-4 p-3 bg-light">
            <form method="post" action="download" enctype="multipart/form-data" autocomplete="off">
                <div>
                    <label><fmt:message key="add_product.product_title"/></label>
                    <input class="form-control" type="text" name="title"/><br/>
                </div>
                <div>
                    <label><fmt:message key="add_product.price"/></label>
                    <input class="form-control" type="text" name="price"/><br/>
                </div>
                <div>
                    <label><fmt:message key="add_product.description"/></label>
                    <input class="form-control" type="text" name="description"/><br/>
                </div>
                <div>
                    <label><fmt:message key="add_product.image"/></label>
                    <input type="file" name="content" accept="image/jpeg" required>
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
                                name="commandName" value="add_product_command">
                            <fmt:message key="add_product.add_button"/>
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
