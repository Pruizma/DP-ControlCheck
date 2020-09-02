<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="investor.offer.form.label.title" path="title" />
	<acme:form-url code="investor.offer.form.label.link" path="link" />
	<acme:form-checkbox code="investor.offer.form.label.passProt" path="passProt" />
	<acme:form-textbox code="investor.offer.form.label.pass" path="pass" />
	<acme:form-submit code="investor.offer.form.button.create" action="/investor/offer/create?id=${id}" />
	<acme:form-return code="investor.offer.form.button.return" />
</acme:form>