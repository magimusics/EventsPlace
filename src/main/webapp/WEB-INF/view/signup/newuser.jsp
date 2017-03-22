<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>New User</title>
</head>
<body>
<span style="color: #dc143c; ">${user.firstname} : </span>
<br>
${user.lastname}.
<br>
${user.bdate}oko
<br>
${user.city}
<br>
</body>
</html>
