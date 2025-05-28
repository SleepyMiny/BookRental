<%@page import="daelim.book.rental.minyoung.admin.AdminAccountVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value='/resources/css/admin/include/nav.css' />" rel="stylesheet" type="text/css">

<jsp:include page="./nav_js.jsp" />

<nav>

    <div id="nav_wrap">

        <%
            AdminAccountVo loginedAdminAccountVo = (AdminAccountVo) session.getAttribute("loginedAdminMemberVo");
            if (loginedAdminAccountVo != null) {
        %>

        <div class="menu">
            <ul>
                <li><a href="<c:url value="admin/account/logoutConfirm" /> ">로그아웃</a></li>
                <li><a href="<c:url value="admin/admin/account/createAccountForm" />">계정수정</a></li>

                <c:if test="${loginedAdminMemberVo.id eq 'system'}">
                    <li><a href="">관리자목록</a></li>
                </c:if>

                <li><a href="">대출도서</a></li>
                <li><a href="">전체도서</a></li>
                <li><a href="">희망도서(입고처리)</a></li>
                <li><a href="">도서등록</a></li>
            </ul>
        </div>
        <%
        } else {
        %>
        <div class="menu">
            <ul>
                <li><a href="<c:url value='/admin/account/loginForm' />">로그인</a></li>
                <li><a href="<c:url value='/admin/account/createAccountForm' />">회원가입</a></li>
            </ul>
        </div>
        <%
            }
        %>

        <div class="search">

            <form action="" name="search_book_form" method="get">
                <input type="text" name="b_name" placeholder="Enter the name of the book you are looking for.">
                <input type="button" value="search" onclick="searchBookForm();">
            </form>

        </div>
    </div>

</nav>