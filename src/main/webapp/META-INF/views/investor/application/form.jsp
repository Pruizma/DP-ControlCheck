<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form-hidden path="investmentHasQuittel"/>

<jstl:if test="${command == 'create'}">
	<acme:form>
		<acme:form-textbox code="investor.application.form.label.ticker" path="ticker" />
		<acme:form-money code="investor.application.form.label.moneyOffer" path="moneyOffer" />
		<jstl:if test="${investmentHasQuittel != 'false'}">
		<acme:form-url code="investor.application.form.label.link" path="link" />
		<acme:form-textbox code="investor.application.form.label.passwordProtected" path="passwordProtected" />
		</jstl:if>
		<acme:form-submit test="${command == 'create'}" code="investor.application.form.button.create"
			action="/investor/application/create?id=${id}"/>
		<acme:form-return code="investor.application.form.button.return" />
	</acme:form>
</jstl:if>
<!-- 
<jstl:if test="${command == 'create' and investmentHasQuittel != 'true'}">
	<acme:form>
		<acme:form-textbox code="investor.application.form.label.ticker" path="ticker" />
		<acme:form-money code="investor.application.form.label.moneyOffer" path="moneyOffer" />
		<acme:form-submit test="${command == 'create'}" code="investor.application.form.button.create"
			action="/investor/application/create?id=${id}"/>
		<acme:form-return code="investor.application.form.button.return" />
	</acme:form>
</jstl:if> -->

<jstl:if test="${command != 'create' and not empty quittel}">
	<acme:form readonly="true">
		<acme:form-textbox code="investor.application.form.label.ticker" path="ticker" />
		<acme:form-moment code="investor.application.form.label.moment" path="moment" />
		<acme:form-textbox code="investor.application.form.label.statement" path="statement" />
		<jstl:if test="${not empty justification}">
		<acme:form-textbox code="investor.application.form.label.justification" path="justification" />
		</jstl:if>
		<acme:form-money code="investor.application.form.label.moneyOffer" path="moneyOffer" />
		<acme:form-textbox code="investor.application.form.label.investmentTicker" path="investmentTicker" />
		<acme:form-textbox code="investor.application.form.label.investorName" path="investorName" />
		<acme:form-textbox code="investor.application.form.label.quittel" path="quittel" />
		<jstl:if test="${not empty link}">
		<acme:form-url code="investor.application.form.label.link" path="link" />
		<jstl:if test="${ not empty passwordProtected}">
		<acme:form-textbox code="investor.application.form.label.passwordProtected" path="passwordProtected" />
		</jstl:if></jstl:if>
		<acme:form-return code="investor.application.form.button.return" />
	</acme:form>
</jstl:if>

<jstl:if test="${command != 'create' and empty quittel}">
	<acme:form readonly="true">
		<acme:form-textbox code="investor.application.form.label.ticker" path="ticker" />
		<acme:form-moment code="investor.application.form.label.moment" path="moment" />
		<acme:form-textbox code="investor.application.form.label.statement" path="statement" />
		<jstl:if test="${not empty justification}">
		<acme:form-textbox code="investor.application.form.label.justification" path="justification" />
		</jstl:if>
		<acme:form-money code="investor.application.form.label.moneyOffer" path="moneyOffer" />
		<acme:form-textbox code="investor.application.form.label.investmentTicker" path="investmentTicker" />
		<acme:form-textbox code="investor.application.form.label.investorName" path="investorName" />

		<acme:form-return code="investor.application.form.button.return" />
	</acme:form>
</jstl:if>