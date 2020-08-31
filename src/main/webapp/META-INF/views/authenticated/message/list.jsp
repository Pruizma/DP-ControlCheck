<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.message.form.label.messageTitle" path="messageTitle" width="20%"/>
	<acme:list-column code="authenticated.message.form.label.creationMoment" path="creationMoment" width="60%"/>
</acme:list>