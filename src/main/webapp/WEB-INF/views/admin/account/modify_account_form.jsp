<%@ page import="daelim.book.rental.minyoung.admin.AdminAccountVo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="../../include/title.jsp"/>
    <link href="<c:url value='/resources/css/admin/modify_account_form.css' />" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="../../include/header.jsp"/>
<jsp:include page="../include/nav.jsp"/>
<section>
    <div id="section_wrap">
        <div class="word">
            <h3>MODIFY ACCOUNT FORM</h3>
        </div>
        <%
            AdminAccountVo loginedAdminAccountVo = (AdminAccountVo) session.getAttribute("loginedAdminAccountVo");
        %>
        <div class="modify_account_form">
            <form action="<c:url value='/admin/account/modifyAccountConfirm' />" name="modify_account_form"
                  method="post">
                <input type="hidden" name="no" value="<%= loginedAdminAccountVo.getNo() %>">
                <input type="text" name="id" value="<%= loginedAdminAccountVo.getId() %>" readonly disabled> <br>
                <input type="password" name="password" value="******" readonly disabled> <br>
                <input type="text" name="name" value="<%= loginedAdminAccountVo.getName() %>"
                       placeholder="INPUT USER NAME."> <br>
                <select name="gender">
                    <option value="">SELECET USER GENDER.</option>
                    <option value="M" <% if (loginedAdminAccountVo.getGender().equals("M")) {%> selected <%}%>>Man
                    </option>
                    <option value="W" <% if (loginedAdminAccountVo.getGender().equals("W")) {%> selected <%}%>>Woman
                    </option>
                </select> <br>
                <input type="text" name="part" value="<%= loginedAdminAccountVo.getPart() %>"
                       placeholder="INPUT USER PART."><br>
                <input type="text" name="position" value="<%= loginedAdminAccountVo.getPosition() %>"
                       placeholder="INPUT USER POSITION."><br>
                <input type="email" name="email" value="<%= loginedAdminAccountVo.getEmail() %>"
                       placeholder="INPUT USER MAIL."><br>
                <input type="text" name="phone" value="<%= loginedAdminAccountVo.getPhone() %>"
                       placeholder="INPUT USER PHONE."> <br>
                <input type="button" value="modify account" onclick="modifyAccountForm();">
                <input type="reset" value="reset">
            </form>
        </div>
    </div>
</section>
<jsp:include page="../../include/footer.jsp"/>
<script type="text/javascript">
    function modifyAccountForm() {
        console.log('modifyAccountForm() CALLED!!');
        let form = document.modify_account_form;
        if (form.name.value == '') {
            alert('INPUT ADMIN NAME.');
            form.name.focus();
        } else if (form.gender.value == '') {
            alert('SELECET ADMIN GENDER.');
            form.gender.focus();
        } else if (form.part.value == '') {
            alert('INPUT ADMIN PART.');
            form.part.focus();
        } else if (form.position.value == '') {
            alert('INPUT ADMIN POSITON.');
            form.position.focus();
        } else if (form.email.value == '') {
            alert('INPUT ADMIN MAIL.');
            form.email.focus();
        } else if (form.phone.value == '') {
            alert('INPUT ADMIN PHONE.');
            form.phone.focus();
        } else {
            form.submit();
        }
    }
</script>
</body>
</html>