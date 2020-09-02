<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<jstl:if test="${command == 'show' }">
	<acme:form readonly="true">
		
		<acme:form-submit code="investor.application.form.button.create" action = "/investor/offer/create?id=${id}"/>
		<acme:form-return code="investor.application.form.button.return" />
	</acme:form>
</jstl:if>
<jstl:if test="${command == 'create' }">
	<acme:form>
		
		<acme:form-return code="investor.offer.form.button.return" />
	</acme:form>
</jstl:if>