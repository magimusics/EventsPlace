<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Registration</title>
</head>
<body>

<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var="isUSer"/>
<c:if test="${isUSer}">
    <c:redirect url="/index.html"/>
</c:if>

Регистрация

<form name="signup" action="/signup" method="post" accept-charset="utf-8">
    <label >Name</label>
    <input name="username" required autofocus/>
</p>
    <label>Password</label>
    <input type="password" name="password"/>
    </p>

    <label>Description</label>
    <textarea type="submit" name="description"></textarea>
    <br/><br/>
    <button>Submit</button>

    <br/>
    <br/>
    <br/>
</form>
</body>
</html>
