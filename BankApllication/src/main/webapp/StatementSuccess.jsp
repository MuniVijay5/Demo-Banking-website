<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Success Page</title>
</head>
<body>
<%
session=request.getsession();
out.println(session.getAttribute("sal"));
out.println("<br>");
out.println(session.getAttribute("ral"));
out.println("<br>");
out.println(session.getAttribute("al"));

%>
</body>
</html>