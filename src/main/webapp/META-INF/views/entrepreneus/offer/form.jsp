<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<jstl:if test="${aux == true}">
<acme:form readonly = "true">
		<acme:form-textbox code="entrepreneus.offer.form.label.title" path="title" />
		<jstl:if test="${link != ''}">
		<acme:form-url code="entrepreneus.offer.form.label.link" path="link" />
		</jstl:if>
		<acme:form-return code="entrepreneus.application.form.button.return" />
</acme:form>
</jstl:if>
<jstl:if test="${aux != true}">
<acme:form>		
		
		<acme:form-textbox code="entrepreneus.offer.form.label.passTextBox" path="passTextbox"/>
		
		<acme:form-submit code="entrepreneus.offer.form.button.update" action="/entrepreneus/offer/update?id=${id}"/>
		<acme:form-return code="entrepreneus.application.form.button.return" />
</acme:form>
</jstl:if>