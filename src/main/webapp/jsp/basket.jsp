<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>
<html>
<head>
    <title><fmt:message key="basket_page.title"/></title>

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
                <c:if test="${baskets.size() == 0}">
                    <p>Empty</p>
                </c:if>
                <c:if test="${baskets.size() != 0}">
                    <c:forEach var="userBasketProduct" items="${baskets}">
                        <div class="col-sm-4">
                            <div class="card text-white bg-secondary card-margin-bottom">
                                <div>
                                    <img style="object-fit: cover; height: 200px" class="card-img-top"
                                         src="image/${userBasketProduct.getProduct().getImage().getName()}.jpg"
                                         alt="Card image cap">
                                </div>
                                <div class="card-body text-center shadow">
                                    <h5 class="card-title">${userBasketProduct.getProduct().getTitle()}</h5>
                                    <form method="post" action="controller">
                                        <input type="hidden" name="commandName" value="product_page">
                                        <button class="btn btn-light btn-block nav-link" name="productId"
                                                value="${userBasketProduct.getProduct().getProductId()}"><fmt:message
                                                key="basket_page.product_page_button"/>
                                        </button>
                                    </form>
                                    <form method="post" action="controller">
                                        <input type="hidden" name="commandName"
                                               value="delete_product_from_basket_command">
                                        <button class="btn btn-block btn-danger nav-link" name="productId"
                                                value="${userBasketProduct.getProduct().getProductId()}">
                                            <fmt:message
                                                    key="basket_page.remove_product_button"/>
                                        </button>
                                    </form>
                                </div>
                                <div class="card-footer text-muted">
                                    2 days ago
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${totalPrice != 0}">
                    <p><fmt:message key="basket_page.total_price"/> ${totalPrice}</p>
                </c:if>
                <form method="post" action="controller">
                    <input type="hidden" name="commandName"
                           value="create_order_command">
                    <button class="btn btn-secondary" name="products"
                            value="${baskets}">
                        <fmt:message
                                key="basket_page.remove_product_button"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</main>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
