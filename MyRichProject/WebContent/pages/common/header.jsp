<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Standard JSF tag library declaration -->
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<f:subview id="svHeader">
	<h:panelGrid columns="2" cellpadding="0" cellspacing="0"
		styleClass="headerBg">
		<h:panelGrid columns="1" cellpadding="0" cellspacing="0"
			styleClass="logoPanel">
			<h:outputText
				value=""
				styleClass="logoText"></h:outputText>
		</h:panelGrid>
		<h:panelGrid columns="1" cellpadding="0" cellspacing="0"
			style="margin: auto 0;" width="660">
			<h:graphicImage value="/theme/images/header.png"></h:graphicImage>
		</h:panelGrid>
	</h:panelGrid>
</f:subview>