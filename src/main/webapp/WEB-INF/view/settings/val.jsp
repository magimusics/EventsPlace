<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Настройки страницы</title>
    <link rel="stylesheet" type="text/css" href="/resources/mabdesign.css">
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script>
        function sendMyData(myData) {
            $.ajax({
                url: "/sendajaxdata/",
                type: "POST",
                contentType : "application/json",
                mimeType: 'application/json',
                data: myData,
                dataType: 'json',
                async: true,
                success: function (dat) {
                    $("#seen").html("<tr><td>"+dat+"</tr></td>");
                },
                error: function (xhr, status, error) {
                    alert(myData);
                    alert(error);
                    alert(xhr);
                    alert(status);
                }
            });
        }

        function addInformation() {
            var myData = new Object();
            myData.firstname = $("#firstname").val();
            myData.lastname = $("#lastname").val();
            sendMyData(JSON.stringify(myData));
        }
    </script>
</head>
<body>

<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var="isUSer"/>
<c:if test="${not isUSer}">
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
    <c:if test="${isUSer}">
        <h7>Редактирование страницы</h7>
    </c:if>
</div>
<div class="thbl" style="height: auto">

        <input id="firstname" type="text" placeholder="Ваше имя"/>
        </p>
        <input id="lastname" type="text"  placeholder="Ваша фамилия"/>
        </p>
        <input type="submit" name="send" onclick="addInformation()" value="Ну ка проверим?">
    <table id="seen">

    </table>
</div>


</body>
</html>
