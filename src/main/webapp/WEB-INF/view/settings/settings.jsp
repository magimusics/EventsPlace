<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Настройки страницы</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap.min.css">
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
                    $("#info").html("<div class=\"alert alert-success\" role=\"alert\">Все успешно сохранилось</div>");
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
            myData.name = $("#firstname").val();
            myData.lastname = $("#lastname").val();
            myData.country = $("#country").val();
            myData.city = $("#city").val();
            myData.occupation = $("#occupation").val();
            myData.bDay = $("#day").val();
            myData.bMonth = $("#month").val();
            myData.bYear = $("#year").val();
            myData.description = $("#description").val();
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
            </li>
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
    <div id="info"></div>
    <form id="settings" accept-charset="utf-8" action="javascript:void(null);" onsubmit="addInformation()">
        <input id="firstname" class="form-control" type="text" value="${user.firstname}" placeholder="Ваше имя"/>
        <br>
        <input id="lastname" class="form-control" style="width: 200px" type="text" value="${user.lastname}" placeholder="Ваша фамилия"/>
        <br>
        <input id="country" class="form-control" type="text" value="${user.country}" placeholder="Страна"/>
        <br>
        <input id="city" class="form-control" type="text" value="${user.city}" placeholder="Город"/>
        <br>
        <input id="occupation" class="form-control" type="text" value="${user.occupation}" placeholder="Род занятий"/>
        <br>
        <input type="file" id="photo">Загрузить фото
        <br>
        Дата рождения
        <br>
        <select id="day">
            <option>День</option>
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
            <option>6</option>
            <option>7</option>
            <option>8</option>
            <option>9</option>
            <option>10</option>
            <option>11</option>
            <option>12</option>
            <option>13</option>
            <option>14</option>
            <option>15</option>
            <option>16</option>
            <option>17</option>
            <option>18</option>
            <option>19</option>
            <option>20</option>
            <option>21</option>
            <option>22</option>
            <option>23</option>
            <option>24</option>
            <option>25</option>
            <option>26</option>
            <option>27</option>
            <option>28</option>
            <option>29</option>
            <option>30</option>
            <option>31</option>
        </select>
        <select id="month">
            <option>Месяц</option>
            <option value="1">Январь</option>
            <option value="2">Февраль</option>
            <option value="3">Март</option>
            <option value="4">Апрель</option>
            <option value="5">Май</option>
            <option value="6">Июнь</option>
            <option value="7">Июль</option>
            <option value="8">Август</option>
            <option value="9">Сентябрь</option>
            <option value="10">Октябрь</option>
            <option value="11">Ноябрь</option>
            <option value="12">Декабрь</option>
        </select>
        <input id="year" type="text" value placeholder="Год"/>
        <br>
        <br>
        <textarea type="submit" class="form-control" id="description" placeholder="Описание" style="resize: none">${user.description}</textarea>
        <br>
        <input type="submit" class="btn btn-default" value="Сохранить">
        </form>
</div>


</body>
</html>
