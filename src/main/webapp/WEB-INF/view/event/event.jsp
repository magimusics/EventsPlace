<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/style.css">
    <link rel="stylesheet" href="/resources/style-meeting.css">
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script>
        function sendData(eventId) {
            $.ajax({
                url: "/addparticipant/",
                type: "POST",
                contentType : "application/json",
                mimeType: 'application/json',
                data: eventId,
                dataType: 'json',
                async: true,
                success: function (dat) {
                    location.reload();
                },
                error: function (xhr, status, error) {
                    alert(eventId);
                    alert(error);
                    alert(xhr);
                    alert(status);
                }
            });
        }
        function addParticipant() {
            var ev = new Object();
            ev.id = ${event.id};
            ev.command = 1;
            sendData(JSON.stringify(ev));
        }

        function getAway() {
            var ev = new Object();
            ev.id = ${event.id};
            ev.command = 0;
            sendData(JSON.stringify(ev));
        }
    </script>
    <title>${event.eventName}</title>
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
        <li><a href = '/users'>Мои друзья</a></li>
        <li><a href = '#'>Мои сообщения</a></li>
        <li><a href = "/events">События</a></li>
        <li><a href = "/events?">Мои события</a></li>
        <li><a href = '/create'>Создать событие</a></li>
    </ul>
</div>
<div class = 'big_show_meet'>
    <div class="sheventwrap">
        <div class="show_event_name">
            ${event.eventName}
        </div>
        <hr/>
        <div class="kv_wrapper">
            <div class="show_key">
                Место встречи:
            </div>
            <div class="show_value">
                ${event.place}
            </div>
        </div>
        <div class="kv_wrapper">
            <div class="show_key">
                Описание:
            </div>
            <div class="show_value">
                ${event.description}
            </div>
        </div>

        <div class="kv_wrapper">
            <div class="show_key">
                Возраст:
            </div>
            <div class="show_value">
                от ${event.ageMIN} до ${event.ageMAX}
            </div>
        </div>

        <div class="kv_wrapper">
            <div style="width: auto" class="show_key">
                ${event.calendar.getTime()}
            </div>
        </div>

        <div class="kv_wrapper">
            <div class="show_key">
                Количество до:
            </div>
            <div class="show_value">
                ${event.amount}
            </div>
        </div>
    </div>
    <div class="shpartwrap">
        <div class="takepart">
            <div class="btnwrap">
                <c:if test="${contain}">
                    <input type = 'button' value = 'Забить' class = 'btn' onclick="getAway()">
                </c:if>
                <c:if test="${not contain}">
                    <input type = 'button' value = 'Учавствовать' class = 'btn' onclick="addParticipant()">
                </c:if>
            </div>
        </div>
        <div class="participants">
            <c:forEach var="i" items="${users}">
                <div class="participant">
                    <a href="/profile${i.id}">${i.firstname} ${i.lastname}</a>
                </div>
            </c:forEach>
            <div class="participant">
                <a href="/sdsd" >Rest</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
