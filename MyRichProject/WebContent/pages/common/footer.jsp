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
<f:subview id="svFooter">
	<rich:panel id="footerPanel" styleClass="footer">
		<h:outputText
			value="©2011 FOOTER"
			styleClass="footer"></h:outputText>
	</rich:panel>
</f:subview>