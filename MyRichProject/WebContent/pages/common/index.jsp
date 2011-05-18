<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Standard JSF tag library declaration -->
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!-- Tomahawk tag library declaration -->
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Configured towards the login.jsp page -->
<link rel="stylesheet" type="text/css" href="../theme/stylesheet.css">
<link rel="icon" type="image/gif" href="../theme/images/tabImg.png" />
<title>Начало</title>
</head>
<body>
<f:view>
	<a4j:form id="formIndex">
		<h:panelGrid columns="1" styleClass="outerPanel" id="outerPanel">
			<jsp:include page="header.jsp" />
			<jsp:include page="status.jsp" />
			<rich:panel styleClass="header" header="" headerClass="bodyHeader"
				style="height: 80px;">

			</rich:panel>
			<jsp:include page="footer.jsp" />
		</h:panelGrid>
	</a4j:form>
</f:view>
</body>
</html>