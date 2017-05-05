<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>

    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <link rel="stylesheet" href="/resources/style.css">
    <link rel="stylesheet" href="/resources/style-meeting.css">
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>

    <script>
        function checkKey(e) {
            var inp = document.getElementById("usersearch")
            if(e.keyCode == "13") {
                sendM();

            }
        }

        function showUsers(myData) {
                document.getElementById("texta").innerHTML+=myData.username+": "+myData.mes+"\n";
        }

        function sendM() {
            var myData = "message="+document.getElementById("usersearch").value+"&userTo="+document.location.pathname;
            document.getElementById("usersearch").value="";
            $.ajax({
                url: "/sendmessage/",
                type: "POST",
                data: myData,
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                async: true,
                success: function (dat) {
                    console.log(JSON.stringify(dat));
                    showUsers(dat)
                },
                error: function (xhr, status, error) {
                    alert(myData);
                    alert(error);
                    alert(xhr);
                    alert(status);
                }
            });
        }

        $(document).ready(function pollForMessages() {
            $.ajax({url : "/mvc/chat", type : "POST",cache: false,
                success : function(messages) {
                    console.log(messages)
                    showUsers(messages)

                },
                error : function(xhr) {
                    if (xhr.statusText != "abort" && xhr.status != 503) {
                        //resetUI();
                        //console.error("Unable to retrieve chat messages. Chat ended.");
                    }
                },
                complete : pollForMessages
            });
            $('#usersearch').focus();
        });
    </script>
    <title>Пользователи</title>

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
<div style="min-height: 400px" class = 'big_show_meet'>
    <div class="searching">
        <input type="text" id="usersearch" class="input_search" onkeydown="checkKey(event)">
        <input type = 'button' value = 'Забить' class = 'btn' onclick="sendM()">
    </div>
    <div class="user_results" id="user_results">
        <textarea rows="15" cols="60" readonly="readonly" id="texta"></textarea>
    </div>
</div>
</body>
</html>
