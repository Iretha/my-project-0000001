<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Standard JSF tag library declaration -->
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/user.js"></script>
<link rel="stylesheet" type="text/css" href="../theme/stylesheet.css">
<link rel="icon" type="image/gif" href="../theme/images/favicon.ico" />
<title>Вход</title>
</head>
<body onkeyup="clickOnEnter(event, 'formLogin:btnLogin');"
	onkeypress="clickOnEnter(event, 'formLogin:btnLogin');">
	<f:view>
		<h:panelGrid columns="1" styleClass="outerPanel" id="outerPanel">
			<jsp:include page="/pages/common/header.jsp" />
			<jsp:include page="/pages/common/status.jsp" />
			<rich:panel header="Вход">
				<a4j:form id="formLogin">
					<h:panelGrid columns="1">
						<h:outputText value="Потребителско име" />
						<h:inputText />
						<h:outputText value="Парола" />
						<h:inputSecret />
						<a4j:commandButton value="Вход" id="btnLogin"
							styleClass="commandButton" />
					</h:panelGrid>
					<rich:messages infoClass="infoClass" errorClass="errorClass" />
				</a4j:form>
			</rich:panel>
			<jsp:include page="/pages/common/footer.jsp" />
		</h:panelGrid>
		<jsp:include page="/pages/common/errorDlg.jsp" />
	</f:view>
</body>
</html>