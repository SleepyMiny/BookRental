<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="../../include/title.jsp" />
    <link href="<c:url value='/resources/css/admin/create_account_form.css' />" rel="stylesheet" type="text/css">
    <jsp:include page="../include/create_account_form_js.jsp" />
</head>
<body>
<jsp:include page="../../include/header.jsp" />
<jsp:include page="../include/nav.jsp" />
<section>
    <div id="section_wrap">
        <div class="word">
            <h3>CREATE ACCOUNT FORM</h3>
        </div>
        <div class="create_account_form">
            <form action="<c:url value='/admin/account/createAccountConfirm' />" name="create_account_form" method="post">
                <input type="text" name="id" placeholder="INPUT ADMIN ID."> <br>
                <input type="password" name="password" placeholder="INPUT ADMIN PW."> <br>
                <input type="password" name="password_again" placeholder="INPUT ADMIN PW AGAIN."> <br>
                <input type="text" name="name" placeholder="INPUT ADMIN NAME."> <br>
                <select name="gender">
                    <option value="">SELECET ADMIN GENDER.</option>
                    <option value="M">Man</option>
                    <option value="W">Woman</option>
                </select> <br>
                <input type="text" name="part" placeholder="INPUT ADMIN PART."> <br>
                <input type="text" name="position" placeholder="INPUT ADMIN POSITION."> <br>
                <input type="email" name="email" placeholder="INPUT ADMIN MAIL." ><br>
                <input type="text" name="phone" placeholder="INPUT ADMIN PHONE."> <br>
                <input type="button" value="create account" onclick="createAccountForm();">
                <input type="reset" value="reset">
            </form>
        </div>
        <div class="login">
            <a href="<c:url value='/admin/account/loginForm' />">login</a>
        </div>
    </div>
</section>
<jsp:include page="../include/create_account_form_js.jsp" />
<jsp:include page="../../include/footer.jsp" />
</body>
</html>