<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.garcia-roales-bulletin.list.label.birth" path="birth" width="25%"/>
	<acme:list-column code="anonymous.garcia-roales-bulletin.list.label.name" path="name" width="25%"/>
	<acme:list-column code="anonymous.garcia-roales-bulletin.list.label.surname" path="surname" width="25%"/>
	<acme:list-column code="anonymous.garcia-roales-bulletin.list.label.uvus" path="uvus" width="25%"/>
</acme:list>