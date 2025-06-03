<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>도서 상세 정보</title>
  <link rel="stylesheet" href="<c:url value='/resources/css/book/book_detail.css'/>">
</head>
<body>

<jsp:include page="../../include/header.jsp" />
<jsp:include page="../include/nav_cookie.jsp" />

<section>
  <div id="section_wrap">
    <div class="word">
      <h3>BOOK DETAIL</h3>
    </div>

    <table>
      <tr>
        <td class="thumbnail-cell">
          <img src="${book.thumbnail}" alt="도서 이미지"/>
        </td>
        <th>도서명</th><td>${book.name}</td>
      </tr>
      <tr><th>저자</th><td>${book.author}</td></tr>
      <tr><th>발행처</th><td>${book.publisher}</td></tr>
      <tr><th>발행연도</th><td>${book.publishYear}</td></tr>
      <tr><th>ISBN</th><td>${book.isbn}</td></tr>
      <tr><th>청구기호</th><td>${book.callNumber}</td></tr>
      <tr>
        <th>대출가능</th>
        <td>
          <c:choose>
            <c:when test="${book.rentalAble}">대출가능</c:when>
            <c:otherwise>대출불가</c:otherwise>
          </c:choose>
        </td>
      </tr>
      <tr><th>등록일</th><td>${book.regDate}</td></tr>
      <tr><th>수정일</th><td>${book.modDate}</td></tr>
    </table>

    <br/>

    <div style="text-align: center;">
      <form method="get" action="<c:url value='/admin/book/modifyBookForm'/>" style="display:inline;">
        <input type="hidden" name="no" value="${book.no}">
        <input type="submit" value="도서 수정">
      </form>

      <form method="post" action="<c:url value='/admin/book/deleteBook'/>"
            onsubmit="return confirm('정말 삭제하시겠습니까?');" style="display:inline;">
        <input type="hidden" name="no" value="${book.no}">
        <input type="submit" value="도서 삭제">
      </form>
    </div>
  </div>
</section>

<jsp:include page="../../include/footer.jsp" />

</body>
</html>
