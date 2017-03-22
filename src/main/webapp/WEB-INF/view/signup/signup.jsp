<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="/resources/mabdesign.css">
</head>
<body>

<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var="isUSer"/>
<c:if test="${isUSer}">
    <c:redirect url="/index.html"/>
</c:if>

<div style="margin: auto; width: 400px">
Регистрация

<form name="signup" action="/signup" method="post" accept-charset="utf-8">

    <input name="firstname" type="text" value="${user.firstname}" placeholder="Ваше имя"/>
</p>
    <input name="lastname" type="text" value="${user.lastname}" placeholder="Ваша фамилия"/>
    </p>
    <input name="email" type="text" value="${user.email}" placeholder="Электронная почта"/>
    </p>
    <input name="country" type="text" value="${user.country}" placeholder="Страна"/>
    </p>
    <input name="city" type="text" value="${user.city}" placeholder="Город"/>
    </p>
    <input name="occupation" type="text" value="${user.occupation}" placeholder="Род занятий"/>
    </p>
    <input type="file" name="photo">Загрузить фото
    <br>
Дата рождения
    <br>
    <select name="day">
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
    <select name="month">
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
    <input name="year" type="text" value placeholder="Год"/>
    <br>


    </p>
    <textarea type="submit" name="description" value="${user.description}" placeholder="Описание"></textarea><c:out value="${lop}"/>
    <br>
    Пол
    <p>
    <input type="radio" name="sex" value="male"/>Мужской
    <input type="radio" name="sex" value="female"/>Женский
    </p>
    <input type="password" name="password" value placeholder="Пароль"/>
    <br/>

    <c:if test="${not empty fillError}">
        <span style="color: crimson">
            СУКА, ВВЕДИ ВСЕ ДО КОНЦА!!!
        </span>
    </c:if>
    <br>
    <input type="submit" name="send" value="Зарегестрироваться!">

    <c:if test="${not empty param.send}">
        <c:set var="user.bdate" value="1991-01-08"/>
    </c:if>


</form>
</div>
</body>
</html>
