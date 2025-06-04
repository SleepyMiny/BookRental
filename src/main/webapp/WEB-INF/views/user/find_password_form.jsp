<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <jsp:include page="../include/title.jsp"/>
  <link href="<c:url value='/resources/css/user/find_password_form.css' />" rel="stylesheet" type="text/css">
</head>
<body>

<jsp:include page="../include/header.jsp"/>
<jsp:include page="nav.jsp"/>

<section>
  <div id="section_wrap">
    <div class="word">
      <h3>FIND PASSWORD FORM</h3>
    </div>
    <div class="find_password_form">
      <form action="<c:url value='/user/account/findPasswordConfirm' />" method="post" name="find_password_form">
        <input type="text" name="id" placeholder="INPUT USER ID."> <br>
        <input type="text" name="name" placeholder="INPUT USER NAME."> <br>
        <input type="email" name="email" placeholder="INPUT USER MAIL."> <br>
        <input type="submit" value="find password">
        <input type="reset" value="reset">
      </form>
    </div>

    <div class="find_password_links">
      <a href="<c:url value='/user/account/createAccountForm' />">create account</a> |
      <a href="<c:url value='/user/account/loginForm' />">login</a>
    </div>
  </div>
</section>

<jsp:include page="../include/footer.jsp"/>

</body>
</html>
