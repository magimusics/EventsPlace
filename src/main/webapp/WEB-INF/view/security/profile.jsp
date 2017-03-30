<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <link rel="stylesheet" href="/resources/style.css">
</head>

<body>

<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var="isUSer"/>
<c:if test="${not isUSer}">
    <c:redirect url="/index.html"/>
</c:if>

<div class = 'header'>
    <ul class  = 'up_navigation'>
        <li><a href = '#'>VPiska</a></li>
        <li><input type = 'text'></li>
        <li><a href = "/people">Люди</a></li>
        <li><a href = "/help">Помощь</a></li>
        <li><a href = "/signout">Выйти</a></li>
    </ul>
</div>
<div class = 'info_big_region'>
    <div class = 'right_menu_div'>
        <ul class = 'right_menu_ul'>
            <li><a href = '/profile'>Моя страница</a></li>
            <li><a href = '/friends'>Мои друзья</a></li>
            <li><a href = '/ms'>Мои сообщения</a></li>
            <li><a href = '#'>Мои группы</a></li>
            <li><a href = '/events'>Мои события</a></li>
            <li><a href = '/create'>Создать событие</a></li>
        </ul>
    </div>
    <div class = 'info_region'>
        <div class = 'avatar'>

                <img src="<c:url value="${userinfo.img}"/>" width="300" height="300">

        </div>
        <div class = 'reducting'>
            <a href = '#'>Редактировать</a>
        </div>
        <div class = 'photo_video'>
            <table>
                <tr>
                    <td>
                        <form action="/profile" method="post"
                              enctype="multipart/form-data">
                            <input type="file" name="file"><br>
                            <input type="submit" value="Загрузить файл">
                        </form>
                    </td><td></td>
                </tr>
                <tr>
                    <td><a href = '#'>Мои фотки</a></td><td></td>
                </tr>
                <tr>
                    <td><a href = '#'>Мои видео</a></td><td></td>
                </tr>
            </table>
        </div>
        <div class = 'about_reg'>
            <table class = 'about_table' >
                <c:if test="${not empty userinfo}">
                <tr>
                    <td>Имя</td> <td>${userinfo.firstname} ${userinfo.lastname}</td>
                </tr>
                <tr>
                    <td>Пол</td>
                        <td>
                            <c:if test="${userinfo.enabled}">
                                Мужской
                            </c:if>
                            <c:if test="${not userinfo.enabled}">
                                 Женский
                            </c:if>
                        </td>
                </tr>
                <tr>
                    <td>Дата рождения</td> <td>${userinfo.bdate}</td>
                </tr>
                <tr>
                    <td>Местоположение</td><td>${userinfo.country} ${user.city}</td>
                </tr>
                <tr>
                    <td>Образование</td> <td>${userinfo.occupation}</td>
                </tr>
                <tr>
                    <td>Место работы</td><td>${userinfo.description}</td>
                </tr>
                </c:if>
            </table>
            <p>Подробная информация</p>
            <table class = 'about_table_full'>
                <tr>
                    <td><h4>Контактая информация</h4></td>
                </tr>
                <tr>
                    <td>Веб-сайт</td><td></td>
                </tr>
                <tr>
                    <td>Мобильный телефон</td><td></td>
                </tr>
                <tr>
                    <td><h4>Личная информация</h4></td>
                </tr>
                <tr>
                    <td>Увлечения</td><td>фыв</td>
                </tr>
                <tr>
                    <td>Любимая группа</td><td>фыв</td>
                </tr>
                <tr>
                    <td>Любимое бухло</td><td>фыв</td>
                </tr>
                <tr>
                    <td>Сколько девок трахнул</td><td>фыв</td>
                </tr>
                <tr>
                    <td>Рост, вес, длина</td><td>фыв</td>
                </tr>
            </table>
            <div>
                <p>Фото с моих вписок</p>
            </div>
        </div>

    </div>
</div>
</body>
</html>