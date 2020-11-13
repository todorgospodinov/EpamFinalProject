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
                    <label for="title"><fmt:message key="add_product.product_title"/></label>
                    <input id="title" class="form-control" required type="text" name="title"
                           maxlength="25" , minlength="2"
                           oninvalid="this.setCustomValidity('<fmt:message key="add_product.title_validator"/>')"
                           onchange="this.setCustomValidity('')"
                           pattern="^[^<>]{2,25}$"
                           title='<fmt:message key="add_product.title_validator"/>'><br/>
                </div>
                <div>
                    <label for="price"><fmt:message key="add_product.price"/></label>
                    <input id="price" class="form-control" type="text" name="price"
                           maxlength="7"
                           oninvalid="this.setCustomValidity('<fmt:message key="add_product.price_validator"/>')"
                           onchange="this.setCustomValidity('')"
                           pattern="^[1-9]\d{0,4}(\.\d{0,2})?$"
                           title='<fmt:message key="add_product.price_validator"/>'><br/>
                </div>
                <div>
                    <label for="description"><fmt:message key="add_product.description"/></label>
                    <input id="description" class="form-control" type="text" name="description" maxlength="1000"
                           oninvalid="this.setCustomValidity('<fmt:message key="add_product.description_validator"/>')"
                           onchange="this.setCustomValidity('')"
                           pattern="^[^<>]{1,1000}$"
                           title='<fmt:message key="add_product.description_validator"/>'><br/>
                </div>
                <div>
                    <label for="content"><fmt:message key="add_product.image"/></label>
                    <input id="content" type="file" name="content" accept="image/jpeg" required>
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
