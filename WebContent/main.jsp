<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<h1>Hello, <%=request.getParameter("username")%>!</h1>
<form method="get" action="MainServlet">
	<p>Select where do you want go</p>
	<input type="radio" name="nextFile" value="StudentServlet"> Students <br>
	<input type="radio" name="nextFile" value="facultyServlet"> Faculties <br> <br>
	<input type="submit" name="submit" value="GO">
</form>
</body>
</html>