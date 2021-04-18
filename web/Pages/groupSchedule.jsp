<%@ page import="Schedule.BSUIRSchedule" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Schedule.BSUIRLesson" %>
<%@ page import="Controller.Tools.HTMLConverter" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.LinkedList" %><%--
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
                    <input id="active" class="page_button" style="width: auto; outline: none; border: none;" type="submit" value="Расписание группы"/>
                </form>
            </li>
            <li><a href="safety.html">Расписание преподавателей</a></li>
            <li><a href="hills.html">Списки факультетов</a></li>
            <li><a href="shops.html">Экзамены</a></li>
        </ul>

    </nav>

    <div id="content">
        <h1 align="center" >РАСПИСАНИЕ ГРУППЫ</h1>
        <div id="content1">
        <%
            BSUIRSchedule schedule = new BSUIRSchedule( request.getParameter("groupNumber"));
            ArrayList<ArrayList<BSUIRLesson>> list = schedule.getScheduleList();
            int currentWeek = schedule.getCurrentWeek();
            HashSet<Integer> usedWeeks = new HashSet<>();
            do {
                usedWeeks.add(currentWeek);
                out.println("<br><h2>" + currentWeek + "</h2>");
                for (int i = 0; i < list.size(); i++) {
                    out.println("<br><h2>" + list.get(i).get(0).getWeekDay() + "</h2>");

                    for (int j = 0; j < list.get(i).size(); j++) {
                        BSUIRLesson currLesson = list.get(i).get(j);
                        if (currLesson.getWeeks().contains(currentWeek)){
                            out.println("<div id=\"content2\">\n" + "<h1>" + currLesson.getSubjectName() + "  (" +
                                    currLesson.getType()  + ")    "  + currLesson.getTime() + "</h1>" +
                                    " <p class=\"text\">Аудитория: " + currLesson.getAuditory() + "</p>" +
                                    " <p class=\"text\">Преподаватель: " + currLesson.getTeacher() + "</p>" + "</div>");
                        }
                    }
                }
                currentWeek++;
                currentWeek = currentWeek > 4 ? 1 : currentWeek ;
            }while (!usedWeeks.contains(currentWeek));
        %>
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

        <div class="author" > <h3> Created by Shestakov Ivan</h3> </div>
    </footer>

</div>
</body>
</html>
