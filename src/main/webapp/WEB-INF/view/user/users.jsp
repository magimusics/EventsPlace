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
    <link rel="stylesheet" href="/resources/datepicker.min.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="/resources/js/datepicker.js"></script>

    <script>
        function checkKey(e) {
            var inp = document.getElementById("usersearch")
            if(e.keyCode == "13") {
                getAllUsers();

            }
        }

        function showUsers(myData) {
            document.getElementById("user_results").innerHTML="";
            for(i = 0; i < myData.length; i++){
                document.getElementById("user_results").innerHTML+="<div class=\"main-item-event\"><div class=\"event-cover\">" +
                        "photo</div><div class=\"event-inf\"><p><a href=\"/profile"+myData[i].id+"\">"+myData[i].firstname+" "+
                        myData[i].lastname+"</a></p><p>"+myData[i].city+"</p></div></div>"

            }
        }

        function getAllUsers() {
            var myData = "search_query="+document.getElementById("usersearch").value;
            $.ajax({
                url: "/all_users/",
                type: "POST",
                data: myData,
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
    <input type = 'button' value = 'Забить' class = 'btn' onclick="getAllUsers()">
</div>
    <div class="user_results" id="user_results">

    </div>
</div>
</body>
</html>
