<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="css" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="bank.css" type="text/css">
<meta charset="ISO-8859-1">
<title>Rabobank </title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/result" enctype="multipart/form-data" method="POST" >
		<table>
		<tr>
		<td><h4>Customer Statement:</h4></td>
		<td><input type="file" name="file"></td>
		</tr>
		<tr><td><input type="submit" value="Upload"></td></tr>
		</table>
	</form>
</body>
</html>