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
<c:if test="${not isUSer}">
    <c:redirect url="/index.html"/>
</c:if>

<div id="block1">
    <div class="block2">
        <ul class="refers">
            <li class="lt">
                <security:authentication property="principal.username"/>
            </li>
            <li class="lt"><b><a class="hh" href="/signout">Выйти</a> </b>
            </li>
        </ul>
    </div>
</div>

<div class="sbl">
        <h7>Здравствуйте!</h7>
</div>

<div class="thbl">
    <c:if test="${not empty userinfo}">
        Hello! I am

        <c:if test="${userinfo !='true' and userinfo != 'false'}">

        <font color="green"><b>${userinfo.firstname} <c:out value=" "/> ${userinfo.lastname}</b></font>
            <hr>
            ${userinfo.description}<br>
            ${userinfo.country} <c:out value=" "/> ${userinfo.city}<br>
            ${userinfo.occupation}
        </c:if>
    </c:if>
    <br>
    <br>

</div>

</html>
