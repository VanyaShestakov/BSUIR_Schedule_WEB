
<%--
  Created by IntelliJ IDEA.
  User: IvanT
  Date: 15.04.2021
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

    <title>JSON Error</title>
</head>
<body>
<%
    String message = pageContext.getException().getMessage();
    String exception = pageContext.getException().getClass().toString();
%>
<h2>Reading JSON error</h2>
<p>Type: <%= exception%></p>
<p>Message: <%= message %></p>
</body>
</html>
