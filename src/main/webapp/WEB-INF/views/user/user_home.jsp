<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">

  <jsp:include page="../include/title.jsp" />

  <link href="<c:url value='/resources/css/user/user_home.css' />" rel="stylesheet" type="text/css">

</head>
<body>

<jsp:include page="../include/header.jsp" />

<jsp:include page="nav.jsp" />

<section>

  <div id="section_wrap">

    <div class="word">

      <h3>사용자 홈 화면</h3>

    </div>

  </div>

</section>

<jsp:include page="../include/footer.jsp" />

</body>
</html>