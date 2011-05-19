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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<f:subview id="svErrDialog">
	<a4j:outputPanel id="panelErrorModalPanel" ajaxRendered="true">
		<!-- Диалог за визуализиране на грешки-->
		<rich:modalPanel id="errorModalPanel" width="500"
			rendered="false" showWhenRendered="true">
			<f:facet name="header">
				<h:outputText value="Възникна следната грешка"
					style="padding-right:15px;" />
			</f:facet>
			<f:facet name="controls">
				<h:panelGroup>
					<h:graphicImage value="/theme/images/close.png" alt="Close"
						title="Close" styleClass="hidelink" id="hidelinkerrorModalPanel" />
					<rich:componentControl for="errorModalPanel"
						attachTo="hidelinkerrorModalPanel" operation="hide"
						event="onclick" />
				</h:panelGroup>
			</f:facet>
			<a4j:form id="formErrDialog">
				<h:panelGrid columns="2" width="95%">
					<h:graphicImage value="/theme/images/error.png" alt="Error"
						id="imgErr">
						<rich:componentControl for="errorModalPanel" attachTo="imgErr"
							operation="hide" event="onclick" />
					</h:graphicImage>
					<rich:messages infoClass="infoClass" errorClass="errorClass" />
				</h:panelGrid>
			</a4j:form>
		</rich:modalPanel>
	</a4j:outputPanel>
</f:subview>