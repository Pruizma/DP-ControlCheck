<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.rmb.form.label.name" path="name"/>
	<acme:form-textbox code="anonymous.rmb.form.label.description" path="description"/>
	
	<acme:form-submit code="anonymous.rmb.form.button.create" action="/anonymous/ruiz-mateos-bulletin/create"/>
	<acme:form-return code="anonymous.rmb.form.button.return"/>
</acme:form>