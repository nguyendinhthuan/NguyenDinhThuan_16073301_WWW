<%@page import="Tuan04.Bai01.Student"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	Student sv = new Student();
	sv = (Student)request.getAttribute("student");
	out.println("First name:" + sv.getFirstname()
	+ "<br/> Last name: " + sv.getLastname()
	+ "<br/> Email : " + sv.getEmail()
	+ "<br/> Gender: " +sv.getGender()
	+ "<br/> Hobbies: " + sv.getHobbies()
	+ "<br/> Birthday: " + sv.getDay()
	);
	%>
</body>
</html>