<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/resources/mabdesign.css">
</head>
<body>

<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var="isUSer"/>

<div id="block1">
    <div class="block2">
        <ul class="refers">
            <li class="lt">Name</li>
            <li class="lt"><b><a class="hh" href="/signout">Выйти</a> </b>
            </li>
        </ul>
    </div>
</div>

<div id="block1">
    <div class="block2">
        <ul class="refers">
            <li class="lt">
                <c:if test="${isUSer}">
                <security:authentication property="principal.username"/>
                </c:if>
            </>
            <li class="lt"><b><a class="hh" href="/signout">Выйти</a> </b>
            </li>
        </ul>
    </div>
</div>

<div class="sbl">
    <c:if test="${not isUSer}">
    <c:if test="${empty param.error}">
        <h7>Вы не вошли в приложение</h7>
    </c:if>
</c:if>
    <c:if test="${isUSer}">
        <h7>Здравствуйте!</h7>
    </c:if>
</div>
<div class="thbl">
    <c:if test="${not isUSer}">
        <a style="color: Green;" href="<c:url value="/login"/>">Login</a>
    </c:if>
</div>

<c:if test="${not isUSer}">
    <c:redirect url="/login"/>
<li> <a style="color: Green;" href="<c:url value="/profile"/>">Check profile</a> </li>
</c:if>
</body>
</html>
