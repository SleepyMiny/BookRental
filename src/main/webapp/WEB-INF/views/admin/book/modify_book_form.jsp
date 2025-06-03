<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="../../include/title.jsp" />
    <link href="<c:url value='/resources/css/book/book_form.css' />" rel="stylesheet" type="text/css">
</head>
<body>

<jsp:include page="../../include/header.jsp" />
<jsp:include page="../include/nav_cookie.jsp" />

<section>
    <div id="section_wrap">
        <div class="word">
            <h3>CREATE BOOK FORM</h3>
        </div>
        <div class="modify_book_form">
            <form action="<c:url value='/admin/book/modifyBook' />" method="post" name="modify_book_form">
                <input type="text" name="name" placeholder="INPUT BOOK NAME" required> <br>
                <input type="text" name="author" placeholder="INPUT AUTHOR" required> <br>
                <input type="text" name="publisher" placeholder="INPUT PUBLISHER" required> <br>
                <input type="text" name="publishYear" placeholder="INPUT PUBLISH YEAR (ex. 2023)" maxlength="4" required> <br>
                <input type="text" name="isbn" placeholder="INPUT ISBN" required> <br>
                <input type="text" name="callNumber" placeholder="INPUT CALL NUMBER" required> <br>
                <select name="rentalAble" required>
                    <option value="">SELECT RENTAL STATUS</option>
                    <option value="1">ABLE</option>
                    <option value="0">UNABLE</option>
                </select> <br>
                <input type="submit" value="create book">
                <input type="reset" value="reset">
            </form>
        </div>
        <div class="book_list_link">
            <a href="<c:url value='/admin/book/getAllBooks' />">go to book list</a>
        </div>
    </div>
</section>

<jsp:include page="../../include/footer.jsp" />
</body>
</html>
