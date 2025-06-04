<%@ page import="daelim.book.rental.minyoung.user.account.UserAccountVo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="../include/title.jsp"/>
    <link href="<c:url value='/resources/css/user/modify_account_form.css' />" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<jsp:include page="nav_user.jsp"/>

<section>
    <div id="section_wrap">
        <div class="word">
            <h3>MODIFY ACCOUNT FORM</h3>
        </div>
        <%
            UserAccountVo loginedUser = (UserAccountVo) session.getAttribute("loginedUser");
        %>
        <div class="modify_account_form">
            <form action="<c:url value='/user/account/modifyAccountConfirm' />" name="modify_account_form" method="post">
                <input type="hidden" name="no" value="<%= loginedUser.getNo() %>">
                <input type="text" name="id" value="<%= loginedUser.getId() %>" readonly disabled> <br>
                <input type="password" value="********" readonly disabled> <br>
                <input type="text" name="name" value="<%= loginedUser.getName() %>" placeholder="INPUT USER NAME."> <br>
                <select name="gender">
                    <option value="">SELECT USER GENDER.</option>
                    <option value="M" <% if ("M".equals(loginedUser.getGender())) { %> selected <% } %>>Man</option>
                    <option value="W" <% if ("W".equals(loginedUser.getGender())) { %> selected <% } %>>Woman</option>
                </select> <br>
                <input type="email" name="email" value="<%= loginedUser.getEmail() %>" placeholder="INPUT USER EMAIL."><br>
                <input type="text" name="phone" value="<%= loginedUser.getPhone() %>" placeholder="INPUT USER PHONE."> <br>
                <input type="button" value="modify account" onclick="modifyAccountForm();">
                <input type="reset" value="reset">
            </form>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp"/>

<script type="text/javascript">
    function modifyAccountForm() {
        console.log('modifyAccountForm() CALLED!!');
        let form = document.modify_account_form;
        if (form.name.value === '') {
            alert('INPUT USER NAME.');
            form.name.focus();
        } else if (form.gender.value === '') {
            alert('SELECT USER GENDER.');
            form.gender.focus();
        } else if (form.email.value === '') {
            alert('INPUT USER EMAIL.');
            form.email.focus();
        } else if (form.phone.value === '') {
            alert('INPUT USER PHONE.');
            form.phone.focus();
        } else {
            form.submit();
        }
    }
</script>
</body>
</html>
