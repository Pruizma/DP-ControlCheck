<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<jstl:if test="${command == 'create'}">
	<acme:form>
		<acme:form-textbox code="investor.application.form.label.ticker" path="ticker" />
		<acme:form-money code="investor.application.form.label.moneyOffer" path="moneyOffer" />
		<acme:form-submit test="${command == 'create'}" code="investor.application.form.button.create"
			action="/investor/application/create?id=${id}" />
		<acme:form-return code="investor.application.form.button.return" />
	</acme:form>
</jstl:if>
<jstl:if test="${command == 'show' }">
	<acme:form readonly="true">
		<acme:form-textbox code="investor.application.form.label.ticker" path="ticker" />
		<acme:form-moment code="investor.application.form.label.moment" path="moment" />
		<acme:form-textbox code="investor.application.form.label.statement" path="statement" />
		<acme:form-textbox code="investor.application.form.label.justification" path="justification" />
		<acme:form-money code="investor.application.form.label.moneyOffer" path="moneyOffer" />
		<acme:form-money code="investor.application.form.label.investmentTicker" path="investmentTicker" />
		<acme:form-money code="investor.application.form.label.investorName" path="investorName" />
		<acme:form-submit test="${offer == true}" code="investor.offer.form.button.create" action="/investor/offer/create?id=${id}"
			method="get" />
		<acme:form-return code="investor.application.form.button.return" />
	</acme:form>
</jstl:if>