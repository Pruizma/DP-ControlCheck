<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="investor.application.form.label.ticker" path="ticker" />
	<jstl:if test="${command != 'create' }">
	<acme:form-moment code="investor.application.form.label.moment" path="moment" />
	<acme:form-textbox code="investor.application.form.label.statement" path="statement" />
	<acme:form-textbox code="investor.application.form.label.justification" path="justification" />
	</jstl:if>
	<acme:form-money code="investor.application.form.label.moneyOffer" path="moneyOffer" />
	<jstl:if test="${command != 'create' }">
	<acme:form-money code="investor.application.form.label.investmentTicker" path="investmentTicker" />
	<acme:form-money code="investor.application.form.label.investorName" path="investorName" />
	</jstl:if>
	<acme:form-submit test="${command == 'create'}" code = "investor.application.form.button.create" action = "/investor/application/create?id=${id}"/>
	<acme:form-return code="investor.application.form.button.return" />
</acme:form>