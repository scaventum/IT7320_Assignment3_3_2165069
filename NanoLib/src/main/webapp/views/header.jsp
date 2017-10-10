<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width,initial-scale=1.0">
	<title>NanoLib</title>
	<link rel="stylesheet" href="style.css">
	<script src="jquery-1.9.1.js"></script>
	<script src="jquery-ui.js"></script>
	<script>
	$(document).ready(function() {
		<% 
		if(request.getParameter("alert")!=null){
			String alert=request.getParameter("alert");
			if(alert.equals("inv_signin")){
				%>alert("Invalid User ID or Password");<%
			}else{
				%>alert("Alert");<%
			}
		}
		%>
	});
	</script>	
</head>
<body>

	<div id="header">
		<div id="title">NanoLib</div>
		<div id="time"><%= new java.text.SimpleDateFormat("E, dd-MMM-YYYY HH:mm").format(new java.util.Date()) %></div>
	</div>
	
	<% if(session.getAttribute("UserID")!=null){ %>
	<div id="menu">
		<ul>
			<!--<li>Master</li>-->
			<a href="borrow.jsp"><li>Borrow</li></a>
			<a href="return.jsp"><li>Return</li></a>
			<a href="check.jsp"><li>Check</li></a>
			<a href="signOut.jsp"><li>Logout [<%= session.getAttribute("UserID") %>]</li></a>
		</ul>
	</div>
	<% }%>
	
	<div class="vmargin" style="height:25px;"></div>
