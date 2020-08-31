<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.inquire.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.inquire.form.label.creation" path="creation"/>
	<acme:form-moment code="authenticated.inquire.form.label.deadline" path="deadline"/>
	<acme:form-textbox code="authenticated.inquire.form.label.description" path="description"/>
	<acme:form-money code="authenticated.inquire.form.label.minMoney" path="moneyMin"/>
	<acme:form-money code="authenticated.inquire.form.label.maxMoney" path="moneyMax"/>
	<acme:form-textbox code="authenticated.inquire.form.label.email" path="email"/>
	
	<acme:form-return code="authenticated.inquire.form.button.return"/>
</acme:form>