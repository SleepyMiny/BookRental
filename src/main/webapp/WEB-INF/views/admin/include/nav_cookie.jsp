<%@page import="daelim.book.rental.minyoung.admin.AdminAccountVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"  %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value='/resources/css/admin/include/nav.css' />" rel="stylesheet" type="text/css">

<jsp:include page="./nav_js.jsp" />

<nav>

    <div id="nav_wrap">
        <c:choose>
            <c:when test="${cookie.loginMember.value eq null}">
                <div class="menu">
                    <ul>
                        <li><a href="<c:url value='/admin/account/loginForm' />">로그인</a></li>
                        <li><a href="<c:url value='/admin/account/createAccountForm' />">회원가입</a></li>
                    </ul>
                </div>
            </c:when>
            <c:otherwise>
                <div class="menu">
                    <ul>
                        <li><a href="<c:url value='/admin/account/logoutConfirm' />">로그아웃(${cookie.loginMember.value}) </a></li>
                        <li><a href="<c:url value='/admin/account/modifyAccountForm' />">계정수정</a></li>

                        <c:if test="${cookie.loginMember.value eq 'system'}">
                            <li><a href="<c:url value='/admin/account/listupAdmin' />">관리자목록</a></li>
                        </c:if>

                        <li><a href="<c:url value='/book/account/getRentalBooks' />">대출도서</a></li>
                        <li><a href="<c:url value='/admin/book/getAllBooks' />">전체도서</a></li>
                        <li><a href="<c:url value='/book/account/getHopeBooks' />">희망도서(입고처리)</a></li>
                        <li><a href="<c:url value='/admin/book/modifyBookForm' />">도서등록</a></li>
                    </ul>
                </div>
            </c:otherwise>
        </c:choose>


        <div class="search">

            <form action="<c:url value='/book/admin/searchBookConfirm' />" name="search_book_form" method="get">
                <input type="text" name="b_name" placeholder="Enter the name of the book you are looking for.">
                <input type="button" value="search" onclick="searchBookForm();">
            </form>

        </div>
    </div>

</nav>