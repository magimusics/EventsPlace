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
        function sendMyData(myData) {
            $.ajax({
                url: "/create/",
                type: "POST",
                contentType : "application/json",
                mimeType: 'application/json',
                data: myData,
                dataType: 'json',
                async: true,
                success: function (dat) {
                    console.log(dat)
                    location.replace(dat.event);
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
            myData.eventName = document.getElementById("eventName").value;
            myData.description = document.getElementById("description").value;
            myData.cover = document.getElementById("cover").value;
            myData.ageMIN = document.getElementById("ageMIN").value;
            myData.ageMAX = document.getElementById("ageMAX").value;
            myData.amount = document.getElementById("amount").value;
            var datepicker = document.getElementById("datepicker").value;
            myData.calendar = new Date(datepicker.replace(/(\d+).(\d+).(\d+)/, '$3/$2/$1'));
            myData.place = document.getElementById("place").value;
            sendMyData(JSON.stringify(myData));
        }
    </script>
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
<div class = 'big_create_meet'>
    <table class = 'create_meet_table' >
        <form>
            <tr>
                <td>Введите название события</td><td><input type = 'text' id="eventName"></td>
            </tr>
            <tr>
                <td>Введите местоположение</td><td><input type = 'text' id="place"></td>
            </tr>
            <tr>
                <td>Опишите событие</td><td><input type = 'textarea' id="description"></td>
            </tr>
            <tr>
                <td>Возрастные ограничение</td>
                <td>
                    Выберите возраст: <br>
                    От <select id="ageMIN">
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
                    <option>32</option>
                    <option>33</option>
                    <option>34</option>
                    <option>35</option>
                    <option>36</option>
                    <option>37</option>
                    <option>38</option>
                    <option>39</option>
                    <option>40</option>
                    <option>41</option>
                    <option>42</option>
                    <option>43</option>
                    <option>44</option>
                    <option>45</option>
                    <option>46</option>
                    <option>47</option>
                    <option>48</option>
                    <option>49</option>
                    <option>50</option>
                    <option>51</option>
                    <option>52</option>
                    <option>53</option>
                    <option>54</option>
                    <option>55</option>
                    <option>56</option>
                    <option>57</option>
                    <option>58</option>
                    <option>59</option>
                </select>
                    До <select id="ageMAX">
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
                    <option>32</option>
                    <option>33</option>
                    <option>34</option>
                    <option>35</option>
                    <option>36</option>
                    <option>37</option>
                    <option>38</option>
                    <option>39</option>
                    <option>40</option>
                    <option>41</option>
                    <option>42</option>
                    <option>43</option>
                    <option>44</option>
                    <option>45</option>
                    <option>46</option>
                    <option>47</option>
                    <option>48</option>
                    <option>49</option>
                    <option>50</option>
                    <option>51</option>
                    <option>52</option>
                    <option>53</option>
                    <option>54</option>
                    <option>55</option>
                    <option>56</option>
                    <option>57</option>
                    <option>58</option>
                    <option>59</option>
                </select>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="datepicker-here" id="datepicker" data-timepicker="true"></div>
                </td>
            </tr>
            <tr>
                <td>Ограничение по количеству участников:</td>
                <td>
                    до <input type = 'text' id="amount">
                </td>
            </tr>
            <tr>
                <td>Прикрепить фотку</td><td><input type = 'file' id="cover"></td>
            </tr>
        </form>
    </table>
    <input type = 'button' value = 'Создать' class = 'form_button' onclick="addInformation()">

</div>
</body>
</html>
