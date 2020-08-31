<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.toolRecord.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.toolRecord.form.label.activitySector" path="activitySector"/>
	<acme:form-textbox code="authenticated.toolRecord.form.label.inventorName" path="inventorName"/>
	<acme:form-textarea code="authenticated.toolRecord.form.label.description" path="description"/>
	<acme:form-url code="authenticated.toolRecord.form.label.webSite" path="webSite"/>
	<acme:form-textbox code="authenticated.toolRecord.form.label.email" path="email"/>
	<acme:form-textbox code="authenticated.toolRecord.form.label.openSource" path="openSource"/>
	<acme:form-textbox code="authenticated.toolRecord.form.label.stars" path="stars"/>
	<acme:form-return code="authenticated.toolRecord.form.button.return"/>
</acme:form>