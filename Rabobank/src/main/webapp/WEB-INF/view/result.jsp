<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%--Importing all the dependent classes--%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.Record"%>
<%@page import="java.util.Iterator"%>
<%@ taglib prefix="css" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="bank.css" type="text/css">
<meta charset="ISO-8859-1">
<title>Rabobank Customer Statement</title>
</head>
<body>

	<table border="1px" style="border-style: solid;">
		<tr>
			<h3>Failed Records:</h3>
		</tr>
		<tr>
			<td><h4>Reference</h4></td>
			<td><h4>Account Number</h4></td>
			<td><h4>Description</h4></td>
		</tr>

		<%--Assigning List object containing failed records data to the local object --%>
		<% ArrayList<Record> records = (ArrayList) request.getAttribute("failedrecords"); %>

		<%
			// Iterating through list

			if (request.getAttribute("failedrecords") != null) // Null check for the object
			{
				Iterator<Record> iterator = records.iterator(); // Iterator interface

				while (iterator.hasNext()) // iterate through all the data until the last record
				{
					Record r = iterator.next(); //assign individual record to the record class object
		%>
		<tr>
			<td><%=r.getReference()%></td>
			<td><%=r.getAccountNumber()%></td>
			<td><%=r.getDescription()%></td>
		</tr>
		<%
			}
			}
		%>
	</table>

</body>
</html>