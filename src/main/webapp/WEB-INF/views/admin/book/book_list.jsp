<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <jsp:include page="../../include/title.jsp" />
  <link rel="stylesheet" href="<c:url value='/resources/css/book/book_list.css'/>" type="text/css">
</head>
<body>

<jsp:include page="../../include/header.jsp"/>
<jsp:include page="../include/nav_cookie.jsp" />

<section>
  <div id="section_wrap">
    <div class="word">
      <h3>FULL LIST OF BOOKS</h3>
    </div>

    <table>
      <thead>
      <tr>
        <th>도서명</th>
        <th>저자</th>
        <th>발행처</th>
        <th>발행연도</th>
        <th>ISBN</th>
        <th>청구기호</th>
        <th>대출가능</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="book" items="${bookList}">
        <tr>
          <td>
            <a href="<c:url value='/admin/book/bookDetail?no=${book.no}'/>">
                ${book.name}
            </a>
          </td>
          <td>${book.author}</td>
          <td>${book.publisher}</td>
          <td>${book.publishYear}</td>
          <td>${book.isbn}</td>
          <td>${book.callNumber}</td>
          <td><c:choose>
            <c:when test="${book.rentalAble}">대출가능</c:when>
            <c:otherwise>대출불가</c:otherwise>
          </c:choose></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</section>

<jsp:include page="../../include/footer.jsp"/>
</body>
</html>
