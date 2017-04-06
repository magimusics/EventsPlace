<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>

    <link rel="stylesheet" href="/resources/style.css">
    <link rel="stylesheet" href="/resources/style-meeting.css">

    <title>Все события</title>

</head>
<body>
<div class = 'header'>
    <ul class  = 'up_navigation'>
        <li><a href = '#'>VPiska</a></li>
        <li><input type = 'text'></li>
        <li><a href = '#'>Люди</a></li>
        <li><a href = '#'>Помощь</a></li>
        <li><a href = '#'>Выйти</a></li>
    </ul>
</div>
<div class = 'right_menu_div'>
    <ul class = 'right_menu_ul'>
        <li><a href = "/profile">Моя страница</a></li>
        <li><a href = '#'>Мои друзья</a></li>
        <li><a href = '/users'>Мои сообщения</a></li>
        <li><a href = "/events">События</a></li>
        <li><a href = "/events?">Мои события</a></li>
        <li><a href = '/create'>Создать событие</a></li>
    </ul>
</div>
<div style="min-height: 400px" class = 'big_show_meet'>

    <c:if test="${eventList !='true' and eventList != 'false'}">
    <c:forEach var="i" items="${eventList}">
        <div class="main-item-event">
            <div class="event-cover">
                photo
            </div>
            <div class="event-inf">
                <p><a href="/event<c:out value="${i.id}"/>">${i.eventName}</a> </p>
                <p>${i.amount}</p>
            </div>
        </div>
        </c:forEach>
        </c:if>

</div>
</body>
</html>
