<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>
<html>
<head>
    <title><fmt:message key="catalog_page.title"/></title>

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
            <div class="row">
                <c:forEach var="product" items="${products}">
                    <div class="col-sm-4">
                        <div class="card text-white bg-secondary">
                            <div>
                                <img style="object-fit: cover; height: 200px" class="card-img-top"
                                     src="image/${product.getImage().getName()}.jpg"
                                     alt="Card image cap">
                            </div>
                            <div class="card-body">
                                <h5 style="text-align: center" class="card-title">${product.getTitle()}</h5>
                                <div class="form-row text-center">
                                    <div class="col-12">
                                        <form method="post" action="controller">
                                            <input type="hidden" name="commandName" value="product_page">
                                            <button class="btn btn-light nav-link" name="productId"
                                                    value="${product.getProductId()}"><fmt:message
                                                    key="catalog_page.product_button"/>
                                            </button>
                                        </form>
                                        <form method="post" action="controller">
                                            <input type="hidden" name="commandName"
                                                   value="add_product_to_basket_command">
                                            <button class="btn btn-light nav-link" name="productId"
                                                    value="${product.getProductId()}"><fmt:message
                                                    key="catalog_page.add_product_to_basket"/>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                                    <%--                                <c:if test="${role.equals('ADMIN')}">--%>
                                    <%--                                    <div class="form-row text-center">--%>
                                    <%--                                        <div class="col-12">--%>
                                    <%--                                            <button type="submit" class="btn btn-danger"--%>
                                    <%--                                                    name="commandName" value="product_page">--%>
                                    <%--                                                <fmt:message key="catalog_page.delete_product_button"/>--%>
                                    <%--                                            </button>--%>
                                    <%--                                        </div>--%>
                                    <%--                                    </div>--%>
                                    <%--                                </c:if>--%>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</main>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
