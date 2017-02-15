<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/resources/mabdesign.css">
    <title>Login Page</title>
</head>
<body>

<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var="isUSer"/>




<c:if test="${isUSer}">
<c:redirect url="/index.html"/>
</c:if>

<div id="block1">
    <div class="block2">
        <ul class="refers">
            <li class="lt">Name</li>
            <li class="lt"><b><a class="hh" href="/signout">Выйти</a> </b>
            </li>
        </ul>
    </div>
</div>

<div class="sbl"><h7>Пожалуйста войдите</h7>
</div>

<div class="thbl">
<form name="form" action="signin" method="post" class="form-signin">

    <br/>
    <c:if test="${not empty param.error}">
        <font size="2" color="red"><b>Неправильный логин или пароль</b></font>
    </c:if>
    <br>

    <label for="inputEmail" class="sr-only"><spring:message code="email" text="Email"/></label>
    <input id="inputEmail" class="form-control" name="username"  required autofocus/>

    <br/>
    <label for="inputPassword" class="sr-only"><spring:message code="pass" text="Password"/></label>
    <input type="password" id="inputPassword" class="form-control" name="password" required/>

    <br/>

    <div class="checkbox">
        <label>
            <input type="checkbox" id="rememberme"  name="_spring_security_remember_me"/>Запомнить меня
        </label>
    </div>
    <input type="submit" value="Войти" class="btn btn-lg btn-primary btn-block" >
    <br/>
    <a href="javascript:history.back()">Назад</a>

    <br />
    <br />

    <a href="/signup">Зарегестрироваться!</a>

</form>
    </div>
</body>

</html>