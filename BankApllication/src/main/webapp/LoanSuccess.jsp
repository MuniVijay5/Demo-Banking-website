<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOan Success</title>
</head>
<body>
<%
session =request.getSession();
out.println("Dear,"+ session.getAttribute("name")+"Thank you for showing your intrest on loans from Our_Bank.");
		out.println("<br>");
		out.println("Our executive will contact you soon on your email address mentioned below: ");
		out.println("<br>");
		out.println(session.getAttribute("email"));
%>
</body>
</html>