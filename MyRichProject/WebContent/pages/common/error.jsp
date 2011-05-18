<%@page isErrorPage="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Configured towards the login.jsp page -->
<link rel="stylesheet" type="text/css" href="../../theme/stylesheet.css">
<link rel="icon" type="image/gif" href="../../theme/images/favicon.ico" />
<title>Грешка</title>
</head>
<body>
<table style="font-size: 14pt;">
	<tr>
		<td><a href="login.jsf" style="font-weight: bold;">Вход в системата</a></td>
	</tr>
	<tr>
		<td style="font-weight: bold;">Възникна следната системна грешка:</td>
	</tr>
	<tr>
		<td><%=exception%></td>
	</tr>
</table>
</body>
</html>