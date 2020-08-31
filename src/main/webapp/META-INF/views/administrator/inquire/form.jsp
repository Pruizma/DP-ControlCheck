<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.inquire.form.label.title" path="title"/>
	
	<jstl:if test="${command != 'create' }">
				<acme:form-textbox code="administrator.inquire.form.label.creation" path="creation" readonly="true"/>
	</jstl:if>
	<acme:form-moment code="administrator.inquire.form.label.deadline" path="deadline"/>
	<acme:form-textbox code="administrator.inquire.form.label.description" path="description"/>
	<acme:form-money code="administrator.inquire.form.label.minMoney" path="moneyMin"/>
	<acme:form-money code="administrator.inquire.form.label.maxMoney" path="moneyMax"/>
	<acme:form-textbox code="administrator.inquire.form.label.email" path="email"/>
	
	<acme:form-submit test="${command == 'show' }" code="administrator.inquire.form.button.update" action="/administrator/inquire/update"/>
	<acme:form-submit test="${command == 'show' }" code="administrator.inquire.form.button.delete" action="/administrator/inquire/delete"/>
	<acme:form-submit test="${command == 'create' }" code="administrator.inquire.form.button.create" action="/administrator/inquire/create"/>
	<acme:form-submit test="${command == 'update' }" code="administrator.inquire.form.button.update" action="/administrator/inquire/update"/>
	<acme:form-submit test="${command == 'delete' }" code="administrator.inquire.form.button.delete" action="/administrator/inquire/delete"/>
	
	<acme:form-return code="administrator.inquire.form.button.return"/>
</acme:form>