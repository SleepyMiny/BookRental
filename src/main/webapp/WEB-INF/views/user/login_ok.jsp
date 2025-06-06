<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="../include/title.jsp" />
    <link href="<c:url value='/resources/css/admin/login_result.css' />" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="../include/header.jsp" />
<jsp:include page="nav_user.jsp" />
<section>
    <div id="section_wrap">
        <div class="word">
            <h3>LOGIN SUCCESS!!</h3>
        </div>
        <div class="others">
            <a href="<c:url value='/user/account/logoutConfirm' />">logout</a>
            <a href="<c:url value='/user/account/modifyAccountForm' />">modify account</a>
        </div>
    </div>
</section>
<jsp:include page="../include/footer.jsp" />
</body>
</html>