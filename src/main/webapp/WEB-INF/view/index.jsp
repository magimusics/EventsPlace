<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var="isUSer"/>


<c:if test="${not isUSer}">
    <li style="padding-top: 15px; padding-bottom: 15px; color: red">
        <c:if test="${empty param.error}">
            Вы не вошли в приложение
        </c:if>
    </li>
    <li> <a style="color: Green;" href="<c:url value="/login"/>">Login</a> </li>
</c:if>

<c:if test="${isUSer}">
    <li style="padding-top: 15px; padding-bottom: 15px; color: green">
        Вы вошли как:
        <security:authentication property="principal.username"/> с ролью:
        <b><security:authentication property="principal.authorities"/></b>

    </li>
    <li> <a style="color: red;" href="<c:url value="/signout"/>">Logout</a> </li>
</c:if>

HELLO
This is the first page in the project
<p/>
<c:if test="${isUSer}">
    <c:redirect url="/profile"/>
<li> <a style="color: Green;" href="<c:url value="/profile"/>">Check profile</a> </li>
</c:if>
</body>
</html>
