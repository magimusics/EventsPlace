<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>JDBC</title>
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

PIZDEC

<!-- Content Column -->
<br>
<a href="/jdbc">get all users</a>
<br>
<br>
<br>
<c:if test="${not empty resultUser}">
    Result:
    <c:if test="${resultUser == 'true'}">
        <font color="green"><b>${resultUser}</b></font>
    </c:if>
    <c:if test="${resultUser == 'false'}">
        <font color="red"><b>${resultUser}</b></font>
    </c:if>
    <c:if test="${resultUser !='true' and resultUser != 'false'}">
        <c:forEach var="i" items="${resultUser}">
        <p>${i.username}
        <c:out value=" password="/>
        ${i.password}</p>
        </c:forEach>
    </c:if>
</c:if>
<c:if test="${empty resultUser}">
    EMPTY!!!!!
</c:if>

</body>
</html>
