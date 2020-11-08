<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="property/local"/>

<html>
<head>
    <title><fmt:message key="message_page.title"/></title>
</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>
<main>
    <section class="masthead">
        <div class="intro-body">
            <div class="container">
                <c:if test="${userDeleteSuccessMessage}">
                    <h2><fmt:message key="message_page.user_delete_error"/></h2>
                </c:if>

                <c:if test="${userDeleteErrorMessage}">
                    <h2><fmt:message key="message_page.user_delete_success"/></h2>
                </c:if>

                <c:if test="${userBlockSuccessMessage}">
                    <h2><fmt:message key="message_page.user_block_success"/></h2>
                </c:if>

                <c:if test="${userBlockErrorMessage}">
                    <h2><fmt:message key="message_page.user_block_error"/></h2>
                </c:if>

                <c:if test="${userUnblockSuccessMessage}">
                    <h2><fmt:message key="message_page.user_unblock_success"/></h2>
                </c:if>

                <c:if test="${userUnblockErrorMessage}">
                    <h2><fmt:message key="message_page.user_unblock_error"/></h2>
                </c:if>

                <c:if test="${userConfirmRegistrationLetter}">
                    <h2><fmt:message key="message_page.user_confirm_letter"/></h2>
                </c:if>

                <c:if test="${userLoginBlocked}">
                    <h2><fmt:message key="message_page.user_login_blocked"/></h2>
                </c:if>

                <c:if test="${userSuccessConfirmRegistrationLetter}">
                    <h2><fmt:message key="message_page.user_success_confirm_registration_letter"/></h2>
                </c:if>

                <c:if test="${userCodeNotFound}">
                    <h2><fmt:message key="message_page.user_code_not_found"/></h2>
                </c:if>

                <c:if test="${userSuccessChangePassword}">
                    <h2><fmt:message key="message_page.user_success_change_password"/></h2>
                </c:if>

                <c:if test="${userErrorChangePassword}">
                    <h2><fmt:message key="message_page.user_error_change_password"/></h2>
                </c:if>

                <c:if test="${userSuccessChangePasswordLetter}">
                    <h2><fmt:message key="message_page.user_success_change_password_letter"/></h2>
                </c:if>

                <c:if test="${userEmailIsNotFound}">
                    <h2><fmt:message key="message_page.user_email_is_not_found"/></h2>
                </c:if>
            </div>
        </div>
    </section>
</main>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>