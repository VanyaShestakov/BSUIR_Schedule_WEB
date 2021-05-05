<%@ page import="Schedule.BSUIRSchedule" %>
<%@ page import="Schedule.BSUIRLesson" %>
<%@ page import="HTML.HTMLWriter" %>
<%@ page import="java.util.*" %>
<%--
  Created by IntelliJ IDEA.
  User: IvanT
  Date: 15.04.2021
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet";
          href="CSS/mainpage.css";
          type="text/css"/>

    <meta charset="utf-8">
    <title>Schedule</title>
</head>
<body>

<div id="page">
    <nav id="menu">
        <ul id="navbar">
            <li>
                <form action="BSUIRSchedule" method="GET" >
                    <input id="active" class="page_button" style="width: auto; outline: none;" type="submit" value="Расписание группы"/>
                </form>
            </li>
            <li>
                <form action="teachers" method="GET">
                    <input class="page_button" style="width: auto; outline: none; " type="submit" value="Преподаватели"/>
                </form>
            </li>
            <li><a href="hills.html">Списки факультетов</a></li>
            <li><a href="shops.html">Экзамены</a></li>
        </ul>

    </nav>

    <div id="content">
        <h1 align="center" >РАСПИСАНИЕ ГРУППЫ</h1>
        <div id="content1">
            <div class="lesson_container">
                <form action="BSUIRSchedule" method="POST" >
                    <center>
                        <h2>Номер группы:</h2> <input class="inputEdit" name="groupNumber" /><br><br>
                        <input class="get_button" style="width: auto; outline: none; border: none;" type="submit" value="ПОЛУЧИТЬ РАСПИСАНИЕ"/>
                    </center>
                </form>
            </div>
        ${schedule}
        </div>
    </div>

    <footer class="footer">

        <div class="footer-block">
            <h3>
                Social networks
            </h3>
            <ul class="footer-ul" >
                <li>
                    <a href="#" class="a-footer" ><img  src="../images/instagram.png" width="15px" > Instagram</a>
                </li>
                <li>
                    <a href="#" class="a-footer" ><img  src="../images/vk.png" width="15px" > ВКонтакте</a>
                </li>
                <li>
                    <a href="#" class="a-footer" ><img  src="../images/telegram.png" width="15px" > Telegram</a>
                </li>
            </ul>
        </div>
        <div class="footer-block">
            <h3>
                Contacts
            </h3>
            <ul class="footer-ul" >
                <li>
                    +375 (29) 584-35-78
                </li>
                <li>
                    г.Гомель, ул.Октябрьская 5/17; 246030
                </li>
                <li>
                    vanka1108@gmail.com
                </li>
            </ul>
        </div>
        <div class="footer-block">
            <h3>
                Github
            </h3>
        </div>

        <div class="author"><h3> Created by Shestakov Ivan</h3> </div>
    </footer>

</div>
</body>
</html>
