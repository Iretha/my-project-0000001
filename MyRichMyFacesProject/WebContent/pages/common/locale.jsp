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
<f:subview id="svLocale">
	<a4j:form id="formLocale">
		<h:panelGrid binding="#{locale.localeGrid}"></h:panelGrid>

		<h:panelGrid columns="2">
			<a4j:commandLink value="ENG" action="#{locale.changeLocale}"
				title="ENG" reRender="outerPanel">
				<a4j:actionparam id="parameterEng" name="eng"
					assignTo="#{locale.selectedLocaleAbbr}" value="eng" />
			</a4j:commandLink>
			<a4j:commandLink value="BUL" action="#{locale.changeLocale}"
				title="BUL" reRender="outerPanel">
				<a4j:actionparam id="parameterBul" name="bul"
					assignTo="#{locale.selectedLocaleAbbr}" value="bul" />
			</a4j:commandLink>
		</h:panelGrid>

	</a4j:form>
</f:subview>