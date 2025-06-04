<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="../include/title.jsp" />
    <link href="<c:url value='/resources/css/user/create_account_form.css' />" rel="stylesheet" type="text/css">
    <jsp:include page="../admin/include/create_account_form_js.jsp" />
</head>
<body>
<jsp:include page="../include/header.jsp" />
<jsp:include page="../admin/include/nav.jsp" />
<section>
    <div id="section_wrap">
        <div class="word">
            <h3>USER SIGN UP FORM</h3>
        </div>
        <div class="create_account_form">
            <form action="<c:url value='/user/account/createAccountConfirm' />" name="create_account_form" method="post">
                <input type="text" name="id" placeholder="Enter your ID"> <br>
                <input type="password" name="pw" placeholder="Enter your password"> <br>
                <input type="password" name="pwAgain" placeholder="Re-enter your password"> <br>
                <input type="text" name="name" placeholder="Enter your name"> <br>
                <select name="gender">
                    <option value="">Select Gender</option>
                    <option value="M">Man</option>
                    <option value="W">Woman</option>
                </select> <br>
                <input type="email" name="email" placeholder="Enter your email"> <br>
                <input type="text" name="phone" placeholder="Enter your phone number"> <br>
                <input type="button" value="Sign Up" onclick="validateAndSubmit();">
                <input type="reset" value="Reset">
            </form>
        </div>
        <div class="login">
            <a href="<c:url value='/user/account/loginForm' />">Login</a>
        </div>
    </div>
</section>
<jsp:include page="create_account_form_js.jsp" />
<jsp:include page="../include/footer.jsp" />
</body>
</html>
